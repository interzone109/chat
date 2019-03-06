package com.chat.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.entity.conversation.ConversationEntity;
import com.chat.model.massage.MessageModel;
import com.chat.model.сonversation.ConversationModel;
import com.chat.service.conversation.ConversationServiceImpl;
import com.chat.service.message.MessageServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/conversation/message")
@Slf4j
public class MessageSearchController {
	@Autowired
	private MessageServiceImpl messageServiceImpl;
	@Autowired
	private ConversationServiceImpl convServiceImpl;
	
	@PostMapping
	public List<MessageModel> searchMessages(@RequestBody ConversationModel conversationModel) {
		log.info("Get last 10 message for conversation"+conversationModel.getId());
		ConversationEntity conversation = convServiceImpl.findById(conversationModel.getId()).get();
		
		 List<MessageModel> messages = new ArrayList<>();
		 
		messageServiceImpl.findTop10ByConversationOrderByIdAsc(conversation).forEach(message->{
			messages.add(MessageModel.builder()
					.conversationId(message.getConversation().getId())
					.date(message.getDate())
					.sender(message.getSender())
					.text(message.getText())
					.build());
		});
	return messages ;
	}
}
