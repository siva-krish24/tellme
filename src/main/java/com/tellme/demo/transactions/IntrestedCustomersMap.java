package com.tellme.demo.transactions;

import com.tellme.demo.users.Customer;
import com.tellme.demo.users.User;

import java.util.*;

public class IntrestedCustomersMap {
    static Map<User, Set<Customer>> intrestedCustomersMap = new HashMap<>();
    private static IntrestedCustomersMap intrestedCustomersMapInstance = null;
    public static IntrestedCustomersMap getIntrestedCustomersMapInstance() {

        if (intrestedCustomersMapInstance == null) {
            intrestedCustomersMapInstance = new IntrestedCustomersMap();
        }
        return intrestedCustomersMapInstance;
    }
    public int size() {
        return intrestedCustomersMap.size();
    }

    public boolean isEmpty() {
        return intrestedCustomersMap.isEmpty();
    }

    public boolean containsKey(Object key) {
        return intrestedCustomersMap.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return intrestedCustomersMap.containsValue(value);
    }

    public Set<Customer> get(Object key) {
        return intrestedCustomersMap.get(key);
    }

    public Object put(Object key, Object value) {
        return intrestedCustomersMap.putIfAbsent((User) key,(Set<Customer>) value);
    }

    public Object remove(Object key) {
        return intrestedCustomersMap.remove(key);
    }


    public void putAll(Map m) {
        intrestedCustomersMap.putAll(m);
    }


    public void clear() {
        intrestedCustomersMap.clear();
    }

    public Set keySet() {
        return intrestedCustomersMap.keySet();
    }

    public Collection values() {
        return intrestedCustomersMap.values();
    }

    public Set<Map.Entry<User, Set<Customer>>> entrySet() {
        return intrestedCustomersMap.entrySet();
    }

    public void putIfAbsent(User user, HashSet<Customer> objects) {
        intrestedCustomersMap.putIfAbsent(user,objects);

    }
}
