package ma.fsts.digitbank.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@DiscriminatorValue(value = "SAVA")
public class SavingAccount extends BankAccount{
    private double interestRate;


}
