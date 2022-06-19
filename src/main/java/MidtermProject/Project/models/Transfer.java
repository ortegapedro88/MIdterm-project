package MidtermProject.Project.models;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    private long originAccount;
    @NotEmpty
    private long destinationAccount;
    @Digits(integer = 6,fraction = 2)
    @Positive
    private double amount;
    private final LocalDate DATE = LocalDate.now();

    public long getId() {
        return id;
    }

    public long getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(long originAccount) {
        this.originAccount = originAccount;
    }

    public long getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(long destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDATE() {
        return DATE;
    }
}
