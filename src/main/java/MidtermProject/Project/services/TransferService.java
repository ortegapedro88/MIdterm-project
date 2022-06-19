package MidtermProject.Project.services;

import MidtermProject.Project.models.Account;
import MidtermProject.Project.models.Transfer;
import MidtermProject.Project.models.User;
import MidtermProject.Project.repositories.*;
import MidtermProject.Project.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferService {
    @Autowired
    TransferRepository transferRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    SavingAccountRepository savingAccountRepository;
    @Autowired
    CheckingAccountRepository checkingAccountRepository;
    @Autowired
    CreditCardAccountRepository creditCardAccountRepository;
    @Autowired
    StudentCheckingAccountRepository studentCheckingAccountRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    private Account transfer(Transfer transfer, UserDetails userDetails){

        if(accountRepository.findById(transfer.getOriginAccount()).isPresent() && accountRepository.findById(transfer.getDestinationAccount()).isPresent() ){
            Account origin = accountRepository.findById(transfer.getOriginAccount()).get();
            Account destination = accountRepository.findById(transfer.getDestinationAccount()).get();
            User origUser = userRepository.findById(Long.parseLong(userDetails.getUsername())).get();

            if(origin.getPrimary().getId() == origUser.getId() || origin.getSecondary().getId() == origUser.getId()){
                Money originNewBalance = new Money(origin.getBalance().getAmount().subtract(new BigDecimal(transfer.getAmount())));
                Money destinationNewBalance = new Money(destination.getBalance().getAmount().add(new BigDecimal(transfer.getAmount())));
                origin.setBalance(originNewBalance);
                destination.setBalance(destinationNewBalance);

                transferRepository.save(transfer);
                accountRepository.save(origin);
                accountRepository.save(destination);

            }
        }

        return null;
    }

}
