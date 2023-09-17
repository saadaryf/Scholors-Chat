package com.Studdent.StudentsApp.services.impl;

import com.Studdent.StudentsApp.model.Message;
import com.Studdent.StudentsApp.model.users.Student;
import com.Studdent.StudentsApp.repository.MessageRepository;
import com.Studdent.StudentsApp.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Override
    public void sendMessage(Message message) {
        messageRepository.save(message);
    }
    @Override
    // Method to get messages sent by a student
    public List<Message> getSentMessages(Student student) {
        return messageRepository.findByStudent(student);
    }
    @Override

    // Method to get messages received by a student
    public List<Message> getReceivedMessages(Student student) {
        return messageRepository.findByStudent1(student);
    }

    @Override
    public List<Message> getSentMessagesForReceiver(Student sender, Long receiverId) {
        return messageRepository.findByStudentAndStudent1_Id(sender,receiverId);
    }

    @Override
    public List<Message> getReceivedMessagesFromSender(Student receiver, Long senderId) {
        return messageRepository.findByStudent1AndStudent_Id(receiver,senderId);
    }
}
