package com.tellme.demo.users;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Data
@Document(collection = "usercustomer")
@Component
public class UserCustomerEntity implements Lead{
    public UserCustomerEntity() {
    }

    private User user;
    @Id
    private Customer customer;

    private int state;

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public UserCustomerEntity(User user, Customer customer) {
        this.user = user;
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCustomerEntity)) return false;
        UserCustomerEntity that = (UserCustomerEntity) o;
        return getUser().equals(that.getUser()) &&
                getCustomer().equals(that.getCustomer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getCustomer().hashCode());
    }
}
