// Student Entity
package com.Studdent.StudentsApp.model.users;

import com.Studdent.StudentsApp.model.Message;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Students")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name="email", unique = true)
    private String email;

    @Column(name = "password", unique = true)
    private String password;

    @Column(name = "joinDate")
    private Date joinDate;

    @OneToMany(mappedBy = "student" ,cascade = CascadeType.ALL)
    private List<Message> messageList;

    @OneToMany(mappedBy = "student1", cascade = CascadeType.ALL)
    private List<Message> messageList1;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "student_roles", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "role")
    private Set<String> roles;


}
