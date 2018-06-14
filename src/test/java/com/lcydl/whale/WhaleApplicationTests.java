package com.lcydl.whale;

import com.lcydl.whale.manage.util.LoginUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhaleApplicationTests {

    private static final String SALT = "aCk7Kc012rH6";  //盐值
    private static final String ALGORITH_NAME = "md5";
    private static final int HASH_ITERATIONS = 2;

    @Test
    public void contextLoads() {

        String salt = LoginUtil.randomStr(12);
        String password = "admin";
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        String newPwd = DigestUtils.md5DigestAsHex((password + salt).getBytes());
        System.out.println("盐值----> " + salt);
        System.out.println("新密码----> " + newPwd);
    }

    @Test
    public void encrypt() {
        String username = "admin";
        String password = "admin";
        String s = new SimpleHash(ALGORITH_NAME, password, ByteSource.Util.bytes(username + SALT),
                HASH_ITERATIONS).toHex();
        System.out.println(s);
    }

}
