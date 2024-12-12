package com.example.demo.Service;

import com.example.demo.Entity.Participation;
import com.example.demo.DTO.ParticipationDTO;

public interface IParticipationService {
    Participation registerParticipation(ParticipationDTO participationDTO);
}
