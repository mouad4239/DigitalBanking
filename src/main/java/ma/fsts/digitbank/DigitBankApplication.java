package ma.fsts.digitbank;

import ma.fsts.digitbank.entities.*;
import ma.fsts.digitbank.enums.OperationType;
import ma.fsts.digitbank.enums.Status;
import ma.fsts.digitbank.exceptions.CustomerNotFoundException;
import ma.fsts.digitbank.repositories.BankAccountRepository;
import ma.fsts.digitbank.repositories.CustomerRepository;
import ma.fsts.digitbank.repositories.OperationRepository;
import ma.fsts.digitbank.service.IBankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitBankApplication.class, args);
    }
    @Bean
    CommandLineRunner start(IBankService iBankService){
        return args -> {
            Stream.of("Mohamed","Mouad","Said").forEach(name->{
                Customer customer = new Customer();
                customer.setId(UUID.randomUUID().toString());
                customer.setUsername(name);
                customer.setEmail(name+"@gmail.com");
                //iBankService.saveCustomer(customer);
            });







        };
    }

}
