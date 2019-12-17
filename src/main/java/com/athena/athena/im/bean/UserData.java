package com.athena.athena.im.bean;

import java.util.HashMap;
import java.util.Map;

public class UserData {

    public static final Map<Long,User> USER_MAP = new HashMap<>();

    static {
        USER_MAP.put(1000L, User.builder().id(1000L).username("AntzUhl").build());
        USER_MAP.put(2000L, User.builder().id(2000L).username("Athena").build());
    }

}
