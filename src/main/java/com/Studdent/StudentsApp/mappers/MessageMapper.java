package com.Studdent.StudentsApp.mappers;

import com.Studdent.StudentsApp.exceptions.ReceiverNotFoundException;
import com.Studdent.StudentsApp.model.Message;
import com.Studdent.StudentsApp.model.requests.MessageRequest;
import com.Studdent.StudentsApp.model.responses.MessageResponse;
import com.Studdent.StudentsApp.model.users.Student;
import com.Studdent.StudentsApp.services.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MessageMapper {
    private static final Logger logger = LoggerFactory.getLogger(MessageMapper.class);

    @Autowired
    StudentService studentService;
    public Message mapToEntity(MessageRequest messageRequest,Student sender,Student receiver){
        Message message=new Message();
        message.setContent(messageRequest.getContent());
        message.setTime(new Date());
       /* Student student=studentService.getStudentById(messageRequest.getSender_id());
        if(student==null){
            logger.warn("Sender Not Found in Message Mapper");
        }
        Student student1=studentService.getStudentById(messageRequest.getReceiver_id());
        if(student1!=null){
            logger.warn("Receiver Not Found in Message Mapper");
        }*/
        if(sender==null){
            logger.warn("Sender Not Found in Message Mapper");
        }
        if(receiver==null){
            throw new ReceiverNotFoundException("Receiver Not Found!");
        }
        message.setStudent(sender);
        message.setStudent1(receiver);
        return message;
    }

    public MessageResponse mapToDTO(Message message){
        MessageResponse dto=new MessageResponse();
        dto.setContent(message.getContent());
        dto.setTime(message.getTime());
        dto.setSender_id(message.getStudent().getId());
        dto.setReceiver_id(message.getStudent1().getId());
        dto.setSender_name(message.getStudent().getUsername());
        dto.setReceiver_name(message.getStudent1().getUsername());
        return dto;
    }

}
