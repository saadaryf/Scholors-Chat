package com.Studdent.StudentsApp.model.responses;

import lombok.Data;

import java.util.Date;

@Data
public class StudentResponse {
    private int id;
    private String name;
    private String username;
    private String email;
    private Date joinDate;
}
