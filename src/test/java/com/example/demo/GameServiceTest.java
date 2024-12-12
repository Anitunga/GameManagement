package com.example.demo;

import com.example.demo.DAO.GameDAO;
import com.example.demo.Entity.Game;
import com.example.demo.Entity.GameType;
import com.example.demo.Service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GameServiceTest {

    @Mock
    private GameDAO gameDAO;

    @InjectMocks
    private GameService gameService;

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

        Game savedGame = new Game();
        savedGame.setId(1L);
        savedGame.setGameType(game.getGameType());
        savedGame.setDate(game.getDate());
        savedGame.setMaxScore(game.getMaxScore());
        savedGame.setHostID(game.getHostID());

        when(gameDAO.save(any(Game.class))).thenReturn(savedGame);

        // Act
        Game result = gameService.createGame(game);

        // Assert
        assertNotNull(result);
        assertEquals(savedGame.getId(), result.getId());
        assertEquals(game.getGameType(), result.getGameType());
        assertEquals(game.getDate(), result.getDate());
        assertEquals(game.getMaxScore(), result.getMaxScore());
        assertEquals(game.getHostID(), result.getHostID());
    }

    @Test
    void getGameById_ShouldReturnGame() {
        // Arrange
        Game game = new Game();
        game.setId(1L);
        game.setGameType(GameType.SINGLEPLAYER);
        game.setDate(new Date());
        game.setMaxScore(100);
        game.setHostID(1L);

        when(gameDAO.findById(1L)).thenReturn(Optional.of(game));

        // Act
        Game result = gameService.getGameById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(game.getId(), result.getId());
        assertEquals(game.getGameType(), result.getGameType());
        assertEquals(game.getDate(), result.getDate());
        assertEquals(game.getMaxScore(), result.getMaxScore());
        assertEquals(game.getHostID(), result.getHostID());
    }

    @Test
    void getGameById_ShouldThrowException_WhenGameNotFound() {
        // Arrange
        when(gameDAO.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> gameService.getGameById(1L));
    }

    @Test
    void updateGame_ShouldReturnUpdatedGame() {
        // Arrange
        Long gameId = 1L;
        Game existingGame = new Game();
        existingGame.setId(gameId);
        existingGame.setGameType(GameType.SINGLEPLAYER);
        existingGame.setDate(new Date());
        existingGame.setMaxScore(100);
        existingGame.setHostID(1L);

        Game updatedGame = new Game();
        updatedGame.setGameType(GameType.MULTIPLAYER);
        updatedGame.setDate(new Date());
        updatedGame.setMaxScore(200);
        updatedGame.setHostID(2L);

        Game savedGame = new Game();
        savedGame.setId(gameId);
        savedGame.setGameType(updatedGame.getGameType());
        savedGame.setDate(updatedGame.getDate());
        savedGame.setMaxScore(updatedGame.getMaxScore());
        savedGame.setHostID(updatedGame.getHostID());

        when(gameDAO.findById(gameId)).thenReturn(Optional.of(existingGame));
        when(gameDAO.save(any(Game.class))).thenReturn(savedGame);

        // Act
        Game result = gameService.updateGame(gameId, updatedGame);

        // Assert
        assertNotNull(result);
        assertEquals(gameId, result.getId());
        assertEquals(updatedGame.getGameType(), result.getGameType());
        assertEquals(updatedGame.getDate(), result.getDate());
        assertEquals(updatedGame.getMaxScore(), result.getMaxScore());
        assertEquals(updatedGame.getHostID(), result.getHostID());
    }

    @Test
    void deleteGame_ShouldDeleteSuccessfully() {
        // Arrange
        Long gameId = 1L;
        Game game = new Game();
        game.setId(gameId);

        when(gameDAO.findById(gameId)).thenReturn(Optional.of(game));
        doNothing().when(gameDAO).delete(game);

        // Act & Assert
        assertDoesNotThrow(() -> gameService.deleteGame(gameId));
        verify(gameDAO, times(1)).delete(game);
    }
}
