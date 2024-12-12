package com.example.demo;

import com.example.demo.Controller.ParticipationController;
import com.example.demo.DTO.ParticipationDTO;
import com.example.demo.Entity.Game;
import com.example.demo.Entity.Participation;
import com.example.demo.Service.IParticipationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ParticipationControllerTest {

    @Mock
    private IParticipationService participationService;

    @InjectMocks
    private ParticipationController participationController;

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

        Participation participation = new Participation();
        participation.setId(1L);
        participation.setPlayerID(participationDTO.getPlayerId());
        participation.setScore(participationDTO.getScore());
        participation.setVictory(participationDTO.isVictory());
        participation.setGame(new Game()); // You might want to set game properties

        when(participationService.registerParticipation(any(ParticipationDTO.class))).thenReturn(participation);

        // Act
        ResponseEntity<Participation> response = participationController.registerParticipation(participationDTO);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode());
        assertEquals(participation, response.getBody());
    }
}
