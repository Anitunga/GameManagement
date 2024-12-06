package com.example.playerdemo.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    private Long friendId; // The ID of the friend player

    // Constructor
    public Friend() {}

    public Friend(Player player, Long friendId) {
        this.player = player;
        this.friendId = friendId;
    }
}
