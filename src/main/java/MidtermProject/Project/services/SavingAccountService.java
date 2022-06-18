package MidtermProject.Project.services;

import MidtermProject.Project.models.CheckingAccount;
import MidtermProject.Project.models.SavingAccount;
import MidtermProject.Project.repositories.SavingAccountRepository;
import MidtermProject.Project.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class SavingAccountService {
    @Autowired
    SavingAccountRepository savingAccountRepository;


    public SavingAccount checkBalance(long accountId){
        SavingAccount account = savingAccountRepository.findById(accountId).get();
        LocalDate hoy = LocalDate.now();
        LocalDate interestRateLastDate = account.getApplyInterestRateDate();
         if (ChronoUnit.DAYS.between(hoy,interestRateLastDate) > 365){
             BigDecimal resultYears =  new BigDecimal(ChronoUnit.DAYS.between(hoy,interestRateLastDate) / 365);
             BigDecimal newBalance = account.getBalance().getAmount().multiply(account.getInterestRate().getAmount().multiply(resultYears));
             account.setBalance(new Money(account.getBalance().getAmount().add(newBalance),account.getBalance().getCurrency()));
             account.setApplyInterestRateDate(hoy);
         }
         if(account.getBalance().getAmount().doubleValue()<account.getMinBalance().getAmount().doubleValue()){
            BigDecimal newBalance = account.getBalance().getAmount().subtract(account.getPenaltyFee().getAmount());
            account.setBalance(new Money(newBalance,account.getBalance().getCurrency()));
        }return savingAccountRepository.save(account);
    }
}
