package com.tellme.demo.transactions;

import com.tellme.demo.users.User;
import java.util.*;

public class RegisteredUsers implements Map {
    static Map<String, User> registeredUserMap = new HashMap<>();
    private static RegisteredUsers registeredUsersMapInstance = null;
    public static RegisteredUsers getRegisteredUsersMapInstance() {

        if (registeredUsersMapInstance == null) {
            registeredUsersMapInstance = new RegisteredUsers();
        }
        return registeredUsersMapInstance;
    }
    @Override
    public int size() {
        return registeredUserMap.size();
    }

    @Override
    public boolean isEmpty() {
        return registeredUserMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return registeredUserMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return registeredUserMap.containsValue(value);
    }

    @Override
    public User get(Object key) {
        return registeredUserMap.get(key);
    }

    @Override
    public Object put(Object key, Object value) {
        return registeredUserMap.putIfAbsent((String) key, (User)value);
    }

    @Override
    public Object remove(Object key) {
        return registeredUserMap.remove(key);
    }

    @Override
    public void putAll(Map m) {
        registeredUserMap.putAll(m);
    }

    @Override
    public void clear() {
        registeredUserMap.clear();
    }

    @Override
    public Set keySet() {
        return registeredUserMap.keySet();
    }

    @Override
    public Collection values() {
        return registeredUserMap.values();
    }

    @Override
    public Set<Map.Entry<String, User>> entrySet() {
        return registeredUserMap.entrySet();
    }

}
