package com.Studdent.StudentsApp.controller;

import com.Studdent.StudentsApp.mappers.MessageMapper;
import com.Studdent.StudentsApp.model.Message;
import com.Studdent.StudentsApp.model.requests.MessageRequest;
import com.Studdent.StudentsApp.model.responses.MessageResponse;
import com.Studdent.StudentsApp.model.users.Student;
import com.Studdent.StudentsApp.services.MessageService;
import com.Studdent.StudentsApp.services.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private StudentService studentService;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private MessageService messageService;

    @PostMapping("/send-message")
    public /*ResponseEntity<String>*/ String sendMessage(@ModelAttribute MessageRequest messageRequest, RedirectAttributes attributes){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String senderUsername=authentication.getName();
        Student sender=studentService.findByUserName(senderUsername);

        Student receiver=studentService.getStudentById(messageRequest.getReceiver_id());
        Message message=messageMapper.mapToEntity(messageRequest,sender,receiver);

        messageService.sendMessage(message);
        /*return ResponseEntity.ok("Message Saved!!");*/
        attributes.addAttribute("studentId",messageRequest.getReceiver_id());
        return "redirect:/messages";
    }

    @GetMapping("/messages")
    public String viewMessages(@RequestParam("studentId") Long studentId, Model model, Principal principal) {
        String username = principal.getName();
        Student student = studentService.findByUserName(username);

        /*List<Message> sentMessages = messageService.getSentMessages(student);
        List<Message> receivedMessages = messageService.getReceivedMessages(student);*/
        List<Message> sentMessages = messageService.getSentMessagesForReceiver(student,studentId);
        List<Message> receivedMessages = messageService.getReceivedMessagesFromSender(student,studentId);

        List<MessageResponse> sentMessagesResponse=sentMessages.stream()
                        .map(msg->messageMapper.mapToDTO(msg))
                        .collect(Collectors.toList());
        List<MessageResponse> receivedMessageResponse=receivedMessages.stream()
                        .map(msg->messageMapper.mapToDTO(msg))
                        .collect(Collectors.toList());
        List<MessageResponse> allMessagesResponse = new ArrayList<>();
        allMessagesResponse.addAll(sentMessagesResponse);
        allMessagesResponse.addAll(receivedMessageResponse);

        Collections.sort(allMessagesResponse, Comparator.comparing(MessageResponse::getTime));

        int studentIdInt = studentId.intValue();

        String receiverName = studentService.getStudentById(studentIdInt).getName();
        model.addAttribute("receiverName",receiverName);
        model.addAttribute("allMessages", allMessagesResponse);
        model.addAttribute("username",username);
        model.addAttribute("studentId",studentId);

        return "/Message/ChatBox"; // Assuming "messages" is your view name (e.g., messages.html)
    }


}
