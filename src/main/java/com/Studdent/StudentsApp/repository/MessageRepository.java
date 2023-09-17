package com.Studdent.StudentsApp.repository;

import com.Studdent.StudentsApp.model.Message;
import com.Studdent.StudentsApp.model.users.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {
    List<Message> findByStudent(Student student);
    List<Message> findByStudent1(Student student);
    List<Message> findByStudentAndStudent1_Id(Student sender,Long receiver_id);
    List<Message> findByStudent1AndStudent_Id(Student receiver, Long sender_id);
}
