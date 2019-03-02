package com.chat.controller.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.entity.conversation.ConversationEntity;
import com.chat.entity.user.UserEntity;
import com.chat.logic.conversation.ModelBuilder;
import com.chat.model.user.UserModel;
import com.chat.model.сonversation.ConversationModel;
import com.chat.service.conversation.ConversationServiceImpl;
import com.chat.service.user.UserServiceImpl;

import javassist.NotFoundException;

@RestController
@RequestMapping("/conversation")
public class ConversationController {
	
	@Autowired
	private ConversationServiceImpl convServiceImpl;
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private ModelBuilder modelBuilder;
	
	/**
	 * show all conversation current user
	 * @throws NotFoundException 
	 */
	@GetMapping
	public List<ConversationModel> showAllonversations(HttpServletRequest request) throws NotFoundException{
		
		String username = (String) request.getSession().getAttribute("username");
		if (username == null || username.isEmpty()) {
			throw new NotFoundException("cant find current user");
		}
		
		List<ConversationModel> conversationModels = new ArrayList<>();
		convServiceImpl.findAllByContacts_Name(username).forEach(conversation->{
				conversationModels.add(modelBuilder.createConversationModel(conversation));
			});
		return conversationModels ;
	}
	
	
	
	/**
	 * create new conversation
	 */
	@PostMapping
	public ConversationModel searchContacts(HttpServletRequest request,@RequestBody UserModel userModel)
			throws NotFoundException{
		
		String username = (String) request.getSession().getAttribute("username");
		if (username == null || username.isEmpty()) {
			throw new NotFoundException("cant find current user");
		}
		
		Optional<UserEntity> userOptional = userServiceImpl.findOneByName(username);
		Optional<UserEntity> contactOptional = userServiceImpl.findOneById(userModel.getId());
		
		if(userOptional.isPresent() && contactOptional.isPresent()) {
			
			ConversationEntity conversation = new ConversationEntity();
			conversation.setConversationName(userOptional.get().getName()+","+contactOptional.get().getName());
			
			List<UserEntity> contacts = new ArrayList<>();
			contacts.add(userOptional.get());
			contacts.add(contactOptional.get());
			conversation.setContacts(contacts);
			convServiceImpl.save(conversation);
			
			return modelBuilder.createConversationModel(conversation);
			
		}else {
			throw new NotFoundException("cant find user ");
		}
		
	}
}
