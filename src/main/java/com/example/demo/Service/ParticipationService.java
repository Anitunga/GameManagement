package com.example.demo.Service;

import com.example.demo.DAO.ParticipationDAO;
import com.example.demo.DTO.ParticipationDTO;
//import com.example.demo.DTO.PlayerDTO;
import com.example.demo.Entity.Game;
import com.example.demo.Entity.Participation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipationService implements IParticipationService {

    @Autowired
    private ParticipationDAO participationDAO;

    @Autowired
    private IGameService gameService;

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

    /*
     * private void updatePlayerStats(Participation participation, Game game) {
     * PlayerDTO statsDTO = new PlayerDTO();
     * statsDTO.setPlayerID(participation.getPlayerID());
     * statsDTO.setTotalPoints(participation.getScore());
     * statsDTO.setTotalWins(participation.isVictory() ? 1 : 0); // count the number
     * of victories
     * // statsDTO.setGameType(game.getGameType().toString());
     * 
     * // Make REST call to Player service to update statistics
     * playerServiceWebClient.put()
     * .uri("/players/{id}/stats", participation.getPlayerID())
     * .bodyValue(statsDTO)
     * .retrieve()
     * .bodyToMono(Void.class)
     * .subscribe(
     * null,
     * error -> System.err.println("Error updating player stats: " +
     * error.getMessage()));
     * }
     */
}