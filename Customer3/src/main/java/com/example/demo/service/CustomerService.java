package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.CustomerException;
import com.example.demo.pojo.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> getAllCustomer(){
		return customerRepository.findAll();
	}
	
		
	public Optional<Customer> findCustomerById (Long id) {
//		return customerRepository.findById(id);
		Optional<Customer> customer = customerRepository.findById(id);
		
		if(!customer.isPresent()) {
			throw new CustomerException("Customer with id:" + id +" not found");
		}
		
		return customer;
	}
	
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public void deleteCustomerById(Long id) {
		customerRepository.deleteById(id);
	}

	
	public Optional<Customer> updateCustomer(Long id, Customer c) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            Customer updatedCustomer = existingCustomer.get();
            updatedCustomer.setName(c.getName());
            updatedCustomer.setEmail(c.getEmail());
            return Optional.of(customerRepository.save(updatedCustomer));
        }
        return Optional.empty();
	}
	


}
