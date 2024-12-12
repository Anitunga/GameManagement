package com.example.demo;

import com.example.demo.Controller.GameController;
import com.example.demo.Entity.Game;
import com.example.demo.Entity.GameType;
import com.example.demo.Service.IGameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class GameControllerTest {

    @Mock
    private IGameService gameService;

    @InjectMocks
    private GameController gameController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createGame_ShouldReturnCreatedGame() {
        // Arrange
        Game game = new Game();
        game.setGameType(GameType.SINGLEPLAYER);
        game.setDate(new Date());
        game.setMaxScore(100);
        game.setHostID(1L);

        Game createdGame = new Game();
        createdGame.setId(1L);
        createdGame.setGameType(game.getGameType());
        createdGame.setDate(game.getDate());
        createdGame.setMaxScore(game.getMaxScore());
        createdGame.setHostID(game.getHostID());

        when(gameService.createGame(any(Game.class))).thenReturn(createdGame);

        // Act
        ResponseEntity<Game> response = gameController.createGame(game);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode());
        assertEquals(createdGame, response.getBody());
    }

    @Test
    void getGame_ShouldReturnGame() {
        // Arrange
        Game game = new Game();
        game.setId(1L);
        game.setGameType(GameType.SINGLEPLAYER);
        game.setDate(new Date());
        game.setMaxScore(100);
        game.setHostID(1L);

        when(gameService.getGameById(1L)).thenReturn(game);

        // Act
        ResponseEntity<Game> response = gameController.getGame(1L);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode());
        assertEquals(game, response.getBody());
    }

    @Test
    void updateGame_ShouldReturnUpdatedGame() {
        // Arrange
        Long gameId = 1L;
        Game game = new Game();
        game.setGameType(GameType.SINGLEPLAYER);
        game.setDate(new Date());
        game.setMaxScore(100);
        game.setHostID(1L);

        Game updatedGame = new Game();
        updatedGame.setId(gameId);
        updatedGame.setGameType(game.getGameType());
        updatedGame.setDate(game.getDate());
        updatedGame.setMaxScore(game.getMaxScore());
        updatedGame.setHostID(game.getHostID());

        when(gameService.updateGame(eq(gameId), any(Game.class))).thenReturn(updatedGame);

        // Act
        ResponseEntity<Game> response = gameController.updateGame(gameId, game);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode());
        assertEquals(updatedGame, response.getBody());
    }

    @Test
    void deleteGame_ShouldReturnOkResponse() {
        // Arrange
        Long gameId = 1L;
        doNothing().when(gameService).deleteGame(gameId);

        // Act
        ResponseEntity<Void> response = gameController.deleteGame(gameId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode());
        verify(gameService, times(1)).deleteGame(gameId);
    }
}