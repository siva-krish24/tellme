package com.tellme.demo.restutil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tellme.demo.repositries.UserCustomerRepositry;
import com.tellme.demo.transactions.UserCustomerMap;
import com.tellme.demo.users.Customer;
import com.tellme.demo.users.User;
import com.tellme.demo.users.UserCustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.tellme.demo.Constants.DAY;
import static com.tellme.demo.Constants.NOTANSWERED;
import static com.tellme.demo.restutil.CommonRestUtil.*;

@RestController
public class NotAnsweredRestUtil {
    private static UserCustomerMap userCustomerMap = UserCustomerMap.getuserCustomersMapInstance();
    @Autowired
    UserCustomerRepositry userCustomerRepo;


    @PostMapping("/addtonotanswerd")
    public void addToNotAnswered(@RequestBody  String userCustomer) throws JsonProcessingException {
        System.out.println("/addtonotanswerd: " + userCustomer);

        UserCustomerEntity userCustomerEntity = authenticate(userCustomer);
        if(userCustomerEntity==null){
            System.out.println("/addtonotanswerd: " + "User Authentication failed");
            return;
        }

        userCustomerEntity.setState(NOTANSWERED);
        String day  =  LocalDateTime.now().toString().split("T")[0];
        userCustomerEntity.setDate(day);
        cleanupOldEntity( userCustomerEntity.getUser(),userCustomerEntity.getCustomer());

        userCustomerMap.putIfAbsent(userCustomerEntity.getUser(),new HashSet<>());
        userCustomerMap.get(userCustomerEntity.getUser()).add(userCustomerEntity);
        userCustomerRepo.save(userCustomerEntity);;
        System.out.println("/addtonotanswerd: Done!" + userCustomer);

    }


    @PostMapping("/getnotanswerd")
    public List<UserCustomerEntity> notAnsweredCustomers(@RequestBody String userAuth) throws JsonProcessingException {
        System.out.println("/getnotanswerd: " + userAuth);

        List<UserCustomerEntity> customers = new ArrayList<>();
        User user = getUser(userAuth);
        if(user == null) return customers;
        if(userCustomerMap.containsKey(user))
        customers.addAll(userCustomerMap.get(user).stream()
                .filter(entity -> entity.getUser()
                        .equals(user) && entity.getState()==NOTANSWERED).collect(Collectors.toList()));

        System.out.println("/getnotanswerd: " + customers);

        return customers;
    }


    private void cleanupOldEntity(User user, Customer customer) {
        List<UserCustomerEntity> list = new ArrayList<>();
        if(userCustomerMap.containsKey(user)){
            list.addAll(userCustomerMap.get(user).stream()
                    .filter(entity -> entity.getCustomer().equals(customer)).collect(Collectors.toList()));
            list.stream().forEach(use -> {
                userCustomerMap.get(user).remove(use);
                if(userCustomerRepo.findAll().contains(use))
                    userCustomerRepo.delete(use);
            });
        }
    }

}
