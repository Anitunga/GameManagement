package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    // @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private long playerID;

    private int score;
    private boolean victory;
}