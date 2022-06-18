package MidtermProject.Project.DTO;

import MidtermProject.Project.utils.Money;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CreditCardDTO {
    @NotNull
    private Long primaryHolderId;
    private Long secondaryHolderId;
    @NotEmpty
    private String dateOfBirth;
    @NotNull
    private double balance;
    @NotEmpty
    private String secretKey;

    private double interestRate;

    private double creditLimit;


    public CreditCardDTO (){}

    public Long getPrimaryHolderId() {
        return primaryHolderId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String date) {
        this.dateOfBirth = date;
    }

    public void setPrimaryHolderId(long primaryHolderId) {
        this.primaryHolderId = primaryHolderId;
    }

    public Long getSecondaryHolderId() {
        return secondaryHolderId;
    }

    public void setSecondaryHolderId(long secondaryHolderId) {
        this.secondaryHolderId = secondaryHolderId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }
}
