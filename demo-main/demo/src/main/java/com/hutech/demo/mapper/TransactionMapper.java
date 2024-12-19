package com.hutech.demo.mapper;

import com.hutech.demo.model.Transaction;
import com.hutech.demo.response.TransactionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = TransactionMapper.class)
public interface TransactionMapper {
    @Mapping(target = "booking", source = "booking")
    TransactionResponse toResponse(Transaction Transaction);
    List<TransactionResponse> getList(List<Transaction> Transactions);
}
