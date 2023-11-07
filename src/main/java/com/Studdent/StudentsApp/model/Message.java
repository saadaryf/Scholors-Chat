// Message Entity
package com.Studdent.StudentsApp.model;

import com.Studdent.StudentsApp.model.users.Student;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Table(name = "Messages")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "content" ,length = 500)
    private String content;

    @Column(name = "time")
    private Date time;


    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Student student1;
}
