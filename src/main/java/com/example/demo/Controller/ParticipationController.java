package com.example.demo.Controller;

import com.example.demo.Entity.Participation;
import com.example.demo.DTO.ParticipationDTO;
import com.example.demo.Service.IParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/participations")
public class ParticipationController {

    @Autowired
    private IParticipationService participationService;

    // REGISTER a participation
    @PostMapping
    public ResponseEntity<Participation> registerParticipation(@RequestBody ParticipationDTO participationDTO) {
        return ResponseEntity.ok(participationService.registerParticipation(participationDTO));
    }
}
