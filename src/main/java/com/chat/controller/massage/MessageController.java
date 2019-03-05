package com.chat.controller.massage;

import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.chat.entity.conversation.ConversationEntity;
import com.chat.entity.message.MessageEntity;
import com.chat.model.massage.MessageModel;
import com.chat.service.conversation.ConversationServiceImpl;

@Controller
public class MessageController {
	
	@Autowired
	private ConversationServiceImpl conversationServiceImpl;
	
	
	@MessageMapping("/message/{chatId}")
	@SendTo("/topic/response/{chatId}")
	public MessageModel mailing(MessageModel message) throws Exception {
		
		System.err.println("/topic/mailing "+message.getConversationId());
		ConversationEntity con = conversationServiceImpl.findById(message.getConversationId()).get();
		
		MessageEntity messageEntity = new MessageEntity();
		messageEntity.setDate(new GregorianCalendar());
		messageEntity.setSender(message.getSender());
		messageEntity.setText(message.getText());
		messageEntity.setConversation(con);
		
		con.getMessages().add(messageEntity);
		conversationServiceImpl.save(con);
		
		
		return MessageModel.builder()
				.id(messageEntity.getId())
				.date(messageEntity.getDate())
				.sender(messageEntity.getSender())
				.text(messageEntity.getText())
				.conversationId(messageEntity.getConversation().getId())
				.build();
	}
	
}
