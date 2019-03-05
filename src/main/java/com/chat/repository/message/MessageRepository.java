package com.chat.repository.message;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.entity.message.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

}
