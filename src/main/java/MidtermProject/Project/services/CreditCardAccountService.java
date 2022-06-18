package MidtermProject.Project.services;

import MidtermProject.Project.models.CheckingAccount;
import MidtermProject.Project.models.CreditCardAccount;
import MidtermProject.Project.repositories.CreditCardAccountRepository;
import MidtermProject.Project.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class CreditCardAccountService {
    @Autowired
    CreditCardAccountRepository creditCardAccountRepository;

    public CreditCardAccount checkBalance(long accountId) {
        CreditCardAccount account = creditCardAccountRepository.findById(accountId).get();
        LocalDate hoy = LocalDate.now();
        LocalDate monthlyFeeLastDate = account.getInterestRateDate();
        if (ChronoUnit.MONTHS.between(hoy, monthlyFeeLastDate) > 1) {
            BigDecimal resultMonths = new BigDecimal(ChronoUnit.MONTHS.between(hoy, monthlyFeeLastDate));
            BigDecimal monthlyInterest = account.getBalance().getAmount().multiply(account.getInterestRate().getAmount().divide(new BigDecimal(12)));
            BigDecimal newBalance = account.getBalance().getAmount().add(monthlyInterest);
            account.setBalance(new Money(newBalance, account.getBalance().getCurrency()));
            account.setInterestRateDate(hoy);
        } return  creditCardAccountRepository.save(account);
    }
}
