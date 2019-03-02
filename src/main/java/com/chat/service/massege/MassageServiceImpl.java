package com.chat.service.massege;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.entity.message.MessageEntity;
import com.chat.repository.massege.MassageRepository;

@Service
public class MassageServiceImpl implements MassageService {

	private final MassageRepository meRepository;

	@Autowired
	public MassageServiceImpl(MassageRepository meRepository) {
		this.meRepository = meRepository;
	}

	public void save(MessageEntity testMassage) {
		meRepository.save(testMassage);
		
	}
	

	
}
