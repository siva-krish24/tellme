package com.tellme.demo;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tellme.demo.users.Customer;
import com.tellme.demo.users.CustomerMeta;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MongoUtil {

    public static void eraseDb(MongoRepository mongoRepository) {
        mongoRepository.deleteAll();
    }

    public static void putCustomerRecordsInMongo(MongoRepository customerRepositry) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File from = new File("/Users/sguntreddi/AndroidStudioProjects/FireBase/csvjson.json");
            JsonNode masterJSON = mapper.readTree(from);
            JsonNode customers = masterJSON.get("customers");
            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            for (int id = 1; id < 500; id++) {

                Customer customer = new Customer();
                CustomerMeta sample = new ObjectMapper().readValue(customers.get(String.valueOf(id)).toString(), CustomerMeta.class);
                customer.setId(String.valueOf(id));
                customer.setName(sample.getCUSTOMERNAME());
                customer.setMobile(sample.getCONTACT());
                customer.setVarient(sample.getVARIANT());
                customer.setHypo(sample.getHYPO());
                customerRepositry.save(customer);
                System.out.println("User " + customer.getName() + "with id:  " + id + "added");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addDataFromCsv(MongoRepository customerRepositry) throws FileNotFoundException {
        try {

            Pattern pattern = Pattern.compile(",");
            File dir = new File("/Users/sguntreddi/Documents/svloanshub/CSVDATA/toUpdate");

            for (File csvFile : dir.listFiles()) {
//            String csvFile = "/Users/sguntreddi/Documents/svloanshub/CSVDATA/niharikia2015.csv";
            BufferedReader in;

            in = new BufferedReader(new FileReader(csvFile));
                AtomicInteger id = new AtomicInteger();
            String []head = in.readLine().split(",");
           Map<String, Integer> map = new HashMap<>();
           int it =0;
//           for(String s : head){
//               map.put(s,it++);
//           }

            List<CustomerMeta> customerMetaList = in.lines().map(line -> {
                String[] x = pattern.split(line);
                CustomerMeta cs =  new CustomerMeta();
                cs.setDefaultValues();
                cs.setId(String.valueOf(id.getAndIncrement()));
                cs.setCONTACT(x[x.length-1]);
                cs.setCUSTOMERNAME(x[4]);
                cs.setADDRESS1(x[5]);
                cs.setADDRESS2(x[6]);
                cs.setADDRESS3(x[7]);
                cs.setCITY(x[8]);
                cs.setENGINCHAS(x[3]);
                cs.setHYPO(x[10]);
                cs.setMODEL(x[1]);
                cs.setSTATE(x[9]);
                cs.setVARIANT(x[2]);
                return cs;
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
               if(customer!=null && customer.getMobile()!=null && !customer.getMobile().equals("")) customerRepositry.save(customer);
            });
            mapper.writeValue(System.out, customerMetaList);
            }
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
//            System.out.println("s is: " + s);
            if(s.isEmpty()|| s==null ||s.equals("")) continue;
            if(Character.isDigit(s.charAt(0))&&onlyDigits(s)|| s.charAt(0) == '+'){
                if(s.length()>9 && s.length()<14) return s;
            }
        }
        return "";
    }
   private static boolean onlyDigits(String str)
    {
        // Regex to check string
        // contains only digits
        String regex = "[0-9]+";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the string is empty
        // return false
        if (str == null) {
            return false;
        }

        // Find match between given string
        // and regular expression
        // using Pattern.matcher()
        Matcher m = p.matcher(str);

        // Return if the string
        // matched the ReGex
        return m.matches();
    }
}
