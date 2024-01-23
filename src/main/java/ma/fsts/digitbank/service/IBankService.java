package ma.fsts.digitbank.service;

import ma.fsts.digitbank.entities.BankAccount;
import ma.fsts.digitbank.entities.CurrentAccount;
import ma.fsts.digitbank.entities.Customer;
import ma.fsts.digitbank.entities.SavingAccount;
import ma.fsts.digitbank.exceptions.BalanceNotSufficientException;
import ma.fsts.digitbank.exceptions.BankAccountNotFounddException;
import ma.fsts.digitbank.exceptions.CustomerNotFoundException;

import java.util.List;

public interface IBankService {
    Customer saveCustomer(Customer customer);
    CurrentAccount saveCurrentBankAccount(double intialeBalance, double overDraft, String customerId) throws CustomerNotFoundException;
    SavingAccount saveSavingAccountBankAccount(double intialeBalance, double intrestRate, String customerId) throws CustomerNotFoundException;



    List<Customer> getAllCustomers();
    BankAccount getBankAccount(String accountId) throws BankAccountNotFounddException;
    void debit(String accountId,double amount,String description) throws BankAccountNotFounddException, BalanceNotSufficientException;
    void credit(String accountId,double amount,String description) throws BankAccountNotFounddException;
    void transfert(String accountIdSource,String accountIdDest,double amount) throws BankAccountNotFounddException, BalanceNotSufficientException;

}
