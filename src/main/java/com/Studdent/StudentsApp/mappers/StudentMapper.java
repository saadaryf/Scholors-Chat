package com.Studdent.StudentsApp.mappers;

import com.Studdent.StudentsApp.model.responses.StudentResponse;
import com.Studdent.StudentsApp.model.users.Student;
import com.Studdent.StudentsApp.model.requests.StudentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StudentMapper {
    private static final Logger logger = LoggerFactory.getLogger(StudentMapper.class);

    public Student mapToEntity(StudentRequest studentRequest){
        Student student=new Student();
        student.setName(studentRequest.getName());
       // logger.info("\nSTUDENT ID: "+student.getId());
  /*      logger.info("Student name: " +studentRequest.getName());
        logger.info("Student email: " +studentRequest.getEmail());*/
        student.setUsername(studentRequest.getUsername());
        student.setEmail(studentRequest.getEmail());
        student.setJoinDate(new Date());
        student.setPassword(studentRequest.getPassword());
     /*   logger.info("Student id: " +student.getId());
        logger.info("Student name: " +student.getName());
        logger.info("Student username: " +student.getUsername());
        logger.info("Student email: " +student.getEmail());
        logger.info("Student pass: " +student.getPassword());*/
        logger.info("\nTHIS mapper fun  IS BEING CALLED --------------<<<<<<<!");
        return student;
    }


    public StudentResponse mapToDTO(Student student){
        StudentResponse dto=new StudentResponse();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setUsername(student.getUsername());
        dto.setJoinDate(student.getJoinDate());
        return dto;
    }
}
