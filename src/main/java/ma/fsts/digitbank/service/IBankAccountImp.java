package ma.fsts.digitbank.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.fsts.digitbank.dto.CustomerDto;
import ma.fsts.digitbank.entities.*;
import ma.fsts.digitbank.enums.OperationType;
import ma.fsts.digitbank.exceptions.BalanceNotSufficientException;
import ma.fsts.digitbank.exceptions.BankAccountNotFounddException;
import ma.fsts.digitbank.exceptions.CustomerNotFoundException;
import ma.fsts.digitbank.mappers.BankAccountMapperImpl;
import ma.fsts.digitbank.repositories.BankAccountRepository;
import ma.fsts.digitbank.repositories.CustomerRepository;
import ma.fsts.digitbank.repositories.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Transactional
@Service
@Slf4j
public class IBankAccountImp implements IBankService{

    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private OperationRepository operationRepository;
    private BankAccountMapperImpl mapp;
    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        log.info("saving account .....");
        Customer customer = mapp.customerfromCustomerDto(customerDto);
        customer.setId(UUID.randomUUID().toString());
        customerRepository.save(customer);
        return mapp.customerDtofromCustomer(customer);
    }

    @Override
    public CurrentAccount saveCurrentBankAccount(double intialeBalance, double overDraft, String customerId) throws CustomerNotFoundException {
        CurrentAccount bankAccount = new CurrentAccount();
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException("Ooooops !! customer not found ");
        bankAccount.setBalance(intialeBalance);
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCustomer(customer);
        bankAccount.setOverDraft(overDraft);
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public SavingAccount saveSavingAccountBankAccount(double intialeBalance, double intrestRate, String customerId) throws CustomerNotFoundException {
        SavingAccount bankAccount = new SavingAccount();
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException("Ooooops !! customer not found ");
        bankAccount.setBalance(intialeBalance);
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCustomer(customer);
        bankAccount.setInterestRate(intrestRate);
        return bankAccountRepository.save(bankAccount);
    }



    @Override
    public List<CustomerDto> getAllCustomers() {
         List<Customer> customers = customerRepository.findAll();
         List<CustomerDto> customerDtos = new ArrayList<>();
         customers.forEach(customer -> {
             customerDtos.add(mapp.customerDtofromCustomer(customer));
         });
    return customerDtos;
    }

    @Override
    public BankAccount getBankAccount(String accountId) throws BankAccountNotFounddException {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).
                orElseThrow(()->new BankAccountNotFounddException("Bank account not found !!!"));
        return bankAccount ;
    }

    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotFounddException, BalanceNotSufficientException {
        BankAccount bankAccount = getBankAccount(accountId);
        if (bankAccount.getBalance() < amount)
            throw  new BalanceNotSufficientException("your balance is not sufficient !!");


        Operation operation = new Operation();
        operation.setBankAccount(bankAccount);
        operation.setOperationType(OperationType.DEBIT);
        operation.setAmount(amount);
        operation.setOperationDate(new Date());
        operationRepository.save(operation);
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void credit(String accountId, double amount, String description) throws BankAccountNotFounddException {
        BankAccount bankAccount = getBankAccount(accountId);



        Operation operation = new Operation();
        operation.setBankAccount(bankAccount);
        operation.setOperationType(OperationType.CREDIT);
        operation.setAmount(amount);
        operation.setOperationDate(new Date());
        operationRepository.save(operation);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfert(String accountIdSource, String accountIdDest, double amount) throws BankAccountNotFounddException, BalanceNotSufficientException {
        debit(accountIdSource,amount,"transfert to :"+accountIdDest );
        credit(accountIdDest,amount,"transfert from :"+accountIdSource);

    }
    @Override
    public CustomerDto getCustomerDto(String customerId) throws  CustomerNotFoundException{
        Customer customer = customerRepository.findById(customerId).orElseThrow(()->new  CustomerNotFoundException("Customer not found exception !!"));
        return  mapp.customerDtofromCustomer(customer);
    }


}
