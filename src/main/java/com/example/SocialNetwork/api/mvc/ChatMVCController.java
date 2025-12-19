package com.example.SocialNetwork.api.mvc;

import com.example.SocialNetwork.api.message.MessageRq;
import com.example.SocialNetwork.api.message.MessageRs;
import com.example.SocialNetwork.entity.UserEntity;
import com.example.SocialNetwork.repository.ChatRepository;
import com.example.SocialNetwork.service.ChatService;
import com.example.SocialNetwork.service.MessageService;
import com.example.SocialNetwork.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatMVCController {


    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    private Long userId;

    @GetMapping("/{chatId}")
    public String getChatWindow(@PathVariable Long chatId,
                                Model model,
                                HttpSession session) {
        userId = 1L;

        var chat = chatService.get(chatId, userId);
        var user = userService.getEntity(userId);

        List<MessageRs> messages = messageService.getByChat(chatId);

        List<ProcessedMessage> processedMessages = processMessages(messages, user);

        model.addAttribute("chat", chat);
        model.addAttribute("user", user);
        model.addAttribute("messages", processedMessages);
        model.addAttribute("monthsShort", new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});

        return "chat-window";
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam Long chatId,
                              @RequestParam String messageText,
                              HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");


        var chat = chatService.getEntity(chatId);
        var user = userService.getEntity(userId);

        MessageRq message = new MessageRq(chatId, userId, messageText, new Date());

        messageService.create(message);

        return "redirect:/chat/" + chatId;
    }

    @PostMapping("/edit/{messageId}")
    public String editMessage(@PathVariable Long messageId,
                              @RequestParam String messageText,
                              @RequestParam Long chatId) {
        var messageRg = new MessageRq(chatId, userId, messageText, new Date());
        messageService.update(messageId, messageRg);
        return "redirect:/chat/" + chatId;
    }

    @PostMapping("/delete-message/{messageId}")
    public String deleteMessage(@PathVariable Long messageId,
                                @RequestParam Long chatId) {
        messageService.delete(messageId);
        return "redirect:/chat/" + chatId;
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

