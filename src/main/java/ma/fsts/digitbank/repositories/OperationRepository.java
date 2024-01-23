package ma.fsts.digitbank.repositories;

import ma.fsts.digitbank.entities.BankAccount;
import ma.fsts.digitbank.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation
        ,Long> {
}
