package com.nhom7.socialNetworkApp.configuration;

import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataHandler extends TextWebSocketHandler{
	@Override
	public void handleMessage(WebSocketSession session,WebSocketMessage<?> message)
	{
		log.info("Message: {}",message.getPayload());
	}
}
