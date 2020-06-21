package com.emor.dbfinal;

//import org.junit.Test;
import com.emor.dbfinal.dao.StudentMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DbfinalApplicationTests {

//    @Autowired
//    StudentMapper studentMapper;
//    @Autowired
//    RedisTemplate redisTemplate; // k - v
//
//    @Autowired
//    StringRedisTemplate stringRedisTemplate;  // strings

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    public void contextLoads() throws Exception{
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

        mimeMessageHelper.setSubject("通知");
        mimeMessageHelper.setText("<h1>你号没了</h>",true);
        mimeMessageHelper.addAttachment("emor",new File("D:\\Users\\Emor\\OneDrive - hrbeu.edu.cn\\桌面\\2018041127-钟智杰-软件工程基础作业.doc"));
        mimeMessageHelper.setTo("523166430@qq.com");
        mimeMessageHelper.setFrom("1227117695@qq.com");
        mailSender.send(mimeMessage);
    }

}
