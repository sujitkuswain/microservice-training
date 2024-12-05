package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Customer;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping()
	public List<Customer> getALlCustomers(){
		return customerService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
//		Optional<Customer> customer = customerService.findById(id);
//	    return new ResponseEntity<Customer>(customer.get(),HttpStatus.OK);
	    
	    return customerService.findById(id)
	    		.map(ResponseEntity::ok)
	    		.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customerInput) {
	 Customer customer = customerService.saveCustomer(customerInput);
	 return new ResponseEntity<>(customer, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id){
		customerService.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
	    return customerService.updateCustomer(id, customer)
	            .map(ResponseEntity::ok)
	            .orElseGet(() -> ResponseEntity.notFound().build());
	}
}
