package com.example.playerdemo.Service;

import com.example.playerdemo.DTO.PlayerDTO;
import com.example.playerdemo.DTO.FriendDTO;
import com.example.playerdemo.Entity.Player;
import com.example.playerdemo.Entity.Friend;
import com.example.playerdemo.Repository.PlayerRepository;
import com.example.playerdemo.Repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private FriendRepository friendRepository;

    // Create a new player
    public Player createPlayer(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setName(playerDTO.getName());
        player.setUsername(playerDTO.getUsername());
        player.setEmail(playerDTO.getEmail());
        return playerRepository.save(player);
    }

    // Get a player by ID
    public Player getPlayerById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }

    // Get all players
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    // Update a player's details
    public Player updatePlayer(Long id, PlayerDTO playerDTO) {
        Player player = getPlayerById(id);
        player.setName(playerDTO.getName());
        player.setUsername(playerDTO.getUsername());
        player.setEmail(playerDTO.getEmail());
        return playerRepository.save(player);
    }

    // Delete a player
    public void deletePlayer(Long id) {
        Player player = getPlayerById(id);
        playerRepository.delete(player);
    }

    // Add a friend to a player
    public Friend addFriend(Long playerId, FriendDTO friendDTO) {
        Player player = getPlayerById(playerId);
        Friend friend = new Friend(player, friendDTO.getFriendId());
        return friendRepository.save(friend);
    }

    // Get a player's friends list
    public List<Friend> getPlayerFriends(Long playerId) {
        Player player = getPlayerById(playerId);
        return friendRepository.findByPlayer(player);
    }
}
