package com.hutech.demo.repository;

import com.hutech.demo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByTransactionId(String transactionId);
}