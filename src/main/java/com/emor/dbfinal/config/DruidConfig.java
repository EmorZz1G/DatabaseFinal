//package com.emor.dbfinal.config;
//
//
//import java.util.Arrays;
//import java.util.HashMap;
//
//import javax.sql.DataSource;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.support.http.StatViewServlet;
//import com.alibaba.druid.support.http.WebStatFilter;
//
//
//
//
//@Configuration
//public class DruidConfig {
//
//    @Bean
//    @ConfigurationProperties(prefix="spring.datasource")
//    public DataSource druid() {
//        return new DruidDataSource();
//    }
//    @Bean
//    public ServletRegistrationBean statViewServlet() {
//        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
//        HashMap<String, String> hashMap = new HashMap<String,String>();
////		map.put("loginUsername","admin");
////		map.put("loginPassword","admin");
////		map.put("allow","localhost");
//        bean.setInitParameters(hashMap);
//        return bean;
//    }
//    @Bean
//    public FilterRegistrationBean statFilter() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
//        // 添加过滤规则
//        filterRegistrationBean.addUrlPatterns("/*");
//        // 忽略过滤格式
//        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,");
//        return filterRegistrationBean;
//    }
//}
