package com.Studdent.StudentsApp.model.users;

import jakarta.validation.constraints.Email;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class MyUserDetails implements UserDetails {
    private static final Logger logger = LoggerFactory.getLogger(MyUserDetails.class);

    private int id;
    private String userName;
    private String password;
    private List<GrantedAuthority> authorities;

    private String name;
    private String email;
    private Date joinDate;


    public MyUserDetails(){};

    /*public MyUserDetails(String username){
        this.userName=username;
    }*/
    public MyUserDetails(Student student){
        this.id=student.getId();
        this.userName=student.getUsername();
        this.password=student.getPassword();
        this.email=student.getEmail();
        this.name=student.getName();
        this.joinDate=student.getJoinDate();
        this.authorities=student.getRoles().stream()
                .map(role-> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    //    logger.info("\n\nStudent in USER-DETAILS: "+student.getUsername()+"\nPass: "+student.getPassword()+"\nEmail: "+student.getEmail()+"\nName: "+student.getName()+"\nRole: "+student.getRoles());

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
       // return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
        return true;
    }
}
