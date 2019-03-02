package com.chat.service.user;

import java.util.List;
import java.util.Optional;

import com.chat.entity.user.UserEntity;

public interface UserService {
	
	Optional<UserEntity> findOneByName(String name);
	
	Optional<UserEntity> findOneById(Long id);

	UserEntity save(UserEntity user);
	
	List<UserEntity> findByNameIgnoreCaseContaining(String name);
}
