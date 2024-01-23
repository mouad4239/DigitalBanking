package ma.fsts.digitbank.mappers;

import ma.fsts.digitbank.dto.CustomerDto;
import ma.fsts.digitbank.entities.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {

    public  CustomerDto  customerDtofromCustomer(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer,customerDto);
    return  customerDto;
    }

    public  Customer  customerfromCustomerDto(CustomerDto customerDto){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto,customer);
        return  customer;
    }
}
