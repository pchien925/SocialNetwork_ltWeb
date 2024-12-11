package com.nhom7.socialNetworkApp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.nhom7.socialNetworkApp.entity.Message;
import com.nhom7.socialNetworkApp.services.IUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {
	private final SimpMessageSendingOperations messagingTemplate;
	@Autowired
	private IUserService userService;
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String userId = (String) headerAccessor.getSessionAttributes().get("userId");
        if (userId != null) {
            log.info("user disconnected: {}", userId);
            var chatMessage = Message.builder()
                    .text("testtext")
                    .userReceiver(userService.findById(3l).get())
                    .userTransmitter(userService.findById(48l).get())
                    .build();
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }

}
