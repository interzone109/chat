package com.chat.logic.conversation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.chat.entity.conversation.ConversationEntity;
import com.chat.entity.user.UserEntity;
import com.chat.model.user.UserModel;
import com.chat.model.сonversation.ConversationModel;

@Component
public class ModelBuilder {


	/**
	 * method builds a ConversationModel  from data
	 */
	public ConversationModel createConversationModel(ConversationEntity conversation) {
		return	ConversationModel.builder()
					.id(conversation.getId())
					.conversationName(conversation.getConversationName())
					.build();
	}

	/**
	 * method builds a UserModel set from data
	 */
	public Set<UserModel> createContactsModel(List<UserEntity> contacts) {
		Set<UserModel> contactsModel = new HashSet<>();
		contacts.forEach(cont -> {
			contactsModel.add(UserModel.builder()
					.id(cont.getId())
					.name(cont.getName())
					.build());
		});
		return contactsModel;
	}


}
