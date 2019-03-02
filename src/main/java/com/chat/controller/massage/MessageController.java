package com.chat.controller.massage;

import org.springframework.http.HttpEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chat.model.massage.MessageModel;

@RestController
@RequestMapping("/messages")
public class MessageController {

	
	@PostMapping
    @MessageMapping("/newMessage")
    @SendTo("/topic/newMessage")
    public MessageModel saveMessage(MessageModel messageModel) {
		return null;
	}
	
	@GetMapping
	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public HttpEntity<MessageModel> list() {
		return null;
	}
}
