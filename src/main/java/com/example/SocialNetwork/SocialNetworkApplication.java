package com.example.SocialNetwork;

import com.example.SocialNetwork.service.ChatService;
import com.example.SocialNetwork.service.MessageService;
import com.example.SocialNetwork.service.PostService;
import com.example.SocialNetwork.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocialNetworkApplication {
	private final ChatService chatService;
	private final MessageService messageService;
	private final PostService postService;
	private final UserService userService;

    public SocialNetworkApplication(ChatService chatService, MessageService messageService, PostService postService, UserService userService) {
        this.chatService = chatService;
        this.messageService = messageService;
        this.postService = postService;
        this.userService = userService;
    }

	private void populateData(){

	}

    public static void main(String[] args) {
		SpringApplication.run(SocialNetworkApplication.class, args);
	}

}
