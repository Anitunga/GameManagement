package com.example.demo.DTO;

import lombok.Data;

@Data
public class PlayerDTO {
    private Long playerID;
    private String name;
    private String username;
    private String email;
    private String level;
    private int totalPoints;
    private int totalWins;

    // Constructors
    public PlayerDTO() {
    }

    public PlayerDTO(Long playerID, String name, String username, String email, String level, int totalPoints,
            int totalWins) {
        this.playerID = playerID;
        this.name = name;
        this.username = username;
        this.email = email;
        this.level = level;
        this.totalPoints = totalPoints;
        this.totalWins = totalWins;
    }
}
