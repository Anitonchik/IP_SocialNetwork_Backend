package com.example.SocialNetwork;

import com.example.SocialNetwork.api.chat.ChatRq;
import com.example.SocialNetwork.api.post.PostRq;
import com.example.SocialNetwork.api.message.MessageRq;
import com.example.SocialNetwork.api.user.UserRq;
import com.example.SocialNetwork.service.ChatService;
import com.example.SocialNetwork.service.MessageService;
import com.example.SocialNetwork.service.PostService;
import com.example.SocialNetwork.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.Objects;

@SpringBootApplication
public class SocialNetworkApplication implements CommandLineRunner {
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
		final var user1 = userService.create(new UserRq("fN", "lN", "ooop", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRaU6TxrGCihlR0z8AtG6hqjUnXbmXP1jlPQA&s",
				"desc", "+7 (999) 999-99-99"));
		final var user2 = userService.create(new UserRq("fN", "lN", "pp", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQMXNoPlWCGr5Bq21PgyZ8qdPMn33QwISRp5g&s",
				"desc", "+7 (999) 999-99-98"));
		final var user3 = userService.create(new UserRq("fN", "lN", "asdfh", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRSu3iRu_bfAoSNyQySyiVOh8uHrAdJX5BkrA&s",
				"desc", "+7 (999) 999-99-97"));
		final var user4 = userService.create(new UserRq("fN", "lN", "pou", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQUBZ4KmdoWceKPI69RDlzV-QieP-GOkYVdnw&s",
				"desc", "+7 (999) 999-99-96"));

		userService.createSubscription(1L, 2L);
		userService.createSubscription(1L, 3L);
		userService.createSubscription(1L, 4L);
		userService.createSubscription(4L, 1L);

		final var post1 = postService.create(new PostRq(1L, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRR6mnq4H6pO_Ii0O5Qe8ZpzH2gKeMfJ7irIw&s", "ddcs"));
		final var post2 = postService.create(new PostRq(1L, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR9-OqGQLZzoAWuNADV6-tiUGCwdmVKYtT4ew&s", "ddcs"));
		final var post3 = postService.create(new PostRq(1L, "https://kartinki.pics/uploads/posts/2022-02/1644939485_55-kartinkin-net-p-estetichnie-kartinki-pinterest-59.jpg", "ddcs"));
		final var post22 = postService.create(new PostRq(1L, "https://kartinki.pics/uploads/posts/2022-02/1644939485_55-kartinkin-net-p-estetichnie-kartinki-pinterest-59.jpg", "ddcs"));
		final var post33 = postService.create(new PostRq(1L, "https://kartinki.pics/uploads/posts/2022-02/1644939485_55-kartinkin-net-p-estetichnie-kartinki-pinterest-59.jpg", "ddcs"));
		final var post32 = postService.create(new PostRq(1L, "https://kartinki.pics/uploads/posts/2022-02/1644939485_55-kartinkin-net-p-estetichnie-kartinki-pinterest-59.jpg", "6"));
		final var post34 = postService.create(new PostRq(1L, "https://kartinki.pics/uploads/posts/2022-02/1644939485_55-kartinkin-net-p-estetichnie-kartinki-pinterest-59.jpg", "ddcs"));
		final var post35 = postService.create(new PostRq(1L, "https://kartinki.pics/uploads/posts/2022-02/1644939485_55-kartinkin-net-p-estetichnie-kartinki-pinterest-59.jpg", "ddcs"));

		final var post4 = postService.create(new PostRq(2L, "https://kartinki.pics/uploads/posts/2022-02/1644939485_55-kartinkin-net-p-estetichnie-kartinki-pinterest-59.jpg", "1"));
        final var post5 = postService.create(new PostRq(2L, "https://kartinki.pics/uploads/posts/2022-02/1644939485_55-kartinkin-net-p-estetichnie-kartinki-pinterest-59.jpg", "2"));
		final var post6 = postService.create(new PostRq(2L, "https://kartinki.pics/uploads/posts/2022-02/1644939485_55-kartinkin-net-p-estetichnie-kartinki-pinterest-59.jpg", "3"));
		final var post7 = postService.create(new PostRq(2L, "https://kartinki.pics/uploads/posts/2022-02/1644939485_55-kartinkin-net-p-estetichnie-kartinki-pinterest-59.jpg", "4"));
		final var post8 = postService.create(new PostRq(2L, "https://kartinki.pics/uploads/posts/2022-02/1644939485_55-kartinkin-net-p-estetichnie-kartinki-pinterest-59.jpg", "5"));
		final var post9 = postService.create(new PostRq(2L, "https://kartinki.pics/uploads/posts/2022-02/1644939485_55-kartinkin-net-p-estetichnie-kartinki-pinterest-59.jpg", "6"));
		final var post10 = postService.create(new PostRq(2L, "https://kartinki.pics/uploads/posts/2022-02/1644939485_55-kartinkin-net-p-estetichnie-kartinki-pinterest-59.jpg", "7"));
		final var post11 = postService.create(new PostRq(2L, "https://kartinki.pics/uploads/posts/2022-02/1644939485_55-kartinkin-net-p-estetichnie-kartinki-pinterest-59.jpg", "8"));
		final var post12 = postService.create(new PostRq(2L, "https://kartinki.pics/uploads/posts/2022-02/1644939485_55-kartinkin-net-p-estetichnie-kartinki-pinterest-59.jpg", "9"));
		final var post13 = postService.create(new PostRq(2L, "https://kartinki.pics/uploads/posts/2022-02/1644939485_55-kartinkin-net-p-estetichnie-kartinki-pinterest-59.jpg", "10"));
		final var post14 = postService.create(new PostRq(2L, "https://kartinki.pics/uploads/posts/2022-02/1644939485_55-kartinkin-net-p-estetichnie-kartinki-pinterest-59.jpg", "11"));


		final var chat1 = chatService.create(new ChatRq(new Date(),  user1.id(),  user2.id()),  user1.id());
		final var chat2 = chatService.create(new ChatRq(new Date(),  user1.id(),  user3.id()),  user1.id());
		final var chat3 = chatService.create(new ChatRq(new Date(),  user1.id(),  user4.id()),  user1.id());
		final var chat4 = chatService.create(new ChatRq(new Date(),  user2.id(),  user3.id()),  user2.id());

		final var message1 = messageService.create(new MessageRq(chat1.id(), user1.id(), "blablabla", new Date()));
		final var message2 = messageService.create(new MessageRq(chat1.id(), user1.id(), "bleblebleblbleble", new Date()));
		final var message3 = messageService.create(new MessageRq(chat1.id(), user2.id(), "dbns;gjbnkjgbn", new Date()));
		final var message4 = messageService.create(new MessageRq(chat1.id(), user1.id(), "uuuuuuuuu", new Date()));
		final var message5 = messageService.create(new MessageRq(chat2.id(), user3.id(), ";fvns;kbjn", new Date()));
		final var message6 = messageService.create(new MessageRq(chat2.id(), user1.id(), "sbvmmbm", new Date()));
		final var message7 = messageService.create(new MessageRq(chat2.id(), user1.id(), "[sdobj", new Date()));
		final var message8 = messageService.create(new MessageRq(chat3.id(), user1.id(), "apfiuvhapfiuvh", new Date()));

	}

    public static void main(String[] args) {
		SpringApplication.run(SocialNetworkApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		populateData();
		if (args.length == 0) {
			return;
		}
		if (Objects.equals("--populate", args[0])) {
			populateData();
		}
	}

}
