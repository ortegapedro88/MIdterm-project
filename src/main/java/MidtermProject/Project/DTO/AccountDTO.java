package MidtermProject.Project.DTO;

import MidtermProject.Project.utils.Money;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AccountDTO {
    @NotNull
    private Long primaryHolderId;
    private Long secondaryHolderId;
    @NotEmpty
    private String dateOfBirth;
    @NotNull
    private double balance;
    @NotEmpty
    private String secretKey;


    public AccountDTO (){}

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


}
