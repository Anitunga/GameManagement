package com.example.demo.DTO;

import com.example.demo.Entity.GameType;
import lombok.Data;

import java.util.Date;

@Data
public class GameDTO {
    private Date date;
    private GameType gameType;
    private int maxScore;
    private Long hostId;
}