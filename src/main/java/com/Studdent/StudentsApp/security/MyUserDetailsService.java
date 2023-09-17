package com.Studdent.StudentsApp.security;

import com.Studdent.StudentsApp.model.users.MyUserDetails;
import com.Studdent.StudentsApp.model.users.Student;
import com.Studdent.StudentsApp.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
    @Autowired
    StudentRepository studentRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Student> studentOptional= studentRepository.findByUsername(username);
        studentOptional.orElseThrow(()->new UsernameNotFoundException("Not Found: "+username));
        Student student=studentOptional.get();
      //  logger.info("\n\nStudent: "+student.getUsername()+"\nPass: "+student.getPassword()+"\nEmail: "+student.getEmail()+"\nName: "+student.getName()+"\nRole: "+student.getRoles());
        return new MyUserDetails(student);
    }
}
