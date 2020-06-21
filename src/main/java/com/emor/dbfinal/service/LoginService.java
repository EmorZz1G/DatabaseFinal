package com.emor.dbfinal.service;

import com.emor.dbfinal.entity.User;

import java.util.Map;

public interface LoginService {
    boolean login(User user, Map<String,Object> resMap);
    int updateUser(User user);
}