package com.Studdent.StudentsApp.controller;

import com.Studdent.StudentsApp.mappers.StudentMapper;
import com.Studdent.StudentsApp.model.responses.StudentResponse;
import com.Studdent.StudentsApp.model.users.Student;
import com.Studdent.StudentsApp.model.requests.StudentRequest;
import com.Studdent.StudentsApp.security.MyUserDetailsService;
import com.Studdent.StudentsApp.services.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @RequestMapping(value = "register",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<String> registration(@ModelAttribute StudentRequest studentRequest){
        logger.info("\nTHIS register fun  IS BEING CALLED --------------<<<<<<<!");
        Student student=studentMapper.mapToEntity(studentRequest);
        if(studentService.register(student)) {
            return ResponseEntity.ok("Student Registered!");
        }
        else {
            return ResponseEntity.ok("Username Already Exists!");
        }
    }

    @GetMapping("/viewProfile")
    public String viewProfile(Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        UserDetails userDetails= myUserDetailsService.loadUserByUsername(username);
        model.addAttribute("userDetails",userDetails);
        return "/Student/profile";
    }

    @GetMapping("/viewAll")
    public String viewAll(Model model){
        List<Student> students= studentService.viewAllSts();
        List<StudentResponse> studentResponses=students.stream()
                .map(student -> studentMapper.mapToDTO(student))
                .collect(Collectors.toList());
        model.addAttribute("studentResponses",studentResponses);
        return "/Student/view";
    }





}







/*@RequestMapping(value = "login", method ={RequestMethod.GET,RequestMethod.POST})
    public String findAuthor(@ModelAttribute LoginRequest loginRequest){
        String username=loginRequest.getUsername();
        String password=loginRequest.getPassword();
        System.out.println(username);
        System.out.println(password);
        boolean foundStudent=studentService.login(username,password);
        if(foundStudent){

            return "/Message/index";
        }
        else{
            return "redirect:/login?error";
        }
    }*/