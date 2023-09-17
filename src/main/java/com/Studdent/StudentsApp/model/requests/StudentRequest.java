package com.Studdent.StudentsApp.model.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentRequest {
    private String name;
  //  @Size(min = 3,message = "username length should be more then 3")
    private String username;
    private String email;
    private String password;
}
