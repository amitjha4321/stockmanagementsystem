package com.stockmanagementsystem.stockmanagementsystem.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private com.stockmanagementsystem.stockmanagementsystem.models.UserDetails userDetails;
    private User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities1=new ArrayList<>();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        System.out.println("upadated hope this works now" + grantedAuthorities);
        System.out.println("this is the main cause"+ user.getRoles());

//        return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role))
//                .collect(Collectors.toList());
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        //System.out.println(user.getPassword());
        return user.getPassword();

    }


    @Override
    public String getUsername() {
        //System.out.println(user.getEmail());
        return user.getEmail();
    }

    public String getUserImage(){
         userDetails.setBase64EncodedImage(Base64.getEncoder().encodeToString(user.getDataimage()));
        return userDetails.getBase64EncodedImage();

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(user.isEnabled())
            return true;
        return false;
    }
}
