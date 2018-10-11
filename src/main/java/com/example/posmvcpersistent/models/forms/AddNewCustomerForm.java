package com.example.posmvcpersistent.models.forms;

import com.example.posmvcpersistent.models.Customer;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//not persistent
public class AddNewCustomerForm {

    @Id
    @GeneratedValue
    private int customerId;

    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getCustomerId() {
        return customerId;
    }

    public AddNewCustomerForm(){}

}
