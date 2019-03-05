package com.chat.service.conversation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.entity.conversation.ConversationEntity;
import com.chat.repository.conversation.ConversationRepository;

@Service
public class ConversationServiceImpl implements ConversationService {

	private final ConversationRepository cRepository;

	@Autowired 
	public ConversationServiceImpl(ConversationRepository cRepository) {
		this.cRepository = cRepository;
	}

	public void save(ConversationEntity conversation) {
		cRepository.save(conversation);
		
	}

	@Override
	public List<ConversationEntity> findAllByContacts_Name(String name) {
		return cRepository.findAllByContacts_Name(name);
	}
	 
	 
	public Optional<ConversationEntity> findById(Long id) {
		return cRepository.findById(id);
	}
	
}


