package com.example.demo.DTO;

import lombok.Data;

@Data
public class ParticipationDTO {
    private Long gameId;
    private Long playerId;
    private int score;
    private boolean victory;
}