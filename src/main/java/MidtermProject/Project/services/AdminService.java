package MidtermProject.Project.services;

import MidtermProject.Project.DTO.AccountDTO;
import MidtermProject.Project.DTO.AccountHolderDTO;
import MidtermProject.Project.DTO.CreditCardDTO;
import MidtermProject.Project.DTO.SavingDTO;
import MidtermProject.Project.models.*;
import MidtermProject.Project.repositories.*;
import MidtermProject.Project.utils.Money;
import MidtermProject.Project.utils.Status;
import org.hibernate.type.LocalDateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    SavingAccountRepository savingAccountRepository;
    @Autowired
    CheckingAccountRepository checkingAccountRepository;
    @Autowired
    CreditCardAccountRepository creditCardAccountRepository;
    @Autowired
    StudentCheckingAccountRepository studentCheckingAccountRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    public Account createAccount(AccountDTO account) {
        System.out.println("---------------- ENTRA AL CREATE ACCOUNT-------------");
        LocalDate hoy = LocalDate.now();
        System.out.println(hoy);
        System.out.println(account.getDateOfBirth());
       LocalDate birthDate = LocalDate.parse(account.getDateOfBirth());
        System.out.println(birthDate);
    System.out.println(ChronoUnit.YEARS.between(birthDate,hoy));

      if (ChronoUnit.YEARS.between(birthDate,hoy) > 24)  return createCheckingAccount(account);
       else return createStudentAccount(account);
       // return null;
    }

    public CreditCardAccount createCreditCardAccount(CreditCardDTO account) {
        return createCreditCard(account);
    }

    public SavingAccount createSavingAccount(SavingDTO account) {
        return createSaving(account);
    }

    public AccountHolder createAccountHolder (AccountHolderDTO accountHolder){
        if (accountHolderRepository.findById(accountHolder.getId()).isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Username already exists");
        }
        AccountHolder newAccountHolder = new AccountHolder();
        newAccountHolder.setId(accountHolder.getId());
        newAccountHolder.setName(accountHolder.getName());
        newAccountHolder.setPassword(accountHolder.getPassword());
        newAccountHolder.setAddress(accountHolder.getAddress());
        newAccountHolder.setEmail(accountHolder.getEmail());
        newAccountHolder.setBirthDate(LocalDate.parse(accountHolder.getDateOfBirth()));
        newAccountHolder.addRole(new Role("ACCOUNT_HOLDER"));
        return accountHolderRepository.save(newAccountHolder);
    }

    public Admin createAdmin (Admin admin){
        if (adminRepository.findById(admin.getId()).isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Username already exists");
        }
        Admin newAdmin = new Admin();
        newAdmin.setId(admin.getId());
        newAdmin.setName(admin.getName());
        newAdmin.setPassword(admin.getPassword());
        return adminRepository.save(newAdmin);
    }
    public ThirdParty createThirdParty (ThirdParty thirdParty){
        if (thirdPartyRepository.findById(thirdParty.getId()).isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Username already exists");
        }
        ThirdParty newThirdParty = new ThirdParty();
        newThirdParty.setId(newThirdParty.getId());
        newThirdParty.setName(newThirdParty.getName());
        newThirdParty.setPassword(newThirdParty.getPassword());
        return thirdPartyRepository.save(newThirdParty);
    }

    public Money getBalanceByAccount(long accountId){
        if(!accountRepository.findById(accountId).isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"cuenta inexistente");
        Account account = accountRepository.findById(accountId).get();
        return account.getBalance();
    }

    public Account setBalanceByAccount (long accountId, Money amount){
        if(!accountRepository.findById(accountId).isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"cuenta inexistente");
        Account account = accountRepository.findById(accountId).get();
        BigDecimal newBalance = account.getBalance().getAmount().add(amount.getAmount());
        account.setBalance(new Money(newBalance,account.getBalance().getCurrency()));
        return accountRepository.save(account);
    }
    private StudentCheckingAccount createStudentAccount (AccountDTO account){
        System.out.println("------------------ ENTRA AL STUDENT------------------");

        AccountHolder secondaryAccountHolder = null;
        StudentCheckingAccount newAccount = new StudentCheckingAccount();
        if (!accountHolderRepository.findById(account.getPrimaryHolderId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario inexistente");
        }
        AccountHolder primaryAccountHolder = accountHolderRepository.findById(account.getPrimaryHolderId()).get();
        if (account.getSecondaryHolderId() != null) {
            if (!accountHolderRepository.findById(account.getSecondaryHolderId()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario secundario inexistente");
            }
            secondaryAccountHolder = accountHolderRepository.findById(account.getSecondaryHolderId()).get();
        }
        newAccount.setPrimary(primaryAccountHolder);
        newAccount.setSecondary(secondaryAccountHolder);
        newAccount.setSecretKey(account.getSecretKey());
        newAccount.setBalance(new Money(new BigDecimal(account.getBalance())));
        return studentCheckingAccountRepository.save(newAccount);
    }
    private CheckingAccount createCheckingAccount (AccountDTO account){

        System.out.println("------------------ ENTRA AL CHECKING------------------");
        AccountHolder secondaryAccountHolder = null;
        CheckingAccount newAccount = new CheckingAccount();
        if (!accountHolderRepository.findById(account.getPrimaryHolderId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario inexistente");
        }
        AccountHolder primaryAccountHolder = accountHolderRepository.findById(account.getPrimaryHolderId()).get();
        if (account.getSecondaryHolderId() != null) {
            if (!accountHolderRepository.findById(account.getSecondaryHolderId()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario secundario inexistente");
            }
            secondaryAccountHolder = accountHolderRepository.findById(account.getSecondaryHolderId()).get();
        }
        newAccount.setPrimary(primaryAccountHolder);
        newAccount.setSecondary(secondaryAccountHolder);
        newAccount.setSecretKey(account.getSecretKey());
        newAccount.setBalance(new Money(new BigDecimal(account.getBalance())));
        newAccount.setApplyMonthlyFeeDate(LocalDate.now());
        return checkingAccountRepository.save(newAccount);
    }
    private CreditCardAccount createCreditCard(CreditCardDTO account){
        CreditCardAccount newAccount = new CreditCardAccount();
        AccountHolder secondaryAccountHolder = null;
        if(!accountHolderRepository.findById(account.getPrimaryHolderId()).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"usuario inexistente");
        }
        AccountHolder primaryAccountHolder = accountHolderRepository.findById(account.getPrimaryHolderId()).get();
        if (account.getSecondaryHolderId() != null) {
            if (!accountHolderRepository.findById(account.getSecondaryHolderId()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario secundario inexistente");
            }
            secondaryAccountHolder = accountHolderRepository.findById(account.getSecondaryHolderId()).get();
        }
        newAccount.setPrimary(primaryAccountHolder);
        newAccount.setSecondary(secondaryAccountHolder);
        newAccount.setSecretKey(account.getSecretKey());
        newAccount.setBalance(new Money(new BigDecimal(account.getBalance())));
        newAccount.setCreditLimit(new Money(new BigDecimal(account.getCreditLimit())));
        newAccount.setInterestRate(new Money(new BigDecimal(account.getInterestRate())));
        newAccount.setInterestRateDate(LocalDate.now());
        return creditCardAccountRepository.save(newAccount);
    }
    private SavingAccount createSaving(SavingDTO account){
        SavingAccount newAccount = new SavingAccount();
        AccountHolder secondaryAccountHolder = null;
        if(!accountHolderRepository.findById(account.getPrimaryHolderId()).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"usuario inexistente");
        }
        AccountHolder primaryAccountHolder = accountHolderRepository.findById(account.getPrimaryHolderId()).get();
        if (account.getSecondaryHolderId() != null) {
            if (!accountHolderRepository.findById(account.getSecondaryHolderId()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario secundario inexistente");
            }
            secondaryAccountHolder = accountHolderRepository.findById(account.getSecondaryHolderId()).get();
        }
        newAccount.setPrimary(primaryAccountHolder);
        newAccount.setSecondary(secondaryAccountHolder);
        newAccount.setSecretKey(account.getSecretKey());
        newAccount.setBalance(new Money(new BigDecimal(account.getBalance())));
        newAccount.setInterestRate(new Money(new BigDecimal(account.getInterestRate())));
        newAccount.setMinBalance(new Money(new BigDecimal(account.getMinBalance())));
        newAccount.setApplyInterestRateDate(LocalDate.now());
        return savingAccountRepository.save(newAccount);
    }

}

