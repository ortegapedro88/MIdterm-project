package MidtermProject.Project.services;

import MidtermProject.Project.models.Account;
import MidtermProject.Project.repositories.AccountHolderRepository;
import MidtermProject.Project.repositories.AccountRepository;
import MidtermProject.Project.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AccountHolderService {
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountRepository accountRepository;

    public Money getBalance (long accountId, UserDetails user){
        if(!accountRepository.findById(accountId).isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cuenta inexistente");
        long primaryUserId = accountRepository.findById(accountId).get().getPrimary().getId();
        long secundaryUserId = accountRepository.findById(accountId).get().getSecondary().getId();

        if(Long.parseLong(user.getUsername()) == primaryUserId ||  Long.parseLong(user.getUsername()) == secundaryUserId ){
        return accountRepository.findById(accountId).get().getBalance();
        }else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El usuario no es titular primario ni secundario de la cuenta");

    }

}
