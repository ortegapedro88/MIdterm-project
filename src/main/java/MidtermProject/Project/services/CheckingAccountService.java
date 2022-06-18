package MidtermProject.Project.services;

import MidtermProject.Project.models.Account;
import MidtermProject.Project.models.CheckingAccount;
import MidtermProject.Project.repositories.AccountHolderRepository;
import MidtermProject.Project.repositories.CheckingAccountRepository;
import MidtermProject.Project.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class CheckingAccountService {
    @Autowired
    CheckingAccountRepository checkingAccountRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    public CheckingAccount checkBalance(long accountId){
        CheckingAccount account = checkingAccountRepository.findById(accountId).get();
        LocalDate hoy = LocalDate.now();
        LocalDate monthlyFeeLastDate = account.getApplyMonthlyFeeDate();
        if (ChronoUnit.MONTHS.between(hoy,monthlyFeeLastDate) > 1){
            BigDecimal resultMonths = new BigDecimal(ChronoUnit.MONTHS.between(hoy,monthlyFeeLastDate));
            BigDecimal newBalance = account.getBalance().getAmount().subtract(account.getMonthlyFee().getAmount().multiply(resultMonths));
            account.setBalance(new Money(newBalance,account.getBalance().getCurrency()));
            account.setApplyMonthlyFeeDate(hoy);
        }
        if(account.getBalance().getAmount().doubleValue()<account.getMinBalance().getAmount().doubleValue()){
            BigDecimal newBalance = account.getBalance().getAmount().subtract(account.getPenaltyFee().getAmount());
            account.setBalance(new Money(newBalance,account.getBalance().getCurrency()));
        }return checkingAccountRepository.save(account);
    }
}
