package MidtermProject.Project.models;

import MidtermProject.Project.utils.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

@Entity
@NoArgsConstructor
public class AccountHolder extends User{
    @Embedded
    private Address address;
    @Email
    private String email;

    private LocalDate birthDate;
    @OneToMany(mappedBy = "primary")
    @JsonIgnore
    private List<Account> primaryList = new ArrayList<>();
    @OneToMany(mappedBy = "secondary")
    @JsonIgnore
    private List<Account> secondaryList = new ArrayList<>();
    public AccountHolder(long id, String name, String password) {
        super(id, name, password);
    }

    public AccountHolder(long id, String name, String password, Address address, String email,LocalDate birthDate) {
        super(id, name, password);
        super.addRole(new Role("account_holder"));
        setAddress(address);
        setEmail(email);
        setBirthDate(birthDate);

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Account> getPrimaryList() {
        return primaryList;
    }

    public void setPrimaryList(List<Account> primaryList) {
        this.primaryList = primaryList;
    }

    public List<Account> getSecondaryList() {
        return secondaryList;
    }

    public void setSecondaryList(List<Account> secondaryList) {
        this.secondaryList = secondaryList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new HashSet<>();

        for(Role role : super.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
        }

        return authorities;
    }

    @Override
    public String getUsername() {
        return Long.toString(super.getId());
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
