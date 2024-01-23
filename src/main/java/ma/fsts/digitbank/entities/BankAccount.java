package ma.fsts.digitbank.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.fsts.digitbank.enums.Status;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",discriminatorType = DiscriminatorType.STRING,length = 4)
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract  class BankAccount {

    @Id
    private String id;
    private Date createdAt;
    private Double Balance;
    @Enumerated(EnumType.STRING)
    private Status accStatus;
    private String currency;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "bankAccount")
    private List<Operation> operationList;

}
