package com.lcydl.whale;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.lcydl.whale.common.mapper")
@SpringBootApplication
public class WhaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhaleApplication.class, args);
        System.out.println(
                " ┏┓         ┏┓                     \n" +
                "┏┛┻━━━━━━━━━┛┻┓                    \n" +
                "┃             ┃                    \n" +
                "┃       ━     ┃                    \n" +
                "┃   ┳┛   ┗┳   ┃                    \n" +
                "┃             ┃                    \n" +
                "┃       ┻     ┃                    \n" +
                "┃             ┃                    \n" +
                "┗━┓         ┏━┛                    \n" +
                "  ┃         ┃                      \n" +
                "  ┃         ┃                      \n" +
                "  ┃         ┗━┓                    \n" +
                "  ┃           ┣┓                   \n" +
                "  ┃           ┏┛                   \n" +
                "  ┗┓┓┏━━━━━┳┓┏┛                    \n" +
                "   ┃┫┫     ┃┫┫                     \n" +
                "   ┗┻┛     ┗┻┛                     \n" +
                "  神兽保佑|永无BUG                   ");
    }
}
