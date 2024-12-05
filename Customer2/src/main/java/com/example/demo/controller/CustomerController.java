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
import com.example.demo.repository.CustomerRepository;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepo;
	
	@GetMapping
	public List<Customer> getAllCustomers(){
		return customerRepo.finaAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
	    return customerRepo.findById(id)
	    		.map(ResponseEntity::ok)
	    		.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customerInput) {
	 Customer customer = customerRepo.saveCustomer(customerInput);
	 return new ResponseEntity<>(customer, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id){
		customerRepo.deleteById(id);
		return ResponseEntity.noContent().build();
	}


	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
	    return customerRepo.updateCustomer(id, customer)
	        .map(ResponseEntity::ok) 
	        .orElseGet(() -> ResponseEntity.notFound().build()); 
	}


}
