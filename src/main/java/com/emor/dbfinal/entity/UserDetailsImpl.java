//package com.emor.dbfinal.entity;
//
//import lombok.ToString;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//
//@ToString
//public class UserDetailsImpl implements UserDetails {
//    private String username;
//    private String password;
//    private String myrole;
//    public UserDetailsImpl(User user){
//        this.username=user.getUsername();
//        this.password=user.getPassword();
//        this.myrole=user.getMyrole();
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        System.out.println("getAuthorities");
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_"+(this.getMyrole()!=null?(this.getMyrole()):"student")));
//        System.out.println("getAuthorities "+authorities);
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public String getMyrole() {
//        return this.myrole;
//    }
//}
