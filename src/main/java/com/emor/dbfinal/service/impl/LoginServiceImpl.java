package com.emor.dbfinal.service.impl;

import com.emor.dbfinal.dao.UserMapper;
import com.emor.dbfinal.entity.User;
import com.emor.dbfinal.entity.UserExample;
import com.emor.dbfinal.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;


@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserMapper userMapper;

    public boolean login(User user,Map<String,Object> resMap){
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());
        criteria.andPasswordEqualTo(user.getPassword());
        List<User> resUser = userMapper.selectByExample(example);
        System.out.println(resUser);
        if(resUser.size()==1){
            resMap.put("user",resUser.get(0));
            resMap.put("msg","登录成功");
            return true;
        }else if(resUser.size()==0){
            resMap.put("msg","用户名或密码错误");
            return false;
        }else{
            resMap.put("msg","查询出错");
            return false;
        }
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
