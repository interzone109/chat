package com.chat.service.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.entity.message.MessageEntity;
import com.chat.repository.message.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {

	private final MessageRepository meRepository;

	@Autowired
	public MessageServiceImpl(MessageRepository meRepository) {
		this.meRepository = meRepository;
	}

	public MessageEntity save(MessageEntity messageEntity) {
		return meRepository.save(messageEntity);
	}
	

	
}
