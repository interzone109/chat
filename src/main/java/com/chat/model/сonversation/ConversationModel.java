package com.chat.model.сonversation;

import java.util.List;
import java.util.Set;

import com.chat.model.massage.MessageModel;
import com.chat.model.user.UserModel;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ConversationModel {
	private long id;

	private Set<UserModel> contacts ;
	
	private String conversationName ;

	private List<MessageModel> massages ;
}
