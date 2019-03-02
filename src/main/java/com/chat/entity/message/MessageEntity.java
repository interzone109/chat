package com.chat.entity.message;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.chat.entity.conversation.ConversationEntity;

import lombok.Data;

/**
 * @author Gromko Maksim
 * MassageEntity - description of the message entity
 * */
@Entity
@Table(name = "messages")
@Data
public class MessageEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_id", nullable = false, unique = true, updatable= false)
	private long id;
	
	// date of posting
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar date ;
	
	// massage sender name
	@Column(name = "sender_name", nullable = true)
	private String sender ;
	
	// massage text
	@Column(name = "message", nullable = true)
	private String message ;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "conversation_id", nullable = false)
	private ConversationEntity conversation ;
}
