package com.eliezer.securepass.web.Mappers;

import com.eliezer.securepass.Domain.Account;
import com.eliezer.securepass.web.Dto.AccountDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    AccountDto accountToAccountDto(Account account);
}
