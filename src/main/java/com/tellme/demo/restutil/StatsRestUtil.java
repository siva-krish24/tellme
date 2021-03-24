package com.tellme.demo.restutil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tellme.demo.transactions.RegisteredUsers;
import com.tellme.demo.transactions.States;
import com.tellme.demo.users.User;
import com.tellme.demo.users.UserDate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static com.tellme.demo.Constants.*;
import static com.tellme.demo.restutil.CommonRestUtil.getCount;


@RestController
public class StatsRestUtil {
    private static RegisteredUsers registeredUsers  = RegisteredUsers.getRegisteredUsersMapInstance();


    @PostMapping("/getstats")
    public States getStats(@RequestBody String userAuth) throws JsonProcessingException {
        System.out.println("/getstats: " + userAuth);

        UserDate pojo = new ObjectMapper().readValue(userAuth, UserDate.class);

        User user = registeredUsers.get(pojo.getUserId());
        int days = pojo.getDays();
        if(user == null) return null;
        States states = new States();

        String then = LocalDateTime.now().minusDays(days).toString().split("T")[0];

        int intrested = getCount(user,INTRESTED,then,days);
        int notIntrested = getCount(user,NOTINTRESTED,then,days);
        int notAnswered = getCount(user,NOTANSWERED,then,days);
        int logins = getCount(user,LOGIN,then,days);
        int leadClosed = getCount(user,LEADCLOSED,then,days);

        states.setTotal(intrested+notAnswered+notIntrested+logins+leadClosed);
        states.setFollowups(intrested);
        states.setNotanswered(notAnswered);
        states.setNotintrested(notIntrested);
        states.setLogins(logins);
        states.setClosed(leadClosed);

        System.out.println("/getstats: " + states.toString());
        return states;
    }


}
