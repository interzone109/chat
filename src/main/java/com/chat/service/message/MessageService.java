package com.chat.service.message;

import java.util.List;

import com.chat.entity.conversation.ConversationEntity;
import com.chat.entity.message.MessageEntity;

public interface MessageService {
	MessageEntity save(MessageEntity messageEntity);
	
	List<MessageEntity> findTop10ByConversationOrderByIdAsc (ConversationEntity conversation);

}
