package com.chat.repository.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.entity.user.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findOneByName(String name);
	
	List<UserEntity> findByNameIgnoreCaseContaining(String name);
}
