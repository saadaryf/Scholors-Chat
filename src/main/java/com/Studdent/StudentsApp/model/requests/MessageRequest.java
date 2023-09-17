package com.Studdent.StudentsApp.model.requests;

import lombok.Data;

import java.util.Date;

@Data
public class MessageRequest {
    private String content;
    private Date time;
    private int sender_id;
    private int receiver_id;
}
