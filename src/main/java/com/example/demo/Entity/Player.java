package com.example.playerdemo.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    private String email;

    @Enumerated(EnumType.STRING)
    private LevelType level = LevelType.BEGINNER; // Default level is BEGINNER

    private int totalPoints = 0;

    private int totalWins = 0;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Friend> friends = new ArrayList<>();

    // Helper method to calculate level based on totalPoints
    @PrePersist
    @PreUpdate
    public void updateLevel() {
        this.level = LevelType.getLevelByPoints(this.totalPoints);
    }

    // Helper method to add a friend to the player's friend list
    public void addFriend(Friend friend) {
        friends.add(friend);
        friend.setPlayer(this);
    }

    // Helper method to remove a friend from the player's friend list
    public void removeFriend(Friend friend) {
        friends.remove(friend);
        friend.setPlayer(null);
    }
}
