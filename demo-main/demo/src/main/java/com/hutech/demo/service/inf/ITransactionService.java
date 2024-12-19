package com.hutech.demo.service.inf;

import com.hutech.demo.filter.TransactionFilter;
import com.hutech.demo.response.TransactionResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ITransactionService {
    void saveVNPayTransaction(Long bookingId, String transactionId, String orderInfo, BigDecimal amount,
                                     String responseCode, String transactionStatus, String bankCode, LocalDateTime payDate);
    TransactionResponse getTransactionById(Long id);
    List<TransactionResponse> getTransactions(TransactionFilter filter);
}
