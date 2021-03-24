package com.data;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tellme.demo.users.Customer;
import com.tellme.demo.users.CustomerMeta;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CsvToJson {
    public static void main(String[] args) throws IOException {

            try {

                Pattern pattern = Pattern.compile(",");
                String csvFile = "/Users/sguntreddi/Documents/svloanshub/CSVDATA/mumbaimaruthialllocation2018.csv";
                BufferedReader in;

                in = new BufferedReader(new FileReader(csvFile));
                AtomicInteger id = new AtomicInteger();
                List<CustomerMeta> customerMetaList = in.lines().skip(1).map(line -> {
                    String[] x = pattern.split(line);
                    String mobile =  getmobile(x);
                    return x.length > 11&&!mobile.equals("") ? new CustomerMeta(String.valueOf(id.getAndIncrement()), x[1], x[2], x[3], x[4], x[5],
                            x[6], x[7], x[8], x[9], mobile, x[11]) : new CustomerMeta();
                }).collect(Collectors.toList());

                ObjectMapper mapper = new ObjectMapper();
                mapper.enable(SerializationFeature.INDENT_OUTPUT);
                customerMetaList.stream().forEach((sample) -> {
                    Customer customer = new Customer();
                    customer.setId(sample.getId());
                    customer.setName(sample.getCUSTOMERNAME());
                    customer.setMobile(sample.getCONTACT());
                    customer.setVarient(sample.getVARIANT());
                    customer.setHypo(sample.getHYPO());
                    System.out.println(customer.toString());
                });
                mapper.writeValue(System.out, customerMetaList);
//            System.out.println("User " + customer.getName() + "with id:  " + id + "added");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (JsonGenerationException ex) {
                ex.printStackTrace();
            } catch (JsonMappingException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    private static String getmobile(String[] x) {
        for(String s : x){
            System.out.println("s is: " + s);
            if(s.isEmpty()|| s==null ||s.equals("")) continue;
            if(Character.isDigit(s.charAt(0)) || s.charAt(0) == '+'){
                 if(s.length()>9 && s.length()<14) return s;
            }
        }
        return "";
    }

}
