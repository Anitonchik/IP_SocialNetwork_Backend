package com.example.SocialNetwork.api.mvc;

import java.util.Date;

/**
 * DTO для отображения обработанных сообщений в чате
 * Соответствует логике обработки из React компонента Message
 */
public class ProcessedMessage {

    private Long id;
    private String messageText;
    private String sender;
    private Date createdAt;
    private boolean isEdited;
    private Integer day;
    private String month;
    private String time;
    private boolean showDate;

    private Long chatId;
    private Long userId;
    private String userName;
    private String userAvatarURL;

    // Конструкторы
    public ProcessedMessage() {
    }

    public ProcessedMessage(Long id, String messageText, String sender,
                            Date createdAt, boolean isEdited) {
        this.id = id;
        this.messageText = messageText;
        this.sender = sender;
        this.createdAt = createdAt;
        this.isEdited = isEdited;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isEdited() {
        return isEdited;
    }

    public void setEdited(boolean edited) {
        isEdited = edited;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isShowDate() {
        return showDate;
    }

    public void setShowDate(boolean showDate) {
        this.showDate = showDate;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatarURL() {
        return userAvatarURL;
    }

    public void setUserAvatarURL(String userAvatarURL) {
        this.userAvatarURL = userAvatarURL;
    }

    // Вспомогательные методы

    /**
     * Проверяет, является ли сообщение от текущего пользователя
     * @return true, если sender = "your"
     */
    public boolean isFromCurrentUser() {
        return "your".equals(sender);
    }

    /**
     * Проверяет, является ли сообщение от собеседника
     * @return true, если sender = "stranger"
     */
    public boolean isFromStranger() {
        return "stranger".equals(sender);
    }

    /**
     * Возвращает CSS класс для сообщения в зависимости от отправителя
     * @return "your-message" или "stranger-message"
     */
    public String getMessageCssClass() {
        return isFromCurrentUser() ? "your-message" : "stranger-message";
    }

    /**
     * Форматирует время для отображения (как в React)
     * @return строка времени в формате "HH:mm"
     */
    public String getFormattedTime() {
        if (time != null) {
            return time;
        }
        return formatTime(createdAt);
    }

    private String formatTime(Date date) {
        if (date == null) return "";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }

    /**
     * Форматирует дату для отображения (как в React)
     * @return строка даты в формате "day month"
     */
    public String getFormattedDate() {
        if (day != null && month != null) {
            return day + " " + month;
        }
        return "";
    }

    /**
     * Получает текст сообщения с учетом редактирования
     * @return текст сообщения с пометкой "edited" если нужно
     */
    public String getDisplayText() {
        return messageText;
    }

    @Override
    public String toString() {
        return "ProcessedMessage{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", messageText='" + (messageText != null ?
                messageText.substring(0, Math.min(20, messageText.length())) : "") + "...'" +
                ", createdAt=" + createdAt +
                ", showDate=" + showDate +
                '}';
    }

    // Builder для удобного создания объектов (опционально)
    public static class Builder {
        private ProcessedMessage message;

        public Builder() {
            message = new ProcessedMessage();
        }

        public Builder id(Long id) {
            message.setId(id);
            return this;
        }

        public Builder messageText(String messageText) {
            message.setMessageText(messageText);
            return this;
        }

        public Builder sender(String sender) {
            message.setSender(sender);
            return this;
        }

        public Builder createdAt(Date createdAt) {
            message.setCreatedAt(createdAt);
            return this;
        }

        public Builder isEdited(boolean isEdited) {
            message.setEdited(isEdited);
            return this;
        }

        public Builder day(Integer day) {
            message.setDay(day);
            return this;
        }

        public Builder month(String month) {
            message.setMonth(month);
            return this;
        }

        public Builder time(String time) {
            message.setTime(time);
            return this;
        }

        public Builder showDate(boolean showDate) {
            message.setShowDate(showDate);
            return this;
        }

        public ProcessedMessage build() {
            return message;
        }
    }
}
