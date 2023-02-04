package com.eliezer.securepass.Service;

import com.eliezer.securepass.Domain.Account;
import com.eliezer.securepass.Domain.User;
import com.eliezer.securepass.Reporsitory.AccountRepository;
import com.eliezer.securepass.Reporsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebAccountServiceImpl implements WebAccountService {

    UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    AccountRepository accountRepository;

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccountWeb(Long userId, Account account) {
        User user = userRepository.findUserById(userId);
        account.setUser(user);
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountByIdWeb(Long idAcc) {
        return accountRepository.findById(idAcc).get();
    }

    @Override
    public Account updateAccountWeb(Account account) {
        return accountRepository.save(account);
    }
}
