package com.eliezer.securepass.web.Controller;

import com.eliezer.securepass.Domain.Account;
import com.eliezer.securepass.Service.AccountService;
import com.eliezer.securepass.Service.WebAccountService;
import com.eliezer.securepass.web.Dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {

    AccountService accountService;

    WebAccountService webAccountService;

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
        webAccountService.createAccountWeb(1L, account);
        return "redirect:/user/" + account.getUser().getId() + "/accounts";
    }

    @GetMapping("/user/{userId}/accounts")
    public String displayAccounts(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("accounts", accountService.displayAccount(userId));
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
        return "redirect:/user/" +account.getUser().getId()+ "/accounts";
    }

    @GetMapping("/user/account/{idAcc}")
    public String editAccountForm(@PathVariable("idAcc") Long id, Model model){
        model.addAttribute("account",webAccountService.getAccountByIdWeb(id));
        return "edit_account";
    }

//    @PostMapping("api/v1/user/{userId}/account")
//    public ResponseEntity<?> createAccount(@PathVariable("userId") Long userId, @RequestBody AccountDto accountDto){
//        return new ResponseEntity<>(accountService.createAccount(userId,accountDto), HttpStatus.CREATED);
//    }

    //    @GetMapping("api/v1/user/{userId}/accounts")
//    public ResponseEntity<?> displayAccounts(@PathVariable("userId") Long userId){
//        return new ResponseEntity<>(accountService.displayAccount(userId), HttpStatus.CREATED);
//    }


//    @GetMapping("api/v1/user/{userId}/account")
//    public ResponseEntity<?> displayAccountsByNameAccount(@PathVariable("userId") Long userId,@RequestParam(value = "keyword") String keyword){
//        return new ResponseEntity<>(accountService.displayAccountByNameAccount(keyword,userId), HttpStatus.OK);
//    }

//    @PutMapping("api/v1/user/account/{idAcc}")
//    public ResponseEntity<?> updateAccount(@PathVariable("idAcc") Long idAcc,@RequestBody AccountDto accountDto){
//        return new ResponseEntity<>(accountService.updateAccount(idAcc,accountDto), HttpStatus.ACCEPTED);
//    }

}