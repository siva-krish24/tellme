package com.tellme.demo.users;
import java.util.Objects;


public class UserAuth {
    private String name;
    private String password;

    public UserAuth(User user) {
        this.name = user.getName();
        this.password = user.getPassword();
    }

    public UserAuth(String username, String password) {
        this.name = username;
        this.password =password;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuth userAuth = (UserAuth) o;
        return name.equals(userAuth.name) &&
                password.equals(userAuth.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }

    public void setPassword(String password) {
        this.password = password;
    }


}