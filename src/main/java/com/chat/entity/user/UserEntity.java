package com.chat.entity.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.chat.entity.conversation.ConversationEntity;

import lombok.Data;

/**
 * @author Gromko Maksim UserEntity - description of the user entity
 */
@Entity
@Table(name = "users")
@Data
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false, unique = true, updatable = false)
	private long id;

	// user name
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	
	@ManyToMany(mappedBy = "contacts", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ConversationEntity> conversations;

}
