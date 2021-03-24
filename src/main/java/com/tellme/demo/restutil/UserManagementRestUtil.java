package com.tellme.demo.restutil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tellme.demo.repositries.UserRepository;
import com.tellme.demo.transactions.RegisteredUsers;
import com.tellme.demo.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.tellme.demo.restutil.CommonRestUtil.getUser;

@RestController
public class UserManagementRestUtil {
    @Autowired
    UserRepository userRepository;

    private static RegisteredUsers registeredUsers  = RegisteredUsers.getRegisteredUsersMapInstance();

    @GetMapping("/getusers")
    public List<User> getUsers(){
        System.out.println("/getusers " );
        List<User> users = new ArrayList<>();
        users.addAll(registeredUsers.values());
        System.out.println("/getusers " + users);
        return users;
    }

    @PostMapping("/signin")
    public User signin(@RequestBody String userAuth) throws JsonProcessingException {
        System.out.println("/signin: " + userAuth);

        User user = getUser(userAuth);
        if(user == null) return null;
        System.out.println("/signin: " + user);

        return user;
    }
    @PostMapping("/signup")
    public void signUp(@RequestBody String userStr) throws JsonProcessingException {
        System.out.println("/signup" + userStr);


        User user = new ObjectMapper().readValue(userStr, User.class);
        if(user==null) return;
        if(!registeredUsers.containsKey(user.getName())){
            registeredUsers.put(user.getName(),user);
        }
        else {
            registeredUsers.remove(user.getName());
            registeredUsers.put(user.getName(),user);
        }
        userRepository.save(user);
        System.out.println("/signup" + userStr + " Success");
    }
}
