package com.example.SocialNetwork.api.mvc;

import com.example.SocialNetwork.api.message.MessageRq;
import com.example.SocialNetwork.api.message.MessageRs;
import com.example.SocialNetwork.entity.UserEntity;
import com.example.SocialNetwork.service.ChatService;
import com.example.SocialNetwork.service.MessageService;
import com.example.SocialNetwork.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ChatMVCController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserChatSession userSession;

    Long userId = 1L;


    @GetMapping("/chat")
    public String home(Model model, HttpSession session) {
        return showChat(1L, model, session);
    }

    @GetMapping("/chat/{chatId}")
    public String showChat(@PathVariable Long chatId,
                           Model model,
                           HttpSession session) {

        var chat = chatService.getEntity(chatId);
        if (!Objects.equals(userId, chat.getFirstUser().getId())) {
            userSession.setLastChat(chat.getFirstUser().getUserName());
        }
        else if (!Objects.equals(userId, chat.getSecondUser().getId())) {
            userSession.setLastChat(chat.getSecondUser().getUserName());
        }

        return loadChatData(chatId, model, session);

    }

    @PostMapping("/chat/send")
    public String sendMessage(@RequestParam Long chatId,
                              @RequestParam String messageText,
                              Model model,
                              HttpSession session) {

        MessageRq message = new MessageRq(chatId, userId, messageText, new Date());
        messageService.create(message);

        return loadChatData(chatId, model, session);
    }

    @PostMapping("/chat/edit/{id}")
    public String editMessage(@PathVariable("id") Long messageId,
                              @RequestParam Long chatId,
                              @RequestParam String messageText,
                              Model model,
                              HttpSession session) {

        var messageRq = new MessageRq(chatId, userId, messageText, new Date());
        messageService.update(messageId, messageRq);

        return loadChatData(chatId, model, session);
    }

    @PostMapping("/chat/delete-message/{id}")
    public String deleteMessage(@PathVariable("id") Long messageId,
                                @RequestParam Long chatId,
                                Model model,
                                HttpSession session) {

        messageService.delete(messageId);
        return loadChatData(chatId, model, session);
    }

    private String loadChatData(Long chatId, Model model, HttpSession session) {
        var chat = chatService.get(chatId, userId);
        var user = userService.getEntity(userId);

        List<MessageRs> messages = messageService.getByChat(chatId);
        List<ProcessedMessage> processedMessages = processMessages(messages, user);

        model.addAttribute("chat", chat);
        model.addAttribute("user", user);
        model.addAttribute("messages", processedMessages);

        return "chat-window";
    }

    private List<ProcessedMessage> processMessages(List<MessageRs> messages, UserEntity user) {
        List<ProcessedMessage> processed = new ArrayList<>();
        Date prevDate = null;

        for (MessageRs msg : messages) {
            ProcessedMessage pm = new ProcessedMessage();
            pm.setId(msg.id());
            pm.setMessageText(msg.messageText());
            pm.setCreatedAt(msg.createdAt());
            pm.setEdited(msg.isEdited());

            if (msg.user().id().equals(user.getId())) {
                pm.setSender("your");
            } else {
                pm.setSender("stranger");
            }

            Calendar cal = Calendar.getInstance();
            cal.setTime(msg.createdAt());
            pm.setDay(cal.get(Calendar.DAY_OF_MONTH));
            pm.setMonth(getMonthShort(cal.get(Calendar.MONTH)));
            pm.setTime(formatTime(msg.createdAt()));

            pm.setShowDate(shouldShowDate(prevDate, msg.createdAt()));
            if (pm.isShowDate()) {
                prevDate = msg.createdAt();
            }

            processed.add(pm);
        }

        return processed;
    }

    private boolean shouldShowDate(Date prevDate, Date currentDate) {
        if (prevDate == null) return true;

        Calendar prevCal = Calendar.getInstance();
        Calendar currCal = Calendar.getInstance();
        prevCal.setTime(prevDate);
        currCal.setTime(currentDate);

        return prevCal.get(Calendar.DAY_OF_MONTH) != currCal.get(Calendar.DAY_OF_MONTH) ||
                prevCal.get(Calendar.MONTH) != currCal.get(Calendar.MONTH);
    }

    private String getMonthShort(int month) {
        String[] monthsShort = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return monthsShort[month];
    }

    private String formatTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }

}