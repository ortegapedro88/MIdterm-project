package MidtermProject.Project.models;

import MidtermProject.Project.utils.Money;
import MidtermProject.Project.utils.Status;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
public class SavingAccount extends Account {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "interest_rate_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "interest_rate_amount"))
    })
    private Money interestRate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount"))
    })
    private Money minBalance;

    private LocalDate applyInterestRateDate;


    public Money getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Money interestRate) {
        if( interestRate == null) interestRate = new Money(new BigDecimal(0.0025), getBalance().getCurrency());
        else if(interestRate.getAmount().doubleValue() < 0.5){
        this.interestRate = interestRate;
        }else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El interes no puede ser mayor a 0,5");
    }

    public Money getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(Money minBalance) {
        if(minBalance == null) minBalance = new Money(new BigDecimal(1000),getBalance().getCurrency());
        else if (minBalance.getAmount().intValue() > 100) {
            this.minBalance = minBalance;
        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El balance minimo no puede ser inferior a 100");
    }

    public LocalDate getApplyInterestRateDate() {
        return applyInterestRateDate;
    }

    public void setApplyInterestRateDate(LocalDate applyInterestRateDate) {
        this.applyInterestRateDate = applyInterestRateDate;
    }
}
