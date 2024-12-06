package com.example.playerdemo.Repository;


import com.example.playerdemo.Entity.Friend;
import com.example.playerdemo.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findByPlayer(Player player);

    Optional<Friend> findByPlayerIdAndFriendId(Long playerId, Long friendId);
}