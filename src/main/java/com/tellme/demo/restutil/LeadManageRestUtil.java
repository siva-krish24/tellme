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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.tellme.demo.Constants.*;
import static com.tellme.demo.restutil.CommonRestUtil.*;

@RestController
public class LeadManageRestUtil {
    private static UserCustomerMap userCustomerMap = UserCustomerMap.getuserCustomersMapInstance();
    @Autowired
    UserCustomerRepositry userCustomerRepo;

    @PostMapping("/makelogin")
    public void makeLogin(@RequestBody String userCustomer) throws JsonProcessingException {
        System.out.println("/makelogin: " + userCustomer);

        UserCustomerEntity userCustomerEntity = authenticate(userCustomer);
        if(userCustomerEntity==null){
            System.out.println("/makelogin: " + "User Authentication failed");
            return;
        }

        userCustomerEntity.setState(LOGIN);
        userCustomerEntity.setDate(DAY);

        cleanupOldEntity(userCustomerEntity.getUser(),userCustomerEntity.getCustomer());
        userCustomerMap.putIfAbsent(userCustomerEntity.getUser(),new HashSet<>());
        userCustomerMap.get(userCustomerEntity.getUser()).add(userCustomerEntity);
        userCustomerRepo.save(userCustomerEntity);;

        System.out.println("/makelogin: Saved" + userCustomerEntity);

    }

    @PostMapping("/closelead")
    public void setLeadclosed(@RequestBody String userCustomer) throws JsonProcessingException {
        System.out.println("/closelead: " + userCustomer);

        UserCustomerEntity userCustomerEntity = authenticate(userCustomer);
        if(userCustomerEntity==null){
            System.out.println("/closelead: " + "User Authentication failed");
            return;
        }

        userCustomerEntity.setState(LEADCLOSED);
        userCustomerEntity.setDate(DAY);

        cleanupOldEntity(userCustomerEntity.getUser(),userCustomerEntity.getCustomer());
        userCustomerMap.putIfAbsent(userCustomerEntity.getUser(),new HashSet<>());
        userCustomerMap.get(userCustomerEntity.getUser()).add(userCustomerEntity);
        userCustomerRepo.save(userCustomerEntity);

        System.out.println("/closelead: Saved" + userCustomerEntity);

    }

    @PostMapping("/getlogins")
    public List<UserCustomerEntity> getLogins(@RequestBody String userAuth) throws JsonProcessingException {
        System.out.println("/getlogins: " + userAuth);

        List<UserCustomerEntity> customers = new ArrayList<>();
        User user = getUser(userAuth);
        if(user == null) return customers;
        if(userCustomerMap.containsKey(user))
        customers.addAll(userCustomerMap.get(user).stream()
                .filter(entity -> entity.getUser()
                        .equals(user) && entity.getState()==LOGIN).collect(Collectors.toList()));



        System.out.println("/getlogins: " + customers);
        return customers;
    }

    @PostMapping("/getleadsclosed")
    public List<UserCustomerEntity> getLeadsClosed(@RequestBody String userAuth) throws JsonProcessingException {
        System.out.println("/getleadsclosed: " + userAuth);

        List<UserCustomerEntity> customers = new ArrayList<>();
        User user = getUser(userAuth);
        if(user == null) return customers;
        if(userCustomerMap.containsKey(user))
            customers.addAll(userCustomerMap.get(user).stream()
                .filter(entity -> entity.getUser()
                        .equals(user) && entity.getState()==LEADCLOSED).collect(Collectors.toList()));



        System.out.println("/getleadsclosed: " + customers);
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
