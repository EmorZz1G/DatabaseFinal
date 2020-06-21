//package com.emor.dbfinal.config;
//
//
//import com.emor.dbfinal.dao.UserMapper;
//import com.emor.dbfinal.service.impl.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
////@EnableWebSecurity
////@Configuration
//public class MySecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    UserDetailsServiceImpl userDetailsService;
//    /**
//     * 定义授权
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        //授权
//        super.configure(http);
//
//        http.authorizeRequests()
//                .antMatchers("/")
//                .permitAll()
//                .anyRequest().authenticated();
//                http.authorizeRequests().antMatchers("/tcr/**").hasRole("teacher")
//                .antMatchers("/stu/**").hasRole("student");
//                http.authorizeRequests().antMatchers("/hello").hasRole("teacher");
//        //登录
//        http.formLogin()//.successForwardUrl("/stu.html");
//                    .loginPage("/login")
//                .usernameParameter("username")
//                .passwordParameter("password")
//
//                .loginProcessingUrl("/login")
//                .successForwardUrl("/index")
//                .failureForwardUrl("/login")
//                .permitAll();
//        //记住我
//        http.rememberMe();//.rememberMeParameter("remember-me");
//        //登出
//        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();
//        http.csrf().disable();
//    }
//    /**
//     * 定义认证
//     */
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("emor").password("emor").roles("student");
//        System.out.println(auth.getDefaultUserDetailsService());
//        auth.userDetailsService(userDetailsService);
//    }
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider
//                = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        return authProvider;
//    }
//
////    @Bean
////    public UserDetailsService userDetailsService() {
//////        return username -> {
//////            List<UserEntity> users = loginService.getUserByUsername(username);
//////            if (users == null || users.size() == 0) {
//////                throw new UsernameNotFoundException("用户名未找到");
//////            }
//////            String password = users.get(0).getPassword();
//////            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//////            String passwordAfterEncoder = passwordEncoder.encode(password);
//////            return User.withUsername(username).password(passwordAfterEncoder).roles("").build();
//////        };
////        return new userDetailsService;
////    }
//}
