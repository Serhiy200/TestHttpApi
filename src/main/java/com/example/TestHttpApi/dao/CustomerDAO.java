package com.example.TestHttpApi.dao;

import com.example.TestHttpApi.models.Customer;
import com.example.TestHttpApi.models.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDAO {

    private Data date;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDAO(JdbcTemplate jdbcTemplate) {
        //Connecting to Database
        this.jdbcTemplate = jdbcTemplate;
        date = new Data();
    }

    //Add new customer to database
    public void addCustomer(Customer customer) {

        long dateMillisecond = date.dataMillisecon();

        String sql = "INSERT INTO customer (created, updated, full_name, email, phone, is_active) VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, dateMillisecond, dateMillisecond, customer.getFullName(), customer.getEmail(), customer.getPhone(), true);
    }

    //Get all Customers which are not deleted
    public List<Customer> getAllCustomers() {

        String sql = "SELECT * FROM customer WHERE is_active = 1";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Customer.class));
    }

    //Get one customer by id
    public Customer getCustomerById(int id) {

        String sql = "SELECT * FROM customer WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Customer.class)).stream().findAny().orElse(null);
    }

    //Update information about customer by id
    public void updateCustomer(int id, Customer customer) {

        long dateMillisecond = date.dataMillisecon();

        String sql = "UPDATE customer set updated = ?, full_name = ?, email = ?, phone = ? WHERE id = ?";
        jdbcTemplate.update(sql, dateMillisecond, customer.getFullName(), customer.getEmail(), customer.getPhone(), id);

    }

    //Delete customer by id
    public void deleteCustomer(int id) {

        String sql = "UPDATE customer set is_active = false WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
