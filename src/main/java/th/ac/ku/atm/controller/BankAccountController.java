package th.ac.ku.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.service.BankAccountService;

@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {
    private BankAccountService accountService;

    public BankAccountController(BankAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String getAccountPage(Model model){
        model.addAttribute("accountList", accountService.getAccounts());
        return "bankaccount";
    }

    @PostMapping
    public String createAccount(@ModelAttribute BankAccount account, Model model){
        accountService.createAccount(account);
        model.addAttribute("accountList", accountService.getAccounts());
        return "redirect:bankaccount";
    }
}
