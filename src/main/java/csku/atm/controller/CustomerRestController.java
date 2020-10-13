package csku.atm.controller;


import csku.atm.controller.service.CustomerService;
import csku.atm.model.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {

    private CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAll(){
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    public Customer getOne(@PathVariable int id){
        return customerService.findCustomer(id);
    }

}
