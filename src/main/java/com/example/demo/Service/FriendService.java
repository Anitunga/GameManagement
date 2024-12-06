package com.example.playerdemo.Service;

import com.example.playerdemo.Entity.Player;
import com.example.playerdemo.Entity.Friend;
import com.example.playerdemo.Repository.PlayerRepository;
import com.example.playerdemo.Repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private FriendRepository friendRepository;

    // Add a friend to a player
    public Friend addFriend(Long playerId, Long friendId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        Player friendPlayer = playerRepository.findById(friendId)
                .orElseThrow(() -> new RuntimeException("Friend player not found"));

        // Create a new friend entity and save it
        Friend friend = new Friend(player, friendId);
        return friendRepository.save(friend);
    }

    // Remove a friend from a player
    public void removeFriend(Long playerId, Long friendId) {
        Friend friend = friendRepository.findByPlayerIdAndFriendId(playerId, friendId)
                .orElseThrow(() -> new RuntimeException("Friendship not found"));

        friendRepository.delete(friend);
    }

    // Get a player's friends list
    public List<Friend> getPlayerFriends(Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        return friendRepository.findByPlayer(player);
    }
}