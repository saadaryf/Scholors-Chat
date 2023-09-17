package com.Studdent.StudentsApp.services.impl;

import com.Studdent.StudentsApp.model.users.Student;
import com.Studdent.StudentsApp.repository.StudentRepository;
import com.Studdent.StudentsApp.services.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public boolean register(Student student) {
        Optional<Student> foundStudent= studentRepository.findByUsername(student.getUsername());
       // logger.info("\nTHIS serviceReg fun  IS BEING CALLED --------------<<<<<<<!");
        if(foundStudent.isPresent()){
            logger.info("User exists username: "+student.getUsername()+"\nPass: "+student.getPassword()+"\nEmail: "+student.getEmail()+"\nid: "+student.getId()+"\nName: "+student.getName());
            return false;
        }else{
            student.setPassword(passwordEncoder.encode(student.getPassword()));
            Set<String> roles=assignRolesBasedOnBusinessLogic();
            student.setRoles(roles);
            studentRepository.save(student);
            return true;
        }
    }

    @Override
    public Set<String> assignRolesBasedOnBusinessLogic() {
        // Implement your role assignment logic based on student properties
        Set<String> roles = new HashSet<>();
        // Example: Assign ROLE_USER to all students
        roles.add("ROLE_USER");
        return roles;
    }

    @Override
    public List<Student> viewAllSts() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(int id) {
        Optional<Student> studentOptional=studentRepository.findById(id);
        return studentOptional.orElse(null);
    }

    @Override
    public Student findByUserName(String username) {
        Optional<Student> studentOptional= studentRepository.findByUsername(username);
        return studentOptional.orElse(null);
    }

}





// BeanUtils.copyProperties(studentRequest,student);


/*private String encodePassword(Student student){
         String encodedPass= passwordEncoder.encode(student.getPassword());
         return encodedPass;
    }*/


/*   @Override
    public boolean login(String username, String password) {
        Optional<Student> foundStudent= studentRepository.findByUsername(username);
        if(foundStudent.isPresent()){
            logger.info("Student found");
            Student student=foundStudent.get();
            if(student.getPassword().equals(password)){
                logger.info("Password matched");
                return true;
            }
            else {
                logger.info("Password doesn't match");
            }
        }
        logger.warn("student not present");
        return false;
    }
*/