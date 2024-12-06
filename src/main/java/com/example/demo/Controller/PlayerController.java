package com.example.playerdemo.Controller;

import com.example.playerdemo.DTO.PlayerDTO;
import com.example.playerdemo.DTO.FriendDTO;
import com.example.playerdemo.Entity.Player;
import com.example.playerdemo.Entity.Friend;
import com.example.playerdemo.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody PlayerDTO playerDTO) {
        return ResponseEntity.ok(playerService.createPlayer(playerDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    @PostMapping("/{id}/friends")
    public ResponseEntity<Friend> addFriend(@PathVariable Long id, @RequestBody FriendDTO friendDTO) {
        return ResponseEntity.ok(playerService.addFriend(id, friendDTO));
    }

    @GetMapping("/{id}/friends")
    public ResponseEntity<List<Friend>> getPlayerFriends(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.getPlayerFriends(id));
    }
}