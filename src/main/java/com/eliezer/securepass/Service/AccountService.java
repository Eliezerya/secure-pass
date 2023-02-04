package com.eliezer.securepass.Service;

import com.eliezer.securepass.Domain.Account;
import com.eliezer.securepass.web.Dto.AccountDto;

import java.util.List;

public interface AccountService {
    Account createAccount(Long userId, AccountDto accountDto);


    List<Account> displayAccount(Long userId);

    List<Account> displayAccountByNameAccount(String keyword,Long userId);

    Account updateAccount(Long idAcc,AccountDto accountDto);
}
