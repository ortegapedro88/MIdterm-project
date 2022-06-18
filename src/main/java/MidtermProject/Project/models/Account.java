package MidtermProject.Project.models;

import MidtermProject.Project.utils.Money;
import MidtermProject.Project.utils.Status;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountNumber;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "primary_user_id")
    private AccountHolder primary;
    @ManyToOne
    @JoinColumn(name = "secondary_user_id")
    private AccountHolder secondary = null;
    @NotNull
    @NotEmpty
    private String secretKey;
    @DateTimeFormat
    private final LocalDate CREATE_DATE = LocalDate.now();
    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "balance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount"))
    })
    private Money balance;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "penalty_fee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "penalty_fee_amount"))
    })
    private final Money PENALTY_FEE = new Money(new BigDecimal(40));

    private Status status = Status.ACTIVE;





    public long getAccountNumber() {
        return accountNumber;
    }

    public AccountHolder getPrimary() {
        return primary;
    }

    public void setPrimary(AccountHolder primary) {
        this.primary = primary;
    }

    public AccountHolder getSecondary() {
        return secondary;
    }

    public void setSecondary(AccountHolder secondary) {
        this.secondary = secondary;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public LocalDate getCreatedDate() {
        return CREATE_DATE;
    }


    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public Money getPenaltyFee() {
        return PENALTY_FEE;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
