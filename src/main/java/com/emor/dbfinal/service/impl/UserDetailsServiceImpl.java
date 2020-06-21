//package com.emor.dbfinal.service.impl;
//
//import com.emor.dbfinal.dao.UserMapper;
//import com.emor.dbfinal.entity.User;
//
//import com.emor.dbfinal.entity.UserDetailsImpl;
//import com.emor.dbfinal.entity.UserExample;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.Cipher;
//import java.util.List;
//
//
//@Component
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    UserMapper userMapper;
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        System.out.println("loadUserByUsername"+s);
//        UserExample userExample =new UserExample();
//        UserExample.Criteria criteria = userExample.createCriteria();
//        criteria.andUsernameEqualTo(s);
//        List<User> user = userMapper.selectByExample(userExample);//.get(0);
//        System.out.println(user);
//        if(user==null){
//            throw new UsernameNotFoundException("没有该用户");
//        }
//        return new UserDetailsImpl(user.get(0));
//    }
//}
