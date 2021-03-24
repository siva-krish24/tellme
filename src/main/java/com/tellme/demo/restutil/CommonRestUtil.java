package com.tellme.demo.restutil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tellme.demo.repositries.UserCustomerRepositry;
import com.tellme.demo.transactions.RegisteredUsers;
import com.tellme.demo.transactions.UserCustomerMap;
import com.tellme.demo.users.*;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommonRestUtil {


    private static RegisteredUsers registeredUsers  = RegisteredUsers.getRegisteredUsersMapInstance();
    private static UserCustomerMap userCustomerMap = UserCustomerMap.getuserCustomersMapInstance();



    public   static User getUser(String userAuth) throws JsonProcessingException {
        String userId = new ObjectMapper().readValue(userAuth, UserId.class).getUserId();
        if(registeredUsers.containsKey(userId)){
            return (User) registeredUsers.get(userId);
        }
        return null;
    }
    public static UserCustomerEntity authenticate(String userCustomer) throws JsonProcessingException {
        UserCustomerPojo userIdCustomerPojo = new ObjectMapper().readValue(userCustomer, UserCustomerPojo.class);
        if(userIdCustomerPojo==null) return null;
        User user = registeredUsers.get(userIdCustomerPojo.getUser().getName());
        Customer customer = userIdCustomerPojo.getCustomer();
        if(user==null || customer == null) return null;
        return new UserCustomerEntity(user,customer);
    }

    public static int getCount(User user, int state, String dateTime, int days){
        if(state==-1 && userCustomerMap.containsKey(user))  return (int) userCustomerMap.get(user).stream()
                .filter(entity -> entity.getUser()
                        .equals(user) &&
                        ChronoUnit.DAYS
                                .between(LocalDate.parse(entity.getDate()),LocalDate.parse(dateTime))<=days).count();

        if(days<7) return userCustomerMap.containsKey(user) ?(int) userCustomerMap.get(user).stream()
                .filter(
                        entity -> entity.getUser().equals(user) && entity.getState()==state
                                && LocalDate.parse(entity.getDate()).equals(LocalDate.parse(dateTime))).count():0;
        else
            return userCustomerMap.containsKey(user) ?(int) userCustomerMap.get(user).stream()
                    .filter(entity -> entity.getUser().equals(user)
                            && entity.getState()==state
                            && ChronoUnit.DAYS
                            .between(LocalDate.parse(dateTime), LocalDate.parse(entity.getDate()))<days).count():0;
    }

}
