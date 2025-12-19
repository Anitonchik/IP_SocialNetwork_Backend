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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class ChatMVCController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    Long userId = 1L;

    // =========== ГЛАВНАЯ СТРАНИЦА ===========
    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        return showChat(1L, model, session);
    }

    // =========== ПОКАЗАТЬ ЧАТ ===========
    @GetMapping("/chat/{chatId}")
    public String showChat(@PathVariable Long chatId,
                           Model model,
                           HttpSession session) {
        return loadChatData(chatId, model, session);
    }

    // =========== ОТПРАВИТЬ СООБЩЕНИЕ ===========
    // ИЗМЕНЕНО: Убрали {chatId} из пути, так как в форме он передается как параметр
    @PostMapping("/chat/send")
    public String sendMessage(@RequestParam Long chatId, // Получаем из формы
                              @RequestParam String messageText,
                              Model model,
                              HttpSession session) {

        MessageRq message = new MessageRq(chatId, userId, messageText, new Date());
        messageService.create(message);

        return loadChatData(chatId, model, session);
    }

    // =========== РЕДАКТИРОВАТЬ СООБЩЕНИЕ ===========
    // ИЗМЕНЕНО: Используем POST вместо PUT (так как в форме method="post")
    @PostMapping("/chat/edit/{id}")
    public String editMessage(@PathVariable("id") Long messageId, // Переименовано для соответствия HTML
                              @RequestParam Long chatId, // Получаем из формы
                              @RequestParam String messageText,
                              Model model,
                              HttpSession session) {

        var messageRq = new MessageRq(chatId, userId, messageText, new Date());
        messageService.update(messageId, messageRq);

        return loadChatData(chatId, model, session);
    }

    // =========== УДАЛИТЬ СООБЩЕНИЕ ===========
    // ИЗМЕНЕНО: Путь соответствует HTML форме
    @PostMapping("/chat/delete-message/{id}")
    public String deleteMessage(@PathVariable("id") Long messageId, // Переименовано для соответствия HTML
                                @RequestParam Long chatId, // Получаем из формы
                                Model model,
                                HttpSession session) {

        messageService.delete(messageId);
        return loadChatData(chatId, model, session);
    }

    // =========== ОБЩИЙ МЕТОД ЗАГРУЗКИ ДАННЫХ ===========
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

    // ... остальные методы (processMessages, shouldShowDate, getMonthShort, formatTime) остаются без изменений
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

    // Внутренний класс для обработанных сообщений
    private static class ProcessedMessage {
        private Long id;
        private String messageText;
        private Date createdAt;
        private boolean isEdited;
        private String sender;
        private int day;
        private String month;
        private String time;
        private boolean showDate;

        // геттеры и сеттеры
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getMessageText() { return messageText; }
        public void setMessageText(String messageText) { this.messageText = messageText; }

        public Date getCreatedAt() { return createdAt; }
        public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

        public boolean isEdited() { return isEdited; }
        public void setEdited(boolean edited) { isEdited = edited; }

        public String getSender() { return sender; }
        public void setSender(String sender) { this.sender = sender; }

        public int getDay() { return day; }
        public void setDay(int day) { this.day = day; }

        public String getMonth() { return month; }
        public void setMonth(String month) { this.month = month; }

        public String getTime() { return time; }
        public void setTime(String time) { this.time = time; }

        public boolean isShowDate() { return showDate; }
        public void setShowDate(boolean showDate) { this.showDate = showDate; }
    }
}