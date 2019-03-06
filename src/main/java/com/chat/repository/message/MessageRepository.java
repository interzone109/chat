package com.chat.repository.message;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.entity.conversation.ConversationEntity;
import com.chat.entity.message.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
	
	List<MessageEntity> findTop10ByConversationOrderByIdAsc (ConversationEntity conversation);

}
