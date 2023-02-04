package com.eliezer.securepass.Service;

import com.eliezer.securepass.Domain.Account;

public interface WebAccountService {
    Account createAccountWeb(Long userId, Account account);
    Account getAccountByIdWeb(Long idAcc);
    Account updateAccountWeb(Account account);
}
