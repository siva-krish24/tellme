package com.tellme.demo.users;

import com.fasterxml.jackson.annotation.*;

import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "userId",
        "days"
})
public class UserDate {

    @JsonProperty("userId")
    private String userId;
    @JsonProperty("days")
    private int days;


    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("date")
    public int getDays() {
        return days;
    }

    @JsonProperty("days")
    public void setDays(int days) {
        this.days = days;
    }




}