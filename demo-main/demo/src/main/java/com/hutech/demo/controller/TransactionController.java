package com.hutech.demo.controller;

import com.hutech.demo.filter.BookingFilter;
import com.hutech.demo.filter.TransactionFilter;
import com.hutech.demo.model.Transaction;
import com.hutech.demo.response.BookingResponse;
import com.hutech.demo.response.TransactionResponse;
import com.hutech.demo.service.inf.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private ITransactionService transactionService;

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getBookingById(@PathVariable Long id) {
        TransactionResponse transaction = transactionService.getTransactionById(id);
        return transaction != null ? ResponseEntity.ok(transaction) : ResponseEntity.notFound().build();
    }

    @GetMapping("/")
    public List<TransactionResponse> getAllBookings(TransactionFilter filter) {
        return transactionService.getTransactions(filter);
    }
}
