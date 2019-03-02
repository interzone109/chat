package com.chat.repository.massege;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.entity.message.MessageEntity;

public interface MassageRepository extends JpaRepository<MessageEntity, Long> {

}
