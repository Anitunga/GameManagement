package com.example.playerdemo.DTO;

import lombok.Data;

@Data
public class PlayerDTO {

    private String name;
    private String username;
    private String email;

    // Constructor
    public PlayerDTO() {}

    public PlayerDTO(String name, String username, String email) {
        this.name = name;
        this.username = username;
        this.email = email;
    }
}