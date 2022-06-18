package MidtermProject.Project.models;

import MidtermProject.Project.utils.Money;
import MidtermProject.Project.utils.Status;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
public class CheckingAccount extends Account{

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount"))
    })
    private Money minBalance;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "monthly_rate_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "monthly_fee_amount"))
    })
    private Money monthlyFee;

    private LocalDate applyMonthlyFeeDate;





    public Money getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(Money minBalance) {
        this.minBalance = minBalance;
    }

    public Money getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(Money monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    @Override
    public void setBalance(Money balance) {
        super.setBalance(balance);
        minBalance = new Money(new BigDecimal(250),getBalance().getCurrency());
        monthlyFee = new Money(new BigDecimal(12),getBalance().getCurrency());
    }

    public LocalDate getApplyMonthlyFeeDate() {
        return applyMonthlyFeeDate;
    }

    public void setApplyMonthlyFeeDate(LocalDate applyMonthlyFeeDate) {
        this.applyMonthlyFeeDate = applyMonthlyFeeDate;
    }
}
