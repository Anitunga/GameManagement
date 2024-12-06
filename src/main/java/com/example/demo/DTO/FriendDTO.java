package com.example.playerdemo.DTO;

import lombok.Data;

@Data
public class FriendDTO {

    private Long friendId; // The ID of the friend to be added

    // Constructor
    public FriendDTO() {}

    public FriendDTO(Long friendId) {
        this.friendId = friendId;
    }
}