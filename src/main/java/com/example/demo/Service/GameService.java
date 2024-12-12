package com.example.demo.Service;

import com.example.demo.Entity.Game;
import com.example.demo.DAO.GameDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService implements IGameService {

    @Autowired
    private GameDAO gameDAO;

    @Override
    public Game createGame(Game game) {
        return gameDAO.save(game);
    }

    @Override
    public Game getGameById(Long id) {
        return gameDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found"));
    }

    @Override
    public Game updateGame(Long id, Game game) {
        Game existingGame = getGameById(id);

        existingGame.setDate(game.getDate());
        existingGame.setGameType(game.getGameType());
        existingGame.setMaxScore(game.getMaxScore());
        existingGame.setHostID(game.getHostID());

        return gameDAO.save(existingGame);
    }

    @Override
    public void deleteGame(Long id) {
        Game game = getGameById(id);
        gameDAO.delete(game);
    }
}
