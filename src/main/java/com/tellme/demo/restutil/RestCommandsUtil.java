//package com.tellme.demo.restutil;
//
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.tellme.demo.CustomerQueue;
//import com.tellme.demo.UserCustomerQueue;
//import com.tellme.demo.repositries.*;
//import com.tellme.demo.transactions.*;
//import com.tellme.demo.users.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDateTime;
//import java.util.*;
//import java.util.stream.Collectors;
//
//
//@RestController
//public class RestCommandsUtil {
//@Autowired
//UserRepository userRepository;
//@Autowired
//CustomerRepositry customerRepositry;
//@Autowired
//UserCustomerRepositry userCustomerRepo;
//
//    private CustomerQueue customerQueue = CustomerQueue.getStreamInstance();
//    private UserCustomerQueue userCustomerQueue = UserCustomerQueue.getStreamInstance();
//    private UserCustomerMap userCustomerMap = UserCustomerMap.getuserCustomersMapInstance();
//
//    private RegisteredUsers registeredUsers  = RegisteredUsers.getRegisteredUsersMapInstance();
//    String day  = LocalDateTime.now().toString().split("T")[0];
//    private static final int INTRESTED = 1;
//    private static final int NOTINTRESTED = 2;
//    private static final int NOTANSWERED = 3;
//    private static final int LOGIN = 4;
//    private static final int LEADCLOSED = 5;
//
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
//        System.out.println(day+ ":  /customers called ");
//
//        User user = getUser(userAuth);
//        if(user == null) return customers;
//
//        customers.addAll(getFreshLeads(user, 5));
//
//        System.out.println(day+":"+ customers);
//
//        return customers;
//    }
//
//    @PostMapping("/getonecustomer")
//    public UserCustomerEntity getOneCustomers(@RequestBody String userAuth) throws JsonProcessingException {
//        System.out.println(day+ ": /getonecustomer called");
//        User user = getUser(userAuth);
//        if(user == null) return new UserCustomerEntity();
//        return getCustomer(user);
//    }
//
//    @PostMapping("/addtointrested")
//    public void addToIntrested(@RequestBody String userCustomer) throws JsonProcessingException {
//        System.out.println("/addtointrested: " + userCustomer);
//
//        UserCustomerEntity userCustomerEntity = authenticate(userCustomer);
//        if(userCustomerEntity==null){
//            System.out.println("/addtointrested: " + "User Authentication failed");
//            return;
//        }
//
//        userCustomerEntity.setState(INTRESTED);
//        userCustomerEntity.setDate(day);
//
//        cleanupOldEntity(userCustomerEntity.getCustomer());
//        userCustomerQueue.add(userCustomerEntity);
//        userCustomerRepo.save(userCustomerEntity);
//
//        System.out.println("/addtointrested: Saved" + userCustomerEntity);
//
//    }
//
//    @PostMapping("/makelogin")
//    public void makeLogin(@RequestBody String userCustomer) throws JsonProcessingException {
//        System.out.println("/makelogin: " + userCustomer);
//
//        UserCustomerEntity userCustomerEntity = authenticate(userCustomer);
//        if(userCustomerEntity==null){
//            System.out.println("/makelogin: " + "User Authentication failed");
//            return;
//        }
//
//        userCustomerEntity.setState(LOGIN);
//        userCustomerEntity.setDate(day);
//
//        cleanupOldEntity(userCustomerEntity.getCustomer());
//        userCustomerQueue.add(userCustomerEntity);
//        userCustomerRepo.save(userCustomerEntity);
//
//        System.out.println("/makelogin: Saved" + userCustomerEntity);
//
//    }
//
//    @PostMapping("/closelead")
//    public void setLeadclosed(@RequestBody String userCustomer) throws JsonProcessingException {
//        System.out.println("/closelead: " + userCustomer);
//
//        UserCustomerEntity userCustomerEntity = authenticate(userCustomer);
//        if(userCustomerEntity==null){
//            System.out.println("/closelead: " + "User Authentication failed");
//            return;
//        }
//
//        userCustomerEntity.setState(LEADCLOSED);
//        userCustomerEntity.setDate(day);
//
//        cleanupOldEntity(userCustomerEntity.getCustomer());
//        userCustomerQueue.add(userCustomerEntity);
//        userCustomerRepo.save(userCustomerEntity);
//
//        System.out.println("/closelead: Saved" + userCustomerEntity);
//
//    }
//
//    @PostMapping("/addtonotintrested")
//    public void addToNotIntrested( @RequestBody String userCustomer) throws JsonProcessingException {
//        System.out.println("/addtonotintrested: " + userCustomer);
//
//        UserCustomerEntity userCustomerEntity = authenticate(userCustomer);
//        if(userCustomerEntity==null){
//            System.out.println("/addtonotintrested: " + "User Authentication failed");
//            return;
//        }
//        userCustomerEntity.setState(NOTINTRESTED);
//        userCustomerEntity.setDate(day);
//
//        cleanupOldEntity(userCustomerEntity.getCustomer());
//
//        userCustomerQueue.add(userCustomerEntity);
//        userCustomerRepo.save(userCustomerEntity);
//
//
//        System.out.println("/addtonotintrested: Done!" + userCustomer);
//
//    }
//
//    @PostMapping("/addtonotanswerd")
//    public void addToNotAnswered(@RequestBody  String userCustomer) throws JsonProcessingException {
//        System.out.println("/addtonotanswerd: " + userCustomer);
//
//        UserCustomerEntity userCustomerEntity = authenticate(userCustomer);
//        if(userCustomerEntity==null){
//            System.out.println("/addtonotanswerd: " + "User Authentication failed");
//            return;
//        }
//
//        userCustomerEntity.setState(NOTANSWERED);
//        userCustomerEntity.setDate(day);
//
//        cleanupOldEntity(userCustomerEntity.getCustomer());
//
//        userCustomerQueue.add(userCustomerEntity);
//        userCustomerRepo.save(userCustomerEntity);
//        System.out.println("/addtonotanswerd: Done!" + userCustomer);
//
//    }
//
//
//    @PostMapping("/getintrested")
//    public List<UserCustomerEntity> intrestedUsers(@RequestBody String userAuth) throws JsonProcessingException {
//        System.out.println("/getintrested: " + userAuth);
//
//        List<UserCustomerEntity> customers = new ArrayList<>();
//        User user = getUser(userAuth);
//        if(user == null) return customers;
//
//        customers.addAll(userCustomerQueue.getstream()
//                .filter(entity -> entity.getUser()
//                        .equals(user) && entity.getState()==INTRESTED).collect(Collectors.toList()));
//
//
//
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
//        customers.addAll(userCustomerQueue.getstream()
//                .filter(entity -> entity.getUser()
//                        .equals(user) && entity.getState()==NOTANSWERED).collect(Collectors.toList()));
//
//        System.out.println("/getnotanswerd: " + customers);
//
//        return customers;
//    }
//
//    @PostMapping("/getlogins")
//    public List<UserCustomerEntity> getLogins(@RequestBody String userAuth) throws JsonProcessingException {
//        System.out.println("/getlogins: " + userAuth);
//
//        List<UserCustomerEntity> customers = new ArrayList<>();
//        User user = getUser(userAuth);
//        if(user == null) return customers;
//
//        customers.addAll(userCustomerQueue.getstream()
//                .filter(entity -> entity.getUser()
//                        .equals(user) && entity.getState()==LOGIN).collect(Collectors.toList()));
//
//
//
//        System.out.println("/getlogins: " + customers);
//        return customers;
//    }
//
//    @PostMapping("/getleadsclosed")
//    public List<UserCustomerEntity> getLeadsClosed(@RequestBody String userAuth) throws JsonProcessingException {
//        System.out.println("/getleadsclosed: " + userAuth);
//
//        List<UserCustomerEntity> customers = new ArrayList<>();
//        User user = getUser(userAuth);
//        if(user == null) return customers;
//
//        customers.addAll(userCustomerQueue.getstream()
//                .filter(entity -> entity.getUser()
//                        .equals(user) && entity.getState()==LEADCLOSED).collect(Collectors.toList()));
//
//
//
//        System.out.println("/getleadsclosed: " + customers);
//        return customers;
//    }
//
//    @PostMapping("/getstats")
//    public States getStats(@RequestBody String userAuth) throws JsonProcessingException {
//        System.out.println("/getstats: " + userAuth);
//
//        UserDate pojo = new ObjectMapper().readValue(userAuth, UserDate.class);
//
//        User user = registeredUsers.get(pojo.getUserId());
//        int days = pojo.getDays();
//        if(user == null) return null;
//        States states = new States();
//
//        String then = LocalDateTime.now().minusDays(days).toString().split("T")[0];
//
//       states.setTotal(getCount(user,-1,then));
//        states.setFollowups(getCount(user,INTRESTED,then));
//        states.setNotanswered(getCount(user,NOTANSWERED,then));
//        states.setNotintrested(getCount(user,NOTINTRESTED,then));
//        states.setLogins(getCount(user,LOGIN,then));
//        states.setClosed(getCount(user,LEADCLOSED,then));
//
//
//        System.out.println("/getstats: " + states.toString());
//        return states;
//    }
//
//    @GetMapping("/getusers")
//    public List<User> getUsers(){
//        System.out.println("/getusers " );
//       List<User> users = new ArrayList<>();
//        users.addAll(registeredUsers.values());
//        System.out.println("/getusers " + users);
//                return users;
//    }
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
//    public void signUp(@RequestBody String userStr) throws JsonProcessingException {
//        System.out.println("/signup" + userStr);
//
//
//        User user = new ObjectMapper().readValue(userStr, User.class);
//        if(user==null) return;
//        if(!registeredUsers.containsKey(user.getName())){
//            registeredUsers.put(user.getName(),user);
//        }
//        else {
//            registeredUsers.remove(user.getName());
//            registeredUsers.put(user.getName(),user);
//        }
//        userRepository.save(user);
//        System.out.println("/signup" + userStr + " Success");
//    }
//
//    private UserCustomerEntity authenticate(String userCustomer) throws JsonProcessingException {
//        UserCustomerPojo userIdCustomerPojo = new ObjectMapper().readValue(userCustomer, UserCustomerPojo.class);
//        if(userIdCustomerPojo==null) return null;
//        User user = registeredUsers.get(userIdCustomerPojo.getUser().getName());
//        Customer customer = userIdCustomerPojo.getCustomer();
//        if(user==null || customer == null) return null;
//        return new UserCustomerEntity(user,customer);
//    }
//
//    private User getUser(String userAuth) throws JsonProcessingException {
//        String userId = new ObjectMapper().readValue(userAuth, UserId.class).getUserId();
//        if(registeredUsers.containsKey(userId)){
//            return (User) registeredUsers.get(userId);
//        }
//        return null;
//    }
//    private void cleanupOldEntity(Customer customer,int state){
//
//        List<UserCustomerEntity> oldeUserCustomerEntities = userCustomerQueue.getstream()
//                .filter(entity -> entity.getCustomer().equals(customer) && entity.getState()==state).collect(Collectors.toList());
//
//        for(UserCustomerEntity use : oldeUserCustomerEntities){
//            if(use==null) continue;
//            userCustomerQueue.remove(use);
//        }
//        for(UserCustomerEntity use : oldeUserCustomerEntities){
//            if(use==null ) continue;
//            if(userCustomerRepo.findAll().contains(use)) userCustomerRepo.delete(use);
//        }
//    }
//    private void cleanupOldEntity(Customer customer){
//        List<UserCustomerEntity> oldeUserCustomerEntities = userCustomerQueue.getstream()
//                .filter(entity -> entity.getCustomer().equals(customer)).collect(Collectors.toList());
//
//        for(UserCustomerEntity use : oldeUserCustomerEntities){
//            if(use==null) continue;
//            userCustomerQueue.remove(use);
//        }
//        for(UserCustomerEntity use : oldeUserCustomerEntities){
//            if(use==null ) continue;
//            if(userCustomerRepo.findAll().contains(use)) userCustomerRepo.delete(use);
//        }
//    }
//
//    private UserCustomerEntity getCustomer(User user){
//        if(!customerQueue.isEmpty()) {
//            Customer customer = customerQueue.poll();
//            UserCustomerEntity userCustomerEntity = new UserCustomerEntity(user,customer);
//            userCustomerEntity.setState(0);
//            userCustomerEntity.setDate(day);
//            cleanupOldEntity(customer,0);
//            userCustomerRepo.save(userCustomerEntity);
//            userCustomerQueue.add(userCustomerEntity);
//            return userCustomerEntity;
//        }
//        return new UserCustomerEntity();
//    }
//    private List<UserCustomerEntity> getFreshLeads(User user, int rem) {
//
//        List<UserCustomerEntity> customers = new ArrayList<>();
//        if(userCustomerMap.containsKey(user)){
//            customers.addAll(userCustomerMap.get(user).stream().filter(entity -> entity.getState()==0)
//                    .collect(Collectors.toList()));
//        }
//       rem = 5-customers.size();
//            for(int i=0;i<rem;i++){
//                if(!customerQueue.isEmpty()) {
//                    Customer customer = customerQueue.poll();
//                    UserCustomerEntity userCustomerEntity = new UserCustomerEntity(user,customer);
//                    userCustomerEntity.setState(0);
//                    userCustomerEntity.setDate(day);
//                    cleanupOldEntity(user,customer,0);
//                    userCustomerRepo.save(userCustomerEntity);
//                    userCustomerMap.putIfAbsent(user, new HashSet<>());
//                    userCustomerMap.get(user).add(userCustomerEntity);
//                    customers.add(userCustomerEntity);
//                }
//            }
//        return customers;
//    }
//
//    private void cleanupOldEntity(User user, Customer customer, int i) {
//        if(userCustomerMap.containsKey(user)){
//        List<UserCustomerEntity> oldeUserCustomerEntities = userCustomerMap.get(user).stream()
//                .filter(entity->entity.getCustomer().equals(customer) && entity.getState()==i).collect(Collectors.toList());
//        if(oldeUserCustomerEntities.size()==0) return;
//        for(UserCustomerEntity use : oldeUserCustomerEntities){
//            if(use==null) continue;
//            userCustomerMap.get(user).remove(use);
//        }
//        for(UserCustomerEntity use : oldeUserCustomerEntities){
//            if(use==null ) continue;
//            if(userCustomerRepo.findAll().contains(use)) userCustomerRepo.delete(use);
//        }
//        }
//    }
//
//    private int getCount(User user, int state, String dateTime){
//        if(state==-1)  return (int) userCustomerQueue.getstream()
//                .filter(entity -> entity.getUser()
//                        .equals(user) &&entity.getDate().equals(dateTime)).count();
//         return (int) userCustomerQueue.getstream()
//                .filter(entity -> entity.getUser()
//                        .equals(user)&& entity.getState()==state &&entity.getDate().equals(dateTime)).count();
//    }
//
//}
