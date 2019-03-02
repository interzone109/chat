package com.chat.controller.rest;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.entity.user.UserEntity;
import com.chat.logic.conversation.ModelBuilder;
import com.chat.model.user.UserModel;
import com.chat.service.user.UserServiceImpl;


@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private ModelBuilder modelBuilder;
	

	/**
	 * create set users from search quarry
	 */
	@PostMapping
	public  Set<UserModel> searchContacts(@RequestBody UserModel userModel){
		List<UserEntity> contacts = userServiceImpl.findByNameIgnoreCaseContaining(userModel.getName());
		return modelBuilder.createContactsModel(contacts);
	}

}
