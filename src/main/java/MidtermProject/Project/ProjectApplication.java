package MidtermProject.Project;

import MidtermProject.Project.models.AccountHolder;
import MidtermProject.Project.models.Admin;
import MidtermProject.Project.models.Role;
import MidtermProject.Project.repositories.AccountHolderRepository;
import MidtermProject.Project.repositories.AdminRepository;
import MidtermProject.Project.utils.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

		@Autowired
	AdminRepository adminRepository;

		@Autowired
	AccountHolderRepository accountHolderRepository;
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public void run(String... args) throws Exception {

		Admin admin = new Admin();
		admin.setId(123123123);
		admin.setName("Pedro");
		admin.setPassword(passwordEncoder.encode("montoto123"));
		admin.addRole(new Role("ADMIN"));
		adminRepository.save(admin);

		Address address= new Address();
		address.setAddress("tuvieja");
		address.setCity("bcn");
		address.setCountry("Spa");
		address.setPostalCode(8903);

		LocalDate birthDate = LocalDate.of(1988,10,31);

		AccountHolder accountHolder = new AccountHolder();
		accountHolder.setId(34179343);
		accountHolder.setName("Martu");
		accountHolder.setPassword(passwordEncoder.encode("1234"));
		accountHolder.setAddress(address);
		accountHolder.setEmail("asd@asd.com");
		accountHolder.setBirthDate(birthDate);
		accountHolder.addRole(new Role("ACCOUNT_HOLDER"));
		accountHolderRepository.save(accountHolder);


		System.out.println(admin.getPassword());
		System.out.println(accountHolder.getPassword());

		LocalDate hoy = LocalDate.now();
		LocalDate birthDate1 = LocalDate.parse("1988-10-31");

		System.out.println(ChronoUnit.YEARS.between(birthDate1,hoy));
	}
}
