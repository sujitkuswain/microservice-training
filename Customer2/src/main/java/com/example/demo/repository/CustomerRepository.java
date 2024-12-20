package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Customer;

@Repository
public class CustomerRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final RowMapper<Customer> rowMapper=new RowMapper<Customer>() {
		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException{
			return new Customer(rs.getLong("id"), rs.getString("name"), rs.getString("email"));
		}
	};
	
	public List<Customer> finaAll(){
		return jdbcTemplate.query("select * from customers", rowMapper);
	}
	
	
	public Optional<Customer> findById(Long id){
		return jdbcTemplate.query("select * from customers where id=?", new Object[] {id}, rowMapper)
				.stream().findFirst();
	}
	
	public Customer saveCustomer(Customer customer) {
		jdbcTemplate.update("insert into customers(id, name, email)values (?,?,?)", customer.getId(), customer.getName(), customer.getEmail());
		return findById(customer.getId()).orElse(null);
	}
	
	public Optional<Customer> updateCustomer(Long id, Customer c) {
		int rowsUpdated = jdbcTemplate.update("update customers set name=?, email=? where id=?", c.getName(), c.getEmail(), id);
	    
		if (rowsUpdated > 0) {
	        return findById(id); 
	    } else {
	        return Optional.empty(); 
	    }
	}
	
	public int deleteById(Long id) {
		return jdbcTemplate.update("delete from customers where id=?", id);
	}
	
}
