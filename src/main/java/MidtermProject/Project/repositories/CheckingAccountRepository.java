package MidtermProject.Project.repositories;

import MidtermProject.Project.models.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingAccountRepository extends JpaRepository <CheckingAccount,Long> {
}
