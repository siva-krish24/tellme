package com.tellme.demo.transactions;

import com.tellme.demo.users.Customer;
import com.tellme.demo.users.User;
import com.tellme.demo.users.UserCustomerEntity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class UserCustomerMap {
    static Map<User, Set<UserCustomerEntity>> userToCustomersMap = new ConcurrentHashMap<>();
    private static UserCustomerMap userToCustomersMapInstance = null;
    public static UserCustomerMap getuserCustomersMapInstance() {

        if (userToCustomersMapInstance == null) {
            userToCustomersMapInstance = new UserCustomerMap();
        }
        return userToCustomersMapInstance;
    }

    public int size() {
        return userToCustomersMap.size();
    }


    public boolean isEmpty() {
        return userToCustomersMap.isEmpty();
    }


    public boolean containsKey(Object key) {
        return userToCustomersMap.containsKey(key);
    }


    public boolean containsValue(Object value) {
        return userToCustomersMap.containsValue(value);
    }


    public Set<UserCustomerEntity> get(Object key) {
        return userToCustomersMap.get(key);
    }


    public Object put(Object key, Object value) {
        return userToCustomersMap.putIfAbsent((User) key,(Set<UserCustomerEntity>) value);
    }


    public Object remove(Object key) {
        return userToCustomersMap.remove(key);
    }


    public void putAll(Map m) {
        userToCustomersMap.putAll(m);
    }


    public void clear() {
        userToCustomersMap.clear();
    }


    public Set keySet() {
        return userToCustomersMap.keySet();
    }

    public Collection values() {
        return userToCustomersMap.values();
    }

    public Set<Map.Entry<User, Set<UserCustomerEntity>>> entrySet() {
        return userToCustomersMap.entrySet();
    }

    public void putIfAbsent(User user, HashSet<UserCustomerEntity> objects) {
        userToCustomersMap.putIfAbsent(user,objects);
    }
}
