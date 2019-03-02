package com.chat.entity.conversation;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.chat.entity.message.MessageEntity;
import com.chat.entity.user.UserEntity;

import lombok.Data;

/**
 * @author Gromko Maksim
 * MassageEntity - description of the message entity
 * */
@Entity
@Table(name = "conversations")
@Data
public class ConversationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "conversation_id", nullable = false, unique = true, updatable= false)
	private long id;
	
	@Column(name = "conversation_name", nullable = false)
	private String conversationName ;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="user_conversations",joinColumns =@JoinColumn(name="conversation_id"),
	inverseJoinColumns = @JoinColumn(name="user_id" ))
	private List<UserEntity> contacts ;
	
	@OneToMany(mappedBy = "conversation", fetch = FetchType.LAZY 
			, cascade = CascadeType.ALL)
	private List<MessageEntity> massages ;
}
