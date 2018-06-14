package com.lcydl.whale.manage.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class LoginUtil {

    private static final String SALT = "aCk7Kc012rH6";  //盐值
    private static final String ALGORITH_NAME = "md5";
    private static final int HASH_ITERATIONS = 2;

    public static String randomStr(int len){

        if(len == 0) {
            return "";
        }
        int a = (int) ( Math.random () * 3 );

        if (a == 0){
            return ( (int) ( Math.random () * 10 ) ) + randomStr (len - 1);
        }else if (a == 1){
            return ( (char) ( (int) ( Math.random () * 26 ) + 65 ) ) + randomStr (len - 1);
        }else{
            return ( (char) ( (int) ( Math.random () * 26 ) + 97 ) ) + randomStr (len - 1);

        }
    }

    public static String encrypt(String username, String pswd) {
        return new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(username + SALT),
                HASH_ITERATIONS).toHex();
    }


}
