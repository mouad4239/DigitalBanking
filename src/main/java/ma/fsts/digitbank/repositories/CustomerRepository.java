package ma.fsts.digitbank.repositories;

import ma.fsts.digitbank.entities.BankAccount;
import ma.fsts.digitbank.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
}
