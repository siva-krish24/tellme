package com.tellme.demo;


import com.tellme.demo.repositries.*;
import com.tellme.demo.transactions.*;
import com.tellme.demo.users.User;
import com.tellme.demo.users.UserCustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.io.FileNotFoundException;
import java.util.HashSet;

import static com.tellme.demo.MongoUtil.addDataFromCsv;
import static com.tellme.demo.MongoUtil.putCustomerRecordsInMongo;


@SpringBootApplication(scanBasePackages={
		"com.tellme.demo"})
public class TellMeService implements CommandLineRunner {
	@Autowired
	UserRepository userRepository;
	@Autowired
	CustomerRepositry customerRepositry;
	@Autowired
	UserCustomerRepositry userCustomerRepositry;


	CustomerQueue customersQuee=CustomerQueue.getStreamInstance();
	RegisteredUsers registeredUsers =  RegisteredUsers.getRegisteredUsersMapInstance();
	UserCustomerMap userCustomerMap = UserCustomerMap.getuserCustomersMapInstance();


	public static void main(String[] args) {
		SpringApplication.run(TellMeService.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		overWriteCustomerData();
//		listAll(customerRepositry);
		loadRegisteredUsers();
		loadToqueue();
		loadUserCustomers();


	}

	private void loadUserCustomers() {
		for(UserCustomerEntity userCustomerEntity : userCustomerRepositry.findAll()){
			userCustomerMap.putIfAbsent(userCustomerEntity.getUser(),new HashSet<>());
			userCustomerMap.get(userCustomerEntity.getUser()).add(userCustomerEntity);
		}
	}


	private void loadRegisteredUsers() {
		for(User user : userRepository.findAll()){
			registeredUsers.put(user.getName(),user);
		}
	}




	private void loadToqueue() {
		if(customersQuee.getTotalSize()<100){
			customersQuee.addAll(customerRepositry.findAll());
		}
	}

	public  void overWriteCustomerData() throws FileNotFoundException {
		System.out.println("overwriting sample data");
//		customerRepositry.deleteAll();
		addDataFromCsv(customerRepositry);

	}
	public  void uploadCustomerData() {
		System.out.println("Adding sample data");

		putCustomerRecordsInMongo(customerRepositry);

	}
	public void listAll(MongoRepository repository) {
		System.out.println("Listing sample data");
		repository.findAll().forEach(u -> System.out.println(u.toString()));
	}
}
