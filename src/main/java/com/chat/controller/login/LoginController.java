package com.chat.controller.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.chat.entity.conversation.ConversationEntity;
import com.chat.entity.user.UserEntity;
import com.chat.service.conversation.ConversationServiceImpl;
import com.chat.service.user.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private ConversationServiceImpl convServiceImpl;

	
	@RequestMapping(path = "/chat", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) {
		
		String username = (String) request.getSession().getAttribute("username");
		if (username == null || username.isEmpty()) {
			
			return "redirect:/login";
		}
		
		Optional<UserEntity> userOptional = userServiceImpl.findOneByName(username);
		
		if (!userOptional.isPresent()){
			log.info("registrate new user "+username);
			UserEntity userSystem = userServiceImpl.findOneByName("system").get();
			UserEntity user = new UserEntity();
			user.setName(username);
			userServiceImpl.save(user);
			
			ConversationEntity conversation = new ConversationEntity();
			conversation.setConversationName(userSystem.getName());
			List<UserEntity> contacts = new ArrayList<>();
			contacts.add(userSystem);
			contacts.add(user);
			conversation.setContacts(contacts);
			convServiceImpl.save(conversation);
			
			user.setConversations(new ArrayList<>() );
			user.getConversations().add(conversation);
			userServiceImpl.save(user);
			userSystem.getConversations().add(conversation);
			userServiceImpl.save(userSystem);
			
		}
		model.addAttribute("userModel",username);
		return "chat";
	}

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String showLoginPage() {
		return "login";
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String doLogin(HttpServletRequest request, @RequestParam(defaultValue = "") String username) {
		username = username.trim();

		if (username.isEmpty()) {
			return "login";
		}
		request.getSession().setAttribute("username", username);

		return "redirect:/chat";
	}

	@RequestMapping(path = "/logout")
	public String logout(HttpServletRequest request) {
		request.getSession(true).invalidate();

		return "redirect:/login";
	}

}
