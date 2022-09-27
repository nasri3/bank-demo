package com.example.bank.mapper;

import com.example.bank.dto.AccountDto;
import com.example.bank.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AccountMapper {

    AccountDto mapToDto(Account account);

    List<AccountDto> mapToDtos(List<Account> accounts);

    @Mapping(target = "id", ignore = true)
    Account mapToEntity(AccountDto accountDto);
}
