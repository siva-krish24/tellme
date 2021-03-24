package com.tellme.demo.transactions;

import com.tellme.demo.users.Customer;
import com.tellme.demo.users.User;

import java.util.*;

public class NotIntrestedCustomersMap  {
    static Map<User, Set<Customer>> notIntrestedCustomersMap = new HashMap<>();
    private static NotIntrestedCustomersMap notIntrestedCustomersMapInstance = null;
    public static NotIntrestedCustomersMap getNotIntrestedCustomersMapInstance() {

        if (notIntrestedCustomersMapInstance == null) {
            notIntrestedCustomersMapInstance = new NotIntrestedCustomersMap();
        }
        return notIntrestedCustomersMapInstance;
    }
    public int size() {
        return notIntrestedCustomersMap.size();
    }

    public boolean isEmpty() {
        return notIntrestedCustomersMap.isEmpty();
    }

    public boolean containsKey(Object key) {
        return notIntrestedCustomersMap.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return notIntrestedCustomersMap.containsValue(value);
    }

    public Set<Customer> get(Object key) {
        return notIntrestedCustomersMap.get(key);
    }

    public Object put(Object key, Object value) {
        return notIntrestedCustomersMap.putIfAbsent((User) key,(Set<Customer>) value);
    }

    public Object remove(Object key) {
        return notIntrestedCustomersMap.remove(key);
    }


    public void putAll(Map m) {
        notIntrestedCustomersMap.putAll(m);
    }


    public void clear() {
        notIntrestedCustomersMap.clear();
    }

    public Set keySet() {
        return notIntrestedCustomersMap.keySet();
    }

    public Collection values() {
        return notIntrestedCustomersMap.values();
    }

    public Set<Map.Entry<User, Set<Customer>>> entrySet() {
        return notIntrestedCustomersMap.entrySet();
    }

    public void putIfAbsent(User user, HashSet<Customer> objects) {
        notIntrestedCustomersMap.putIfAbsent(user,objects);

    }
}
