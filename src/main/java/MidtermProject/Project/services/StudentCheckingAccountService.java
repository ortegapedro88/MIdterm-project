package MidtermProject.Project.services;

import MidtermProject.Project.repositories.StudentCheckingAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCheckingAccountService {
    @Autowired
    StudentCheckingAccountRepository studentCheckingAccountRepository;
}
