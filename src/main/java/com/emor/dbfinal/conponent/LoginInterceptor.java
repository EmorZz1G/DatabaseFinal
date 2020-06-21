package com.emor.dbfinal.conponent;

import com.emor.dbfinal.dao.StudentMapper;
import com.emor.dbfinal.entity.Student;
import com.emor.dbfinal.entity.Teacher;
import com.emor.dbfinal.entity.User;
import com.emor.dbfinal.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginInterceptor implements HandlerInterceptor {
    static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    @Autowired
    StudentMapper studentMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        logger.info("请求的URI{},method {}",uri,request.getMethod());
        System.out.println(uri);
        if(!uri.startsWith("/tcr")&&!uri.startsWith("/stud")){
            return true;
        }else {
            User user = (User) request.getSession().getAttribute("loginUser");
            logger.info("interceptor user {}",user);
            if(user!=null){
                String myrole = user.getMyrole();
                Integer fid = user.getFid();
                if(fid==null){
                    request.setAttribute("msg","请先设置个人信息");
                    request.getRequestDispatcher("/index").forward(request,response);
                    logger.info("转发");
                    return false;
                }
                if(myrole==null||myrole.trim()==""){
                    request.getSession().setAttribute("error_msg","请先设置个人信息");
                    return true;
                }else if (myrole.equals("student")&&uri.startsWith("/stud")){
                    logger.info("正常访问{}",user);
                    return true;
                }else if(myrole.equals("teacher")&&uri.startsWith("/tcr")){
                    logger.info("正常访问{}",user);
                    return true;
                }else {
                    request.setAttribute("msg","没有访问权限！");
                    request.getRequestDispatcher("/index").forward(request,response);
                    logger.info("拦截到权限不足访问{}",user);
                    return false;
                }
            }else {
//                request.setAttribute("msg", "请先登录！");
                response.sendRedirect("/login.html");
                return false;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        String s = (String) request.getSession().getAttribute("error_msg");
//        if(StringUtils.isEmpty(s)){
//            logger.info("不太彳亍");
//            request.
////            modelAndView.addObject("msg",s);
////            modelAndView.setViewName("index");
//        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
