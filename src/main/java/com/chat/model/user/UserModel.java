package com.chat.model.user;

import java.util.Set;

import com.chat.model.сonversation.ConversationModel;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserModel {
	private long id;
	
	private String name;
	
	private Set<ConversationModel> conversation ;
}
