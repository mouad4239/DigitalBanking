package ma.fsts.digitbank.web;


import lombok.AllArgsConstructor;
import ma.fsts.digitbank.dto.CustomerDto;
import ma.fsts.digitbank.exceptions.CustomerNotFoundException;
import ma.fsts.digitbank.service.IBankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerWeb {
    private IBankService iBankService;

    @GetMapping
    List<CustomerDto> getCustomersDto(){
        return iBankService.getAllCustomers();
    }
    @GetMapping("/{id}")
    CustomerDto getCustomer(@PathVariable String id) throws CustomerNotFoundException {
        return  iBankService.getCustomerDto(id);
    }

    @PostMapping
    CustomerDto saveCustomer(@RequestBody CustomerDto customerDto){
        return  iBankService.saveCustomer(customerDto);
    }

}
