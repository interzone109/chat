package com.chat.repository.conversation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.entity.conversation.ConversationEntity;

public interface ConversationRepository extends JpaRepository<ConversationEntity, Long>{
	List<ConversationEntity> findAllByContacts_Name(String name); 
}
