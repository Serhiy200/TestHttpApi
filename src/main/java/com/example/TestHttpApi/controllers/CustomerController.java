package com.example.TestHttpApi.controllers;

import com.example.TestHttpApi.dao.CustomerDAO;
import com.example.TestHttpApi.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api")
public class CustomerController {


    private CustomerDAO customerDAO;

    @Autowired
    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping("/customers")
    public String index(Model model, Customer customer) {

        //get list of customers from v
        model.addAttribute("customers", customerDAO.getAllCustomers());

        return "main";
    }

    @GetMapping("/customers/{id}")
    public String getCustomer(@PathVariable("id") int id, Model model) {

        //get customer from Database
        model.addAttribute("customer", customerDAO.getCustomerById(id));

        return "customer-page";
    }

    @PostMapping(value = "/customers")
    public String addNewCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {

        //verification data from html
        if (bindingResult.hasErrors()) {
            return "main";
        }

        //add new customer to Database
        customerDAO.addCustomer(customer);

        return "redirect:/api/customers";
    }


    @PatchMapping("/customers/{id}")
    public String updateCustomer(@Valid @ModelAttribute("customer") Customer customer, @PathVariable("id") int id, BindingResult bindingResult) {

        //verification data from html
        if (bindingResult.hasErrors()) {
            return "main";
        }

        //update date about customer in Database
        customerDAO.updateCustomer(id, customer);

        return "redirect:/api/customers";
    }


    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable(value = "id", required = false) int id) {

        //delete customer from UI but not from Database
        customerDAO.deleteCustomer(id);

        return "redirect:/api/customers";
    }
}
