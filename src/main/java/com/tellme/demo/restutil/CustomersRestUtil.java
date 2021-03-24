package com.tellme.demo.restutil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tellme.demo.CustomerQueue;
import com.tellme.demo.repositries.UserCustomerRepositry;
import com.tellme.demo.transactions.RegisteredUsers;
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
import java.util.Set;
import java.util.stream.Collectors;

import static com.tellme.demo.Constants.DAY;
import static com.tellme.demo.restutil.CommonRestUtil.*;

@RestController
public class CustomersRestUtil {
    @Autowired
    private UserCustomerRepositry userCustomerRepo;

    private static RegisteredUsers registeredUsers  = RegisteredUsers.getRegisteredUsersMapInstance();
    private static UserCustomerMap userCustomerMap = UserCustomerMap.getuserCustomersMapInstance();
    private static CustomerQueue customerQueue = CustomerQueue.getStreamInstance();

    @PostMapping("/customers")
    public Set<UserCustomerEntity> getCustomers(@RequestBody String userAuth) throws JsonProcessingException {

        Set<UserCustomerEntity> customers = new HashSet<>();
        System.out.println(DAY+ ":  /customers called ");

        User user = getUser(userAuth);
        if(user == null) return customers;

        customers.addAll(getFreshLeads(user, 5));

        System.out.println(DAY+":"+ customers);

        return customers;
    }

    @PostMapping("/getonecustomer")
    public UserCustomerEntity getOneCustomers(@RequestBody User user) throws JsonProcessingException {
        System.out.println(DAY+ ": /getonecustomer called");
        if(user == null) return new UserCustomerEntity();
        return getCustomer(user);
    }
    public List<UserCustomerEntity> getFreshLeads(User user, int rem) {

        List<UserCustomerEntity> customers = new ArrayList<>();
        if(userCustomerMap.containsKey(user)){
            customers.addAll(userCustomerMap.get(user).stream().filter(entity -> entity.getState()==0)
                    .collect(Collectors.toList()));
        }
        rem = 5-customers.size();
        for(int i=0;i<rem;i++){
            if(!customerQueue.isEmpty()) {
                Customer customer = customerQueue.poll();
                UserCustomerEntity userCustomerEntity = new UserCustomerEntity(user,customer);
                userCustomerEntity.setState(0);
                String day  =  LocalDateTime.now().toString().split("T")[0];
                userCustomerEntity.setDate(day);
                cleanupOldEntity(user,customer,0);
                userCustomerRepo.save(userCustomerEntity);
                userCustomerMap.putIfAbsent(user, new HashSet<>());
                userCustomerMap.get(user).add(userCustomerEntity);
                customers.add(userCustomerEntity);
            }
        }
        return customers;
    }


    public  UserCustomerEntity getCustomer(User user){
        if(!customerQueue.isEmpty()) {
            Customer customer = customerQueue.poll();
            UserCustomerEntity userCustomerEntity = new UserCustomerEntity(user,customer);
            userCustomerEntity.setState(0);
            String day  =  LocalDateTime.now().toString().split("T")[0];
            userCustomerEntity.setDate(day);
            cleanupOldEntity(user, customer,0);
            userCustomerRepo.save(userCustomerEntity);
            userCustomerMap.putIfAbsent(user, new HashSet<>());
            userCustomerMap.get(user).add(userCustomerEntity);
            return userCustomerEntity;
        }
        return new UserCustomerEntity();
    }

    private  void cleanupOldEntity(User user, Customer customer, int state) {
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
