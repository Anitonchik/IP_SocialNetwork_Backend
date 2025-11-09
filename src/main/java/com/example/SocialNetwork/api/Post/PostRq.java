package com.example.SocialNetwork.api.Post;

import com.example.SocialNetwork.api.user.UserRq;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostRq {
        @NotNull
        private Long userId;
        private String postImageURL;
        @NotEmpty
        @Size(min = 1, max = 1000, message = "Text content must not be longer than 1000")
        private String postTextContent;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getPostImageURL() {
            return postImageURL;
        }

        public void setPostImageURL(String postImageURL) {
            this.postImageURL = postImageURL;
        }

        public String getPostTextContent() {
            return postTextContent;
        }

        public void setPostTextContent(String postTextContent) {
            this.postTextContent = postTextContent;
        }

}
