package com.tellme.demo;

import com.tellme.demo.transactions.AuthMap;
import com.tellme.demo.transactions.RegisteredUsers;
import com.tellme.demo.transactions.UserCustomerMap;

public class ResourceUtil {
    private CustomerQueue customerQueue = CustomerQueue.getStreamInstance();
    private UserCustomerQueue intrestedCustomerQueue = UserCustomerQueue.getStreamInstance();
    private AuthMap authMap = AuthMap.getStreamInstance();
    private UserCustomerMap userToCutomerMap = UserCustomerMap.getuserCustomersMapInstance();
    private RegisteredUsers registeredUsers  = RegisteredUsers.getRegisteredUsersMapInstance();
}
