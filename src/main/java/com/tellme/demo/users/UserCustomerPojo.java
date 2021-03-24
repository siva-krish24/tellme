package com.tellme.demo.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "user",
        "customer",
        "date"
})
public class UserCustomerPojo {

    @JsonProperty("user")
    private User user;
    @JsonProperty("customer")
    private Customer customer;
    @JsonProperty("state")
    private int state;
    @JsonProperty("date")
    private String date;

    @JsonProperty("date")
    public String getDate() {
        return date;
    }
    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }


    @JsonProperty("state")
    public int getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(int state) {
        this.state = state;
    }
    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }

    @JsonProperty("customer")
    public Customer getCustomer() {
        return customer;
    }

    @JsonProperty("customer")
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


}
