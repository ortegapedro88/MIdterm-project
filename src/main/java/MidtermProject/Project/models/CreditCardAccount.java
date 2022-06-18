package MidtermProject.Project.models;

import MidtermProject.Project.utils.Money;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
public class CreditCardAccount extends Account{

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "interest_rate_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "interest_rate_amount"))
    })
    private Money interestRate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "credit_limit_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount"))
    })
    private Money creditLimit;


    private LocalDate interestRateDate;



    public Money getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Money interestRate) {
        if(interestRate == null) interestRate = new Money(new BigDecimal(0.2),getBalance().getCurrency());
        else if (interestRate.getAmount().doubleValue() > 0.1) {
            this.interestRate = interestRate;
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "el interes no puede ser menor que 0.1");
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        if(creditLimit == null){
            creditLimit = new Money(new BigDecimal(100),getBalance().getCurrency());
        }
        else if(creditLimit.getAmount().intValue()< 100000){
            this.creditLimit = creditLimit;
        }else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"el limite de credito no puede ser mayor que 100000");
    }

    public LocalDate getInterestRateDate() {
        return interestRateDate;
    }

    public void setInterestRateDate(LocalDate interestRateDate) {
        this.interestRateDate = interestRateDate;
    }
}
