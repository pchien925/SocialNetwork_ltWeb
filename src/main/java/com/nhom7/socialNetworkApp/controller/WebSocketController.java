package com.nhom7.socialNetworkApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.nhom7.socialNetworkApp.entity.Message;
import com.nhom7.socialNetworkApp.entity.User;
import com.nhom7.socialNetworkApp.entity.notification.NotificationMessage;
import com.nhom7.socialNetworkApp.services.IMessageService;
import com.nhom7.socialNetworkApp.services.INotificationService;
import com.nhom7.socialNetworkApp.services.IStatusService;
import com.nhom7.socialNetworkApp.services.IUserService;

@Controller
public class WebSocketController {
	@Autowired
    private INotificationService notificationService;
	@Autowired
    private IUserService userService;
	@Autowired
	private IStatusService statusService;
	@Autowired
	private IMessageService messageService;
	@MessageMapping("/chat/message/{usernameTransmitter}/{usernameReceiver}/{friendIsInChat}")
    @SendTo({"/ws/chat/message/{usernameReceiver}/{usernameTransmitter}"})
    public Message sendMessage(@DestinationVariable String friendIsInChat, Message message) {
        try {
            boolean friendInChat = Boolean.parseBoolean(friendIsInChat);
            if (!friendInChat) {
                NotificationMessage notification = new NotificationMessage();
                notification.setUserTransmitter(message.getUserTransmitter());
                notification.setUserReceiver(message.getUserReceiver());
                notificationService.save(notification);
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return message;
    }
	@MessageMapping("/chat/message/delete/{usernameTransmitter}/{usernameReceiver}")
    @SendTo({"/ws/chat/message/delete/{usernameReceiver}/{usernameTransmitter}"})
    public Message deleteMessage(Message message) {
        return message;
    }

    @MessageMapping("/chat/writing/{usernameTransmitter}/{usernameReceiver}")
    @SendTo({"/ws/chat/writing/{usernameReceiver}/{usernameTransmitter}"})
    public String userIsWriting() {
        return "200";
    }
    @MessageMapping("/chat/connect/{username}")
    @SendTo({"/ws/chat/connect/{username}"})
    public String userConnected(@DestinationVariable String username) {
        User user = userService.findByUsername(username).get();
        user.setStatus(this.statusService.findById(1L).get());
        userService.save(user);
        return "200";
    }

    @MessageMapping("/chat/disconnect/{username}")
    @SendTo({"/ws/chat/disconnect/{username}"})
    public String userDisconnect(@DestinationVariable String username) {
        User user = userService.findByUsername(username).get();
        user.setStatus(this.statusService.findById(2L).get());
        userService.save(user);
        return "200";
    }

    @MessageMapping("/chat/inChat/{usernameTransmitter}/{usernameReceiver}")
    @SendTo({"/ws/chat/inChat/{usernameReceiver}/{usernameTransmitter}"})
    public String userIsInChat() {
        return "true";
    }
    @MessageMapping("/chat/outChat/{usernameTransmitter}/{usernameReceiver}")
    @SendTo({"/ws/chat/inChat/{usernameReceiver}/{usernameTransmitter}"})
    public String userIsNotInChat() {
        return "false";
    }	
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(
            @Payload Message chatMessage
    ) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Message addUser(
            @Payload Message chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("userId", chatMessage.getUserTransmitter().getId());
       // headerAccessor.getSessionAttributes().put("userId", chatMessage.getUserReceiver().getUsername());
        System.out.println("ok");
        return chatMessage;
    }
    
}
