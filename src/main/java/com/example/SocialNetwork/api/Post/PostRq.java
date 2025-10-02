package com.example.SocialNetwork.api.Post;

import com.example.SocialNetwork.api.user.UserRq;
import jakarta.validation.constraints.NotNull;

public class PostRq {
        @NotNull
        private Long userId;
        private String postImageURL;
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
