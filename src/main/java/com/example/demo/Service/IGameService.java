package com.example.demo.Service;

import com.example.demo.Entity.Game;

public interface IGameService {
    Game createGame(Game game);

    Game updateGame(Long id, Game game);

    void deleteGame(Long id);

    Game getGameById(Long id);
}
