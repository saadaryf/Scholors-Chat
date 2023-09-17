package com.Studdent.StudentsApp.services;

import com.Studdent.StudentsApp.model.requests.StudentRequest;
import com.Studdent.StudentsApp.model.users.Student;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Set;


public interface StudentService {
    @Transactional
    boolean register(Student student);
    Set<String> assignRolesBasedOnBusinessLogic();

    @Transactional
    List<Student> viewAllSts();

    @Transactional
    Student getStudentById(int id);

    @Transactional
    public Student findByUserName(String username);
}



/*    @Transactional
    public void createStudent(Student student);*/

/*  @Transactional
    public boolean login(String username,String password);
*/
