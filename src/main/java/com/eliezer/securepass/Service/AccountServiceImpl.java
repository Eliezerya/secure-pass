package com.eliezer.securepass.Service;

import com.eliezer.securepass.Domain.Account;
import com.eliezer.securepass.Domain.User;
import com.eliezer.securepass.Reporsitory.AccountRepository;
import com.eliezer.securepass.Reporsitory.UserRepository;
import com.eliezer.securepass.web.Dto.AccountDto;
import com.eliezer.securepass.web.Mappers.AccountMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    AccountMapper accountMapper;

//    private PasswordEncoder passwordEncoder;

    UserRepository userRepository;
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Autowired
//    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    @Autowired
    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public Account createAccount(Long id,AccountDto accountDto) {
        Account account = new Account();
        account.setUsernameAccount(accountDto.getUsernameAccount());
        account.setEmailAccount(accountDto.getEmailAccount());
        account.setNameAccount(accountDto.getNameAccount());
        account.setPasswordAccount(accountDto.getPasswordAccount());
        account.setDescriptionAccount(accountDto.getDescriptionAccount());
        User user = userRepository.findUserById(id);
        account.setUser(user);
        return accountRepository.save(account);
    }



    @Override
    public List<Account> displayAccount(Long userId) {
        return accountRepository.findAllByUserId(userId);
    }

    @Override
    public List<Account> displayAccountByNameAccount(String keyword, Long userId) {
        return accountRepository.findAllByNameAccountAndUserId(keyword,userId);
    }

    @Override
    public Account updateAccount(Long idAcc, AccountDto accountDto) {
        Optional<Account> fetchAccount = accountRepository.findById(idAcc);
        if (fetchAccount.isPresent()){
            Account account = fetchAccount.get();
            account.setNameAccount(accountDto.getNameAccount());
            account.setEmailAccount(accountDto.getEmailAccount());
            account.setUsernameAccount(accountDto.getUsernameAccount());
            account.setDescriptionAccount(accountDto.getDescriptionAccount());
            account.setPasswordAccount(accountDto.getPasswordAccount());
            accountRepository.save(account);
            return account;
        }
        return null;
    }

}
