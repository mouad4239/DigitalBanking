package ma.fsts.digitbank.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Customer {
    @Id
    private String id;
    @Column(unique = true)
    private String username;
    private String email;
    @OneToMany(mappedBy = "customer")
    private List<BankAccount> accounts;

}
