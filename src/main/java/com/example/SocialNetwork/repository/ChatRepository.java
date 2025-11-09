package com.example.SocialNetwork.repository;

import com.example.SocialNetwork.entity.ChatEntity;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.stereotype.Repository;

@Repository
public class ChatRepository extends MapRepository<ChatEntity> {
    


    /*@GetMapping("/userschats/{userId}")
    public List<ChatDTO> getMethodName(@PathVariable int userId) {
        log.debug("Get all user's chats");
        var d = chats.stream().filter(chat -> chat.getParticipants().contains(userId)).toList();
        return d;
    }*/
}
