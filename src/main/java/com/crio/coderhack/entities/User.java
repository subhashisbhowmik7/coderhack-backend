package com.crio.coderhack.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
public class User {
    @Id
    private String userId;

    private String username;
    private int score;
    private Set<String> badges;

    public User() {
        this.score = 0;
        this.badges = new HashSet<>();
    }

    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
        this.score = 0;
        this.badges = new HashSet<>();
    }

    // Getters and Setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Set<String> getBadges() {
        return badges;
    }

    public void setBadges(Set<String> badges) {
        this.badges = badges;
    }

    public void addBadge(String badge) {
        this.badges.add(badge);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", score=" + score +
                ", badges=" + badges +
                '}';
    }
}
