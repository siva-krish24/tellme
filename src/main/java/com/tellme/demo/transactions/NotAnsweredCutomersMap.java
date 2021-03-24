package com.tellme.demo.transactions;

import com.tellme.demo.users.Customer;
import com.tellme.demo.users.User;

import java.util.*;

public class NotAnsweredCutomersMap  {
    static Map<User, Set<Customer>> notAnsweredCustomersMap = new HashMap<>();
    private static NotAnsweredCutomersMap notAnsweredCustomersMapInstance = null;
    public static NotAnsweredCutomersMap getNotAnsweredCustomersMapInstance() {

        if (notAnsweredCustomersMapInstance == null) {
            notAnsweredCustomersMapInstance = new NotAnsweredCutomersMap();
        }
        return notAnsweredCustomersMapInstance;
    }
    public int size() {
        return notAnsweredCustomersMap.size();
    }

    public boolean isEmpty() {
        return notAnsweredCustomersMap.isEmpty();
    }

    public boolean containsKey(Object key) {
        return notAnsweredCustomersMap.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return notAnsweredCustomersMap.containsValue(value);
    }

    public Set<Customer> get(Object key) {
        return notAnsweredCustomersMap.get(key);
    }

    public Object put(Object key, Object value) {
        return notAnsweredCustomersMap.putIfAbsent((User) key,(Set<Customer>) value);
    }

    public Object remove(Object key) {
        return notAnsweredCustomersMap.remove(key);
    }


    public void putAll(Map m) {
        notAnsweredCustomersMap.putAll(m);
    }


    public void clear() {
        notAnsweredCustomersMap.clear();
    }

    public Set keySet() {
        return notAnsweredCustomersMap.keySet();
    }

    public Collection values() {
        return notAnsweredCustomersMap.values();
    }

    public Set<Map.Entry<User, Set<Customer>>> entrySet() {
        return notAnsweredCustomersMap.entrySet();
    }

    public void putIfAbsent(User user, HashSet<Customer> objects) {
        notAnsweredCustomersMap.putIfAbsent(user,objects);

    }
}
