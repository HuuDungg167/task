package com.hutech.demo.controller;

import com.hutech.demo.config.VNPayConfig;
import com.hutech.demo.createrequest.CreatePaymentRequest;
import com.hutech.demo.response.PaymentResponse;
import com.hutech.demo.service.inf.IBookingService;
import com.hutech.demo.service.inf.IPaymentService;
import com.hutech.demo.service.inf.ITransactionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private ITransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody CreatePaymentRequest request, HttpServletRequest req) {
        return ResponseEntity.ok(paymentService.createPayment(request, req));
    }
    @GetMapping("/callback")
    public void VNPayCallback(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, String> requestParams = new HashMap<>();
            String queryString = request.getQueryString();
            if (queryString != null) {
                String[] pairs = queryString.split("&");
                for (String pair : pairs) {
                    String[] keyValue = pair.split("=", 2);
                    String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8);
                    String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8) : "";
                    requestParams.put(key, value);
                }
            }

            System.out.println("VNPay Callback received: " + requestParams);

            String vnp_SecureHash = requestParams.get("vnp_SecureHash");
            requestParams.remove("vnp_SecureHash");

            List<String> fieldNames = new ArrayList<>(requestParams.keySet());
            Collections.sort(fieldNames);

            StringBuilder hashData = new StringBuilder();
            for (String fieldName : fieldNames) {
                if (requestParams.get(fieldName) != null && !requestParams.get(fieldName).isEmpty()) {
                    hashData.append(fieldName)
                            .append('=')
                            .append(URLEncoder.encode(requestParams.get(fieldName), StandardCharsets.US_ASCII));
                    hashData.append('&');
                }
            }

            if (hashData.length() > 0) {
                hashData.deleteCharAt(hashData.length() - 1);
            }

            String calculatedHash = VNPayConfig.hmacSHA512(VNPayConfig.secretKey, hashData.toString());

            // Log the debug information
            System.out.println("Hash data for calculation: " + hashData.toString());
            System.out.println("Received secure hash: " + vnp_SecureHash);
            System.out.println("Calculated hash: " + calculatedHash);

            if (!calculatedHash.equals(vnp_SecureHash)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Invalid secure hash");
                return;
            }

            String transactionStatus = requestParams.get("vnp_TransactionStatus");
            String vnpTxnRef = requestParams.get("vnp_TxnRef");
            Long bookingId;
            if (vnpTxnRef != null && !vnpTxnRef.isEmpty())
                bookingId = Long.parseLong(vnpTxnRef);
            else
                throw new IllegalArgumentException("vnpTxnRef from VNPay is null");
            BigDecimal amount = new BigDecimal(requestParams.get("vnp_Amount")).divide(new BigDecimal(100)); // Convert from smallest unit to actual amount
            String transactionId = requestParams.get("vnp_TransactionNo");
            String orderInfo = requestParams.get("vnp_OrderInfo"); // Add any additional information here
            String responseCode = requestParams.get("vnp_ResponseCode");
            String bankCode = requestParams.get("vnp_BankCode");
            LocalDateTime payDate = LocalDateTime.now(); // You might want to parse this from the response if available

            // Save the transaction using the TransactionService
            transactionService.saveVNPayTransaction(bookingId, transactionId, orderInfo, amount, responseCode, transactionStatus, bankCode, payDate);
            if ("00".equals(transactionStatus)) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Payment confirmed");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Payment failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try {
                response.getWriter().write("Error processing callback");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
