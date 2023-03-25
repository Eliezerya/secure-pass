package com.eliezer.securepass.web.Controller;

import com.eliezer.securepass.Domain.Account;
import com.eliezer.securepass.Domain.User;
import com.eliezer.securepass.Service.AccountService;
import com.eliezer.securepass.Service.LoginService;
import com.eliezer.securepass.Service.WebAccountService;
import com.eliezer.securepass.web.Dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {

    AccountService accountService;

    WebAccountService webAccountService;

    LoginService loginService;

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public Authentication authentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;
    }


    @Autowired
    public void setWebAccountService(WebAccountService webAccountService) {
        this.webAccountService = webAccountService;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/user/account")
    public String createAccountForm(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "create_account";
    }

    @PostMapping("/user/account")
    public String createAccount(@ModelAttribute("account") Account account) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = loginService.findByEmail(currentPrincipalName);
        webAccountService.createAccountWeb(user.getId(), account);
        return "redirect:/user/accounts";
    }

    @GetMapping("/user/accounts")
    public String displayAccounts(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = loginService.findByEmail(currentPrincipalName);

        model.addAttribute("accounts", accountService.displayAccount(user.getId()));
        return "home";
    }

    @PostMapping("/user/account/{idAcc}")
    public String updateAccount(@PathVariable("idAcc") Long idAcc,@ModelAttribute("account") Account accountDto) {
        Account account = webAccountService.getAccountByIdWeb(idAcc);
        account.setId(account.getId());
        account.setNameAccount(accountDto.getNameAccount());
        account.setEmailAccount(accountDto.getEmailAccount());
        account.setUsernameAccount(accountDto.getUsernameAccount());
        account.setDescriptionAccount(accountDto.getDescriptionAccount());
        account.setPasswordAccount(accountDto.getPasswordAccount());
        webAccountService.updateAccountWeb(account);
        return "redirect:/user/accounts";
    }

    @GetMapping("/user/account/{idAcc}")
    public String editAccountForm(@PathVariable("idAcc") Long id, Model model){
        model.addAttribute("account",webAccountService.getAccountByIdWeb(id));
        return "edit_account";
    }


}