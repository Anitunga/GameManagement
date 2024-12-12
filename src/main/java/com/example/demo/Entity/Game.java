package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;
//import com.example.playerdemo.Entity.Player;
import com.example.demo.DTO.PlayerDTO;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @Enumerated(EnumType.STRING)
    private GameType gameType; // enum: multiplayer, singleplayer

    private int maxScore;

    @JoinColumn(name = "host_id", nullable = false)
    private long hostID; // referencing the player who created the game

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Participation> participations;
}