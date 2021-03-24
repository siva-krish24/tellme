package com.tellme.demo.users;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Data
@Document(collection = "customer")
public class Customer implements Lead {
    @Id
    String id;
    String name;
    String mobile;
    String varient;
    String hypo;
    String details;

    @Override
    public String toString() {
        return "{"
                + "  \"id\":\"" + id + "\""
                + ",   \"name\":\"" + name + "\""
                + ",   \"mobile\":\"" + mobile + "\""
                + ",   \"varient\":\"" + varient + "\""
                + ",   \"hypo\":\"" + hypo + "\""
                + ",   \"details\":\"" + details + "\""
                + "}";
    }


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVarient() {
        return varient;
    }

    public void setVarient(String varient) {
        this.varient = varient;
    }

    public String getHypo() {
        return hypo;
    }

    public void setHypo(String hypo) {
        this.hypo = hypo;
    }

    public Customer() {
    }

    public Customer(String id, String name, String mobile, String varient, String hypo) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.varient = varient;
        this.hypo = hypo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return getName().equals(customer.getName()) &&
                getMobile().equals(customer.getMobile());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getMobile());
    }

}
