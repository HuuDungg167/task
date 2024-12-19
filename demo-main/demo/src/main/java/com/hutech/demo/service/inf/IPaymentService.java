package com.hutech.demo.service.inf;

import com.hutech.demo.createrequest.CreatePaymentRequest;
import com.hutech.demo.response.PaymentResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface IPaymentService {
    PaymentResponse createPayment(CreatePaymentRequest request, HttpServletRequest req);
}
