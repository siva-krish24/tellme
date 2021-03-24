//package com.tellme.demo;
//
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.tellme.demo.repositries.*;
//import com.tellme.demo.transactions.*;
//import com.tellme.demo.users.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//
//@RestController
//public class RestCommands {
//@Autowired
//UserRepository userRepository;
//@Autowired
//CustomerRepositry customerRepositry;
//@Autowired
//IntrestedCustomerRepositry interestedCustomerRepo;
//@Autowired
//NotIntrestedCutomerRepositry notIntrestedCutomerRepositry;
//@Autowired
//NotAnsweredRepositry notAnsweredRepositry;
//@Autowired
//FreshLeadsRepositry freshLeadsRepositry;
//
//
//    private CustomerQueue customerQueue = CustomerQueue.getStreamInstance();
//    private UserCustomerQueue intrestedCustomerQueue = UserCustomerQueue.getStreamInstance();
//    private AuthMap authMap = AuthMap.getStreamInstance();
//    private FreshLeadsMap freshLeadsMap = FreshLeadsMap.getusetToCustomersMapInstance();
//    private RegisteredUsers registeredUsers  = RegisteredUsers.getRegisteredUsersMapInstance();
//    private CustomorStateMap followUps = FollowUpCustomersMap.getfollowUpCustomersMapInstance();
//    private NotIntrestedCustomersMap notIntrested = NotIntrestedCustomersMap.getNotIntrestedCustomersMapInstance();
//    private NotAnsweredCutomersMap notAnswered = NotAnsweredCutomersMap.getNotAnsweredCustomersMapInstance();
//    private IntrestedCustomersMap intrested = IntrestedCustomersMap.getIntrestedCustomersMapInstance();
//
//    @GetMapping("/")
//    public String home(){
//        return "This is CustomerBase Application";
//    }
//
//    @PostMapping("/customers")
//    public Set<UserCustomerEntity> getCustomers(@RequestBody String userAuth) throws JsonProcessingException {
//
//        Set<UserCustomerEntity> customers = new HashSet<>();
//        System.out.println("/customers called");
//
//        User user = getUser(userAuth);
//        if(user == null) return customers;
//
//        customers.addAll(getFreshLeads(user, 5));
//
//        System.out.println(customers);
//
//        return customers;
//    }
//
//    @PostMapping("/addtointrested")
//    public void addToIntrested(@RequestBody String userCustomer) throws JsonProcessingException {
//        System.out.println("/addtointrested: " + userCustomer);
//        UserCustomerPojo userIdCustomerPojo = new ObjectMapper().readValue(userCustomer, UserCustomerPojo.class);
//        if(userIdCustomerPojo==null) return;
//        User user = registeredUsers.get(userIdCustomerPojo.getUser().getName());
//        Customer customer = userIdCustomerPojo.getCustomer();
//        if(user==null || customer == null) return;
//
//        intrested.putIfAbsent(user, new HashSet<>());
//        List<Customer> oldCustomers = intrested.get(user).stream()
//                .filter(ent -> ent.equals(customer)).collect(Collectors.toList());
//        for(Customer oldc : oldCustomers){
//            if(oldc==null) continue;
//            IntrestedCutsomer intrestedOldCutsomer = new IntrestedCutsomer(user, oldc);
//            if(oldc!=null && interestedCustomerRepo.findAll().contains(intrestedOldCutsomer))
//                interestedCustomerRepo.delete(intrestedOldCutsomer);
//        }
//        if(!intrested.get(user).add(customer)) {
//            intrested.get(user).remove(customer);
//            intrested.get(user).add(customer);
//        }
//        ;
//        IntrestedCutsomer intrestedCutsomer = new IntrestedCutsomer(user, customer);
//
//
//        interestedCustomerRepo.save( intrestedCutsomer);
//        System.out.println("/addtointrested: Saved " + userCustomer);
//
//
//
//        NotAnsweredCustomer notAnsweredCustomer = new NotAnsweredCustomer(user, customer);
//        FreshLead freshLead = new FreshLead(user, customer);
//        //remove from not answered and freshLeads maps
//        if(freshLeadsMap.containsKey(user)) freshLeadsMap.get(user).remove(customer);
//        if(notAnswered.containsKey(user)) notAnswered.get(user).remove(customer);
//
//        if(notAnsweredRepositry.findAll().contains(notAnsweredCustomer))
//            notAnsweredRepositry.delete(notAnsweredCustomer);
//        if(freshLeadsRepositry.findAll().contains(freshLead))
//            freshLeadsRepositry.delete( freshLead);
//    }
//
//    @PostMapping("/addtonotintrested")
//    public void addToNotIntrested( @RequestBody String userCustomer) throws JsonProcessingException {
//        System.out.println("/addtonotintrested: " + userCustomer);
//
//        UserIdCustomerPojo userIdCustomerPojo = new ObjectMapper().readValue(userCustomer, UserIdCustomerPojo.class);
//        if(userIdCustomerPojo==null) return;
//        User user = registeredUsers.get(userIdCustomerPojo.getUserId());
//        Customer customer = userIdCustomerPojo.getCustomer();
//        if(user==null || customer == null) return;
//
//        notIntrested.putIfAbsent(user,new HashSet<>());
//        notIntrested.get(user).add(customer);
//        NotIntrestedCustomer userCustomerEntity = new NotIntrestedCustomer(user, customer);
//        notIntrestedCutomerRepositry.save( userCustomerEntity);
//
//
//        NotAnsweredCustomer notAnsweredCustomer = new NotAnsweredCustomer(user, customer);
//        IntrestedCutsomer intrestedCutsomer = new IntrestedCutsomer(user, customer);
//        FreshLead freshLead = new FreshLead(user, customer);
//
//        if(freshLeadsMap.containsKey(user)) freshLeadsMap.get(user).remove(customer);
//        if(notAnswered.containsKey(user)) notAnswered.get(user).remove(customer);
//        if(intrested.containsKey(user)) intrested.get(user).remove(customer);
//
//        if(notAnsweredRepositry.findAll().contains(notAnsweredCustomer))
//            notAnsweredRepositry.delete(notAnsweredCustomer);
//        if(freshLeadsRepositry.findAll().contains(freshLead))
//            freshLeadsRepositry.delete( freshLead);
//        if(interestedCustomerRepo.findAll().contains(intrestedCutsomer))
//            interestedCustomerRepo.delete( intrestedCutsomer);
//        System.out.println("/addtonotintrested: Done!" + userCustomer);
//
//    }
//
//    @PostMapping("/addtonotanswerd")
//    public void addToNotAnswered(@RequestBody  String userCustomer) throws JsonProcessingException {
//        System.out.println("/addtonotanswerd: " + userCustomer);
//        UserIdCustomerPojo userIdCustomerPojo = new ObjectMapper().readValue(userCustomer, UserIdCustomerPojo.class);
//        if(userIdCustomerPojo==null) return;
//        User user = registeredUsers.get(userIdCustomerPojo.getUserId());
//        Customer customer = userIdCustomerPojo.getCustomer();
//        if(user==null || customer == null) return;
//
//        notAnswered.putIfAbsent(user, new HashSet<>());
//        notAnswered.get(user).add(customer);
//        NotAnsweredCustomer notAnsweredCustomer = new NotAnsweredCustomer(user, customer);
//        notAnsweredRepositry.save( notAnsweredCustomer);
//
//        FreshLead freshLead = new FreshLead(user, customer);
//        if(freshLeadsMap.containsKey(user)) freshLeadsMap.get(user).remove(customer);
//        if(freshLeadsRepositry.findAll().contains(freshLead))
//            freshLeadsRepositry.delete(  freshLead);
//        System.out.println("/addtonotanswerd: Done!" + userCustomer);
//
//    }
//
//
//    @PostMapping("/getintrested")
//    public Set<UserCustomerEntity> intrestedUsers(@RequestBody String userAuth) throws JsonProcessingException {
//        System.out.println("/getintrested: " + userAuth);
//
//        Set<UserCustomerEntity> customers = new HashSet<>();
//        User user = getUser(userAuth);
//        if(user == null) return customers;
//
//        if(user.getUserType().toLowerCase().equals("admin")){
//            customers.addAll(interestedCustomerRepo.findAll());
//        }
//        else{
//            if(intrested.containsKey(user)){
//                for(Customer customer :intrested.get(user)){
//                    customers.add(new UserCustomerEntity(user,customer));
//                }
//            }
//            else {
//                customers.addAll(interestedCustomerRepo.findAll().stream()
//                        .filter( entity -> entity.getUser().equals(user)).collect(Collectors.toList()));
//                intrested.putIfAbsent(user,new HashSet<>());
//                for(UserCustomerEntity customerEntity : customers){
//                    intrested.get(user).add(customerEntity.getCustomer());
//                }
//            }
//        }
//        System.out.println("/getintrested: " + customers);
//        return customers;
//    }
//
//    @PostMapping("/getnotanswerd")
//    public List<UserCustomerEntity> notAnsweredCustomers(@RequestBody String userAuth) throws JsonProcessingException {
//        System.out.println("/getnotanswerd: " + userAuth);
//
//        List<UserCustomerEntity> customers = new ArrayList<>();
//        User user = getUser(userAuth);
//        if(user == null) return customers;
//
//        if(notAnswered.containsKey(user)){
//            Set<Customer> customerSet = intrested.get(user);
//            for(Customer customer : customerSet){
//                customers.add(new UserCustomerEntity(user,customer));
//            }
//        }
//        else {
//            customers.addAll(notAnsweredRepositry.findAll().stream()
//                    .filter( entity -> entity.getUser().equals(user)).collect(Collectors.toList()));
//
//            notAnswered.putIfAbsent(user,new HashSet<>());
//            for(UserCustomerEntity customerEntity : customers)
//                notAnswered.get(user).add(customerEntity.getCustomer());
//        }
//        System.out.println("/getnotanswerd: " + customers);
//
//        return customers;
//    }
//
//
//    @PostMapping("/signin")
//    public User signin(@RequestBody String userAuth) throws JsonProcessingException {
//        System.out.println("/signin: " + userAuth);
//
//        User user = getUser(userAuth);
//        if(user == null) return null;
//        System.out.println("/signin: " + user);
//
//        return user;
//    }
//    @PostMapping("/signup")
//    public void signUp(@RequestBody User user){
//        if(!registeredUsers.containsKey(user.getName())){
//            registeredUsers.put(user.getName(),user);
//            userRepository.save(user);
//        }
//    }
//
//
//
//    private User getUser(String userAuth) throws JsonProcessingException {
//        String userId = new ObjectMapper().readValue(userAuth, UserId.class).getUserId();
//        if(registeredUsers.containsKey(userId)){
//            return (User) registeredUsers.get(userId);
//        }
//        return null;
//    }
//
//    private Set<UserCustomerEntity> getFreshLeads(User user, int rem) {
//
//        Set<UserCustomerEntity> customers = new HashSet<>();
//        if(freshLeadsMap.containsKey(user)){
//            for(Customer lead :  freshLeadsMap.get(user)){
//
//                customers.add(new UserCustomerEntity(user,lead));
//                if(customers.size()==rem) return customers;
//            }
//        }
//        rem = rem - customers.size();
//            freshLeadsMap.putIfAbsent(user,new HashSet<>());
//            for(int i=0;i<rem;i++){
//                if(!customerQueue.isEmpty()) {
//                    Customer customer = customerQueue.poll();
//                    freshLeadsMap.get(user).add(customer);
//                    freshLeadsRepositry.save(new FreshLead(user,customer));
//                    customers.add(new UserCustomerEntity(user,customer));
//                }
//            }
//        return customers;
//    }
//
//}
