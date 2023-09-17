package com.Studdent.StudentsApp.model.responses;

import lombok.Data;

import java.util.Date;

@Data
public class MessageResponse {

    private String content;
    private Date time;
    private int sender_id;
    private int receiver_id;
    private String sender_name;
    private String receiver_name;
   /* private int sender_id;
    private int receiver_id;*/
}
