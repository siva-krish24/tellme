package com.tellme.demo.transactions;

import com.tellme.demo.users.Customer;
import com.tellme.demo.users.User;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FollowUpCustomersMap implements CustomorStateMap {
    static Map<User, Set<Customer>> followUpCustomersMap = new HashMap<>();
    private static CustomorStateMap followUpCustomersMapInstance = null;
    public static CustomorStateMap getfollowUpCustomersMapInstance() {

        if (followUpCustomersMapInstance == null) {
            followUpCustomersMapInstance = new FollowUpCustomersMap();
        }
        return followUpCustomersMapInstance;
    }
    @Override
    public int size() {
        return followUpCustomersMap.size();
    }

    @Override
    public boolean isEmpty() {
        return followUpCustomersMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return followUpCustomersMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return followUpCustomersMap.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return followUpCustomersMap.get(key);
    }

    @Override
    public Object put(Object key, Object value) {
        return followUpCustomersMap.putIfAbsent((User) key,(Set<Customer>) value);
    }

    @Override
    public Object remove(Object key) {
        return followUpCustomersMap.remove(key);
    }

    @Override
    public void putAll(Map m) {
        followUpCustomersMap.putAll(m);
    }

    @Override
    public void clear() {
        followUpCustomersMap.clear();
    }

    @Override
    public Set keySet() {
        return followUpCustomersMap.keySet();
    }

    @Override
    public Collection values() {
        return followUpCustomersMap.values();
    }

    @Override
    public Set<Entry<User, Set<Customer>>> entrySet() {
        return followUpCustomersMap.entrySet();
    }
}
