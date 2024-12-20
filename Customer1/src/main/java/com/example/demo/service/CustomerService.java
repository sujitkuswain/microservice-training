package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.pojo.Customer;

@Service
public class CustomerService {
	
	private List<Customer> customers = new ArrayList<Customer>();
	
	Customer customerArray[] = {
			new Customer(11L, "AA", "AA@gmail.com"),
			new Customer(22L, "BB", "BB@gmail.com")
	};
	
	public CustomerService() {
		 customers = new ArrayList<>(List.of(customerArray));
	}
	
	public List<Customer> findAll(){
		return customers;
	}
	
	public Optional<Customer> findById(Long id) {
	    return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst();
	}

	public Customer saveCustomer(Customer cus) {
		customers.add(cus);
		return cus;
	}
	
	public void deleteById(Long id) {
		customers.removeIf(c -> c.getId().equals(id));
	}
	
	public Optional<Customer> updateCustomer(Long id, Customer customer) {
		Optional<Customer> existingCustomer = findById(id);
		
		if(existingCustomer.isPresent()) {
			existingCustomer.get().setName(customer.getName());
			existingCustomer.get().setEmail(customer.getEmail());
			return existingCustomer;
		}
		return Optional.empty();
	}
	
}
