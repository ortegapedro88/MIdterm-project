package MidtermProject.Project.controllers;

import MidtermProject.Project.repositories.AccountHolderRepository;
import MidtermProject.Project.services.AccountHolderService;
import MidtermProject.Project.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountHolderController {
    @Autowired
    AccountHolderService accountHolderService;

    @GetMapping("/user/check-balance/{account}")
    public Money checkBalance(@PathVariable long account, @AuthenticationPrincipal UserDetails userDetails){
        return accountHolderService.getBalance(account,userDetails);
    }


    @PostMapping("/transfer/")

}
