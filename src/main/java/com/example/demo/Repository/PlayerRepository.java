package com.example.playerdemo.Repository;

import com.example.playerdemo.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}