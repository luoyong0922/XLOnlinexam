package com.roy.utils;

import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashSet;
import java.util.Set;


public class PasswordHashTest {

    @Test
    public void createHash() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String p = "abc3983shel9%35#$";
        int i = 10;
        System.out.println("同一密码加密十次的结果");
        Set encryptedSet = new HashSet();
        while (i > 0) {
            String h = PasswordHash.createHash(p);
            encryptedSet.add(h);
            System.out.println(h);
            i--;
        }
        if(encryptedSet.size() == 10){
            System.out.println("加密结果没有出现重复值(*^_^*)");
        }else {
            System.out.println("加密结果出现了重复值/(ㄒoㄒ)/~~");
        }

    }

    @Test
    public void validatePassword() throws InvalidKeySpecException, NoSuchAlgorithmException {

        String errorKey = "1024:4f44820b0a0e2806dff8b44d03456dc3e43b3f33666ea4c6:bc37d9e0bdef2fa168a3b61ce5b06c47ab83d2db6954a5a6";

        String password = "fish==bear?true:false";

        String correctKey1 = PasswordHash.createHash(password);
        String correctKey2 = PasswordHash.createHash(password);

        boolean result = false;
        // 校验正确的密码
        result = PasswordHash.validatePassword(password,correctKey1);
        Assert.assertTrue(result);
        // 校验错误的密码
        result = !PasswordHash.validatePassword(password,errorKey);
        Assert.assertTrue(result);
        // 验证两次加密的密码是否相等
        result = !correctKey1.equals(correctKey2);
        Assert.assertTrue(result);

        if (result) {
            System.out.println("密码验证成功(≧∀≦)ゞ");
        } else {
            System.out.println("密码验证失败/(ㄒoㄒ)/~~");
        }

    }

}