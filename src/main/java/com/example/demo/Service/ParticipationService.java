package com.example.demo.Service;

import com.example.demo.DAO.ParticipationDAO;
import com.example.demo.DTO.ParticipationDTO;
import com.example.demo.DTO.PlayerStatsDTO;
import com.example.demo.Entity.Game;
import com.example.demo.Entity.Participation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ParticipationService implements IParticipationService {

    @Autowired
    private ParticipationDAO participationDAO;

    @Autowired
    private IGameService gameService;

    @Autowired
    private WebClient webClient;

    @Override
    public Participation registerParticipation(ParticipationDTO participationDTO) {
        Game game = gameService.getGameById(participationDTO.getGameId());

        Participation participation = new Participation();
        participation.setGame(game);
        participation.setPlayerID(participationDTO.getPlayerId());
        participation.setScore(participationDTO.getScore());
        participation.setVictory(participationDTO.isVictory());

        return participationDAO.save(participation);
    }

    public void completeGame(Long gameId) {
        Game game = gameService.getGameById(gameId);
        List<Participation> participations = participationDAO.findAll();

        for (Participation participation : participations) {
            // Create stats update DTO
            if (participation.getGame() == game) {
                PlayerStatsDTO statsDTO = new PlayerStatsDTO();
                statsDTO.setScore(participation.getScore());
                statsDTO.setVictory(participation.isVictory());

                // Make REST call to Player service
                webClient.put()
                        .uri("http://localhost:8080/api/players/{id}/stats", participation.getPlayerID())
                        .bodyValue(statsDTO)
                        .retrieve()
                        .bodyToMono(Void.class)
                        .subscribe(
                                null,
                                error -> System.err.println("Error updating player stats: " + error.getMessage()));
            }
        }
    }
}