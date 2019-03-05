package com.chat.model.massage;

import java.util.Calendar;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MessageModel {
	private long id;
	
	// date of posting
	private Calendar date ;
	
	// massage sender name
	private String sender ;
	
	// massage text
	private String text ;
	
	private long conversationId;
}
