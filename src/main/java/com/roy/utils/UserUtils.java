package com.roy.utils;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by sang on 2017/12/30.
 */
public class UserUtils {
    public static Object getCurrentUser() {

        System.out.println("currentUser"+SecurityContextHolder.getContext().getAuthentication().getPrincipal());

       Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(object != null) {

        }
        return object;
    }
}
