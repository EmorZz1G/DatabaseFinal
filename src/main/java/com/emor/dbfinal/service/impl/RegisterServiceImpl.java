package com.emor.dbfinal.service.impl;

import com.emor.dbfinal.dao.UserMapper;
import com.emor.dbfinal.entity.User;
import com.emor.dbfinal.entity.UserExample;
import com.emor.dbfinal.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void register(User user) throws Exception{
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        String username = user.getUsername();
        if(username==null||username.isEmpty()){
            throw new Exception("输入有误");
        }
        criteria.andUsernameEqualTo(user.getUsername());
        int cnt = userMapper.countByExample(userExample);
        if(cnt>0){
            throw new Exception("用户已存在");
        }
        if(user.getPassword()==null||user.getPassword().isEmpty()){
            throw new Exception("输入有误");
        }
        user.setMyrole("student");
        userMapper.insertSelective(user);
    }
}
