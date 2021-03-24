package com.tellme.demo.transactions;

import com.tellme.demo.users.UserAuth;
import com.tellme.demo.users.User;

import java.util.*;

public class AuthMap {
    static Map<UserAuth, User> userMap = new HashMap<>();
    private static AuthMap userSetInstance = null;
    public static AuthMap getStreamInstance() {

        if (userSetInstance == null) {
            userSetInstance = new AuthMap();
        }
        return userSetInstance;
    }

    public Map<UserAuth, User> get() {
        return userMap;
    }

    public void add(UserAuth value, User user) {
            userMap.put(value,user);
    }


    // Removes a single instance of the specified element from this collection
    public void remove(UserAuth value) {
        synchronized (userMap) {
            userMap.remove(value);
        }
    }

    // Retrieves and removes the head of this queue, or returns null if this
    // queue is empty.
    public boolean find(UserAuth userAuth){
        return userMap.containsKey(userAuth);
    }
    public User getUser(UserAuth userAuth){
        return userMap.containsKey(userAuth)?userMap.get(userAuth): new User();
    }

    // Returns true if this collection contains no elements
    public boolean isEmpty() {
        return userMap.isEmpty();
    }


    public int getTotalSize() {
        return userMap.size();
    }
}
