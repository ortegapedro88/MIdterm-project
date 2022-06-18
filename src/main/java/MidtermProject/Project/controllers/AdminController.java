package MidtermProject.Project.controllers;

import MidtermProject.Project.DTO.AccountDTO;
import MidtermProject.Project.DTO.AccountHolderDTO;
import MidtermProject.Project.DTO.CreditCardDTO;
import MidtermProject.Project.DTO.SavingDTO;
import MidtermProject.Project.models.*;
import MidtermProject.Project.services.AdminService;
import MidtermProject.Project.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;


    @PostMapping("/admin/create-account")
    public Account createAccount(@RequestBody AccountDTO dto){
        System.out.println("LA CONCHA DE TU MADRE");
        return adminService.createAccount(dto);

    }

    @PostMapping("/admin/create-saving")
    public SavingAccount createSaving(@RequestBody SavingDTO dto){
        return adminService.createSavingAccount(dto);
    }

    @PostMapping("/admin/create-third")
    public ThirdParty createThird(@RequestBody ThirdParty thirdParty){
        return adminService.createThirdParty(thirdParty);
    }

    @PostMapping("/admin/create-credit")
    public CreditCardAccount createCredit(@RequestBody CreditCardDTO dto){
        return adminService.createCreditCardAccount(dto);
    }

    @PostMapping("/admin/create-account-holder")
    public AccountHolder createAccHold(@RequestBody AccountHolderDTO accountHolder){
        return adminService.createAccountHolder(accountHolder);
    }

    @PostMapping("/admin/create-admin")
    public Admin createAdmin(@RequestBody Admin admin){
        return adminService.createAdmin(admin);
    }

    @GetMapping("/admin/check-balance/{id}")
    public Money getBalance(@PathVariable Long id){
        return adminService.getBalanceByAccount(id);
    }

    @PatchMapping("/admin/modify-balance/{id}")
    public Account modifyBalance(@PathVariable Long id, @RequestBody Money balance){
        return adminService.setBalanceByAccount(id,balance);
    }

    @GetMapping("/admin/say-hi")
    public String sayHi(){
        return "hi";
    }

}
