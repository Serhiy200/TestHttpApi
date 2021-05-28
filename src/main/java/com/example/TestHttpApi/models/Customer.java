package com.example.TestHttpApi.models;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Customer {

    private int id;
    private long created;
    private long updated;

    //All these annotations set validation for data from html
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String fullName;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 6, max = 14, message = "Phone should be between 6 and 14 characters")
    @Pattern(regexp = "\\+[0-9()-\\\\.]*", message = "Enter valid phone number +XXXXXXXXXX")
    private String phone;
    private boolean isActive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Customer(int id, long created, long updated, String fullName, String email, String phone, boolean isActive) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.isActive = isActive;
    }
}
