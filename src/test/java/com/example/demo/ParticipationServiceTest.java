package com.example.demo;

import com.example.demo.DAO.ParticipationDAO;
import com.example.demo.DTO.ParticipationDTO;
import com.example.demo.Entity.Game;
import com.example.demo.Entity.Participation;
import com.example.demo.Service.IGameService;
import com.example.demo.Service.ParticipationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ParticipationServiceTest {

    @Mock
    private ParticipationDAO participationDAO;

    @Mock
    private IGameService gameService;

    @InjectMocks
    private ParticipationService participationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerParticipation_ShouldReturnCreatedParticipation() {
        // Arrange
        ParticipationDTO participationDTO = new ParticipationDTO();
        participationDTO.setGameId(1L);
        participationDTO.setPlayerId(1L);
        participationDTO.setScore(100);
        participationDTO.setVictory(true);

        Game game = new Game();
        game.setId(1L);

        Participation savedParticipation = new Participation();
        savedParticipation.setId(1L);
        savedParticipation.setPlayerID(participationDTO.getPlayerId());
        savedParticipation.setScore(participationDTO.getScore());
        savedParticipation.setVictory(participationDTO.isVictory());
        savedParticipation.setGame(game);

        when(gameService.getGameById(participationDTO.getGameId())).thenReturn(game);
        when(participationDAO.save(any(Participation.class))).thenReturn(savedParticipation);

        // Act
        Participation result = participationService.registerParticipation(participationDTO);

        // Assert
        assertNotNull(result);
        assertEquals(savedParticipation.getId(), result.getId());
        assertEquals(participationDTO.getPlayerId(), result.getPlayerID());
        assertEquals(participationDTO.getScore(), result.getScore());
        assertEquals(participationDTO.isVictory(), result.isVictory());
        assertEquals(game, result.getGame());
    }
}
