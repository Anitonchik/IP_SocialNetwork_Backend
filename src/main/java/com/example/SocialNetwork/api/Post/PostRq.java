package com.example.SocialNetwork.api.Post;

import com.example.SocialNetwork.api.user.UserRq;
import jakarta.validation.constraints.NotNull;

public class PostRq {
        @NotNull
        private UserRq user;
        private String postImageURL;
        private String postTextContent;

        public UserRq getUser() {
            return user;
        }

        public void setUser(UserRq user) {
            this.user = user;
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
