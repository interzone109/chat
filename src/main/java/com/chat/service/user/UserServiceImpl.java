package com.chat.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.entity.user.UserEntity;
import com.chat.repository.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Optional<UserEntity> findOneByName(String name) {
		return userRepository.findOneByName(name);
	}

	@Override
	public UserEntity save(UserEntity user) {
		return userRepository.save(user);
	}

	@Override
	public List<UserEntity> findByNameIgnoreCaseContaining(String name) {
		return userRepository.findByNameIgnoreCaseContaining(name);
	}

	@Override
	public Optional<UserEntity> findOneById(Long id) {
		return userRepository.findById(id);
	}
	

	
}
