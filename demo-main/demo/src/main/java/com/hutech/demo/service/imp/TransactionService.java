package com.hutech.demo.service.imp;

import com.hutech.demo.filter.TransactionFilter;
import com.hutech.demo.mapper.TransactionMapper;
import com.hutech.demo.model.Booking;
import com.hutech.demo.model.Transaction;
import com.hutech.demo.repository.BookingRepository;
import com.hutech.demo.repository.TransactionRepository;
import com.hutech.demo.response.TransactionResponse;
import com.hutech.demo.service.inf.ITransactionService;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService {
    private final TransactionRepository transactionRepository;
    private final BookingRepository bookingRepository;
    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionRepository transactionRepository, BookingRepository bookingRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.bookingRepository = bookingRepository;
        this.transactionMapper = transactionMapper;
    }
    @Override
    public void saveVNPayTransaction(Long bookingId, String transactionId, String orderInfo, BigDecimal amount, String responseCode, String transactionStatus, String bankCode, LocalDateTime payDate) {
        try {
            Booking booking = bookingRepository.findById(bookingId)
                    .orElseThrow(() -> new BadRequestException("Booking with ID " + bookingId + " not found"));

            // Create a new Transaction object and save it
            Transaction transaction = Transaction.builder()
                    .booking(booking)
                    .transactionId(transactionId)
                    .orderInfo(orderInfo)
                    .amount(amount)
                    .responseCode(responseCode)
                    .transactionStatus(transactionStatus)
                    .bankCode(bankCode)
                    .payDate(payDate)
                    .createdAt(LocalDateTime.now())
                    .build();

            transactionRepository.save(transaction);

            // Optionally, update booking status based on transaction outcome
            if ("00".equals(transactionStatus)) {
                booking.setStatus(Booking.Status.CONFIRMED);
            } else {
                booking.setStatus(Booking.Status.EXPIRED);
            }

            bookingRepository.save(booking);
        }
        catch(Exception e){
            throw new RuntimeException("Error during transaction saving", e);
        }

    }

    @Override
    public TransactionResponse getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));
        return transactionMapper.toResponse(transaction);
    }

    @Override
    public List<TransactionResponse> getTransactions(TransactionFilter filter) {
        // Fetch all transactions from the repository
        List<Transaction> transactions = transactionRepository.findAll();

        // Apply filters based on non-null filter fields
        List<Transaction> filteredTransactions = transactions.stream()
                .filter(transaction -> filter.getBookingId() == null || transaction.getBooking().getId().equals(filter.getBookingId()))
                .filter(transaction -> filter.getTransactionId() == null || transaction.getTransactionId().equals(filter.getTransactionId()))
                .filter(transaction -> filter.getOrderInfo() == null || transaction.getOrderInfo().contains(filter.getOrderInfo()))
                .filter(transaction -> filter.getAmount() == null || transaction.getAmount().equals(filter.getAmount()))
                .filter(transaction -> filter.getResponseCode() == null || transaction.getResponseCode().equals(filter.getResponseCode()))
                .filter(transaction -> filter.getBankCode() == null || transaction.getBankCode().equals(filter.getBankCode()))
                .filter(transaction -> filter.getTransactionStatus() == null || transaction.getTransactionStatus().equals(filter.getTransactionStatus()))
                .filter(transaction -> filter.getPayDate() == null || (transaction.getPayDate() != null && transaction.getPayDate().isAfter(filter.getPayDate())))
                .filter(transaction -> filter.getCreatedAt() == null || (transaction.getCreatedAt() != null && transaction.getCreatedAt().isAfter(filter.getCreatedAt())))
                .collect(Collectors.toList());

        // Map filtered transactions to response
        return transactionMapper.getList(filteredTransactions);
    }
}
