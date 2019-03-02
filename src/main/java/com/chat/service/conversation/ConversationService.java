package com.chat.service.conversation;

import java.util.List;

import com.chat.entity.conversation.ConversationEntity;

public interface ConversationService {

	List<ConversationEntity> findAllByContacts_Name(String name); 
}
