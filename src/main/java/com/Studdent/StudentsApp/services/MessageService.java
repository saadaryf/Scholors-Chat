package com.Studdent.StudentsApp.services;

import com.Studdent.StudentsApp.model.Message;
import com.Studdent.StudentsApp.model.users.Student;
import jakarta.transaction.Transactional;

import java.util.List;

public interface MessageService {
    @Transactional
    void sendMessage(Message message);
    @Transactional
    List<Message> getSentMessages(Student student);
    @Transactional
    List<Message> getReceivedMessages(Student student);

    @Transactional
    List<Message> getSentMessagesForReceiver(Student sender, Long receiverId);

    @Transactional
    List<Message> getReceivedMessagesFromSender(Student receiver, Long senderId);

}
