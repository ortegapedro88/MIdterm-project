package MidtermProject.Project.services;

import MidtermProject.Project.models.Account;
import MidtermProject.Project.repositories.AccountHolderRepository;
import MidtermProject.Project.repositories.AccountRepository;
import MidtermProject.Project.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AccountHolderService {
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountRepository accountRepository;

    public Money getBalance (long accountId,long userId){
        if(!accountRepository.findById(accountId).isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cuenta inexistente");
        return accountRepository.findById(accountId).get().getBalance();
    }

}
