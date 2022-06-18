package MidtermProject.Project.DTO;

import MidtermProject.Project.utils.Address;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class AccountHolderDTO {
    @NotEmpty
    private long id;
    private String name;
    @NotEmpty
    private String password;
    @NotEmpty
    private String dateOfBirth;
    private Address address;
    @Email
    private String email;

    public AccountHolderDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
