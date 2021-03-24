package com.tellme.demo;

import java.time.LocalDateTime;

public class Constants {
    public static final String DAY  = LocalDateTime.now().toString().split("T")[0];
    public static final int INTRESTED = 1;
    public static final int NOTINTRESTED = 2;
    public static final int NOTANSWERED = 3;
    public static final int LOGIN = 4;
    public static final int LEADCLOSED = 5;
}
