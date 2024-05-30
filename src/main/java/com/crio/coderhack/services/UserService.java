package com.crio.coderhack.services;


import com.crio.coderhack.entities.User;
import com.crio.coderhack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        users.sort((u1, u2) -> Integer.compare(u2.getScore(), u1.getScore()));
        return users;
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public User registerUser(User user) {
        if (userRepository.existsById(user.getUserId())) {
            throw new IllegalArgumentException("User ID already exists.");
        }
        user.setScore(0);
        user.setBadges(new HashSet<>());
        return userRepository.save(user);
    }

    public User updateUserScore(String userId, int score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100.");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found."));
        user.setScore(score);
        updateUserBadges(user);
        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found.");
        }
        userRepository.deleteById(userId);
    }

    private void updateUserBadges(User user) {
        int score = user.getScore();
        Set<String> badges = new HashSet<>();
        if (score >= 1 && score < 30) {
            badges.add("Code Ninja");
        }
        if (score >= 30 && score < 60) {
            badges.add("Code Champ");
        }
        if (score >= 60 && score <= 100) {
            badges.add("Code Master");
        }
        user.setBadges(badges);
    }
}
