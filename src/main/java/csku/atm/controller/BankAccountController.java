package csku.atm.controller;


import csku.atm.controller.service.BankAccountService;
import csku.atm.model.BankAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {

    private BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }


    @GetMapping
    public String getBankAccountPage(Model model){
        model.addAttribute("bankaccounts",bankAccountService.getBankAccounts());
        return "bankaccount";
    }

//    @PostMapping
//    public String openBankAccount (@ModelAttribute BankAccount bankAccount
//            ,Model model){
//
//        System.out.print(bankAccount);
//
//        bankAccountService.CreateBankAccount(bankAccount);
//        model.addAttribute("allBankAccount",bankAccountService.getBankAccount());
//
//        return "redirect:bankaccount";
//    }
    @PostMapping
    public String openAccount(@ModelAttribute BankAccount bankAccount, Model model) {
        bankAccountService.openBankAccount(bankAccount);
        model.addAttribute("bankaccounts",bankAccountService.getBankAccounts());
        return "redirect:bankaccount";
    }

    @GetMapping("/edit/{id}")
    public String getEditBankAccountPage(@PathVariable int id, Model model){
        BankAccount bankAccount = bankAccountService.getBankAccount(id);
        model.addAttribute("bankAccount",bankAccount);
        return "bankaccount-edit";
    }

    @PostMapping("/edit/{id}")
    public String editAccount(@PathVariable int id,
                              @ModelAttribute BankAccount bankAccount,
                              Model model){
        bankAccountService.editBankAccount(bankAccount);
        model.addAttribute("bankaccounts", bankAccountService.getBankAccounts());

        return "redirect:/bankaccount";
    }

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable int id,
                                @ModelAttribute BankAccount bankAccount,
                                Model model){
        bankAccountService.deleteBankAccount(bankAccount);

        model.addAttribute("bankaccounts", bankAccountService.getBankAccounts());

        return "redirect:/bankaccount";

    }

}
