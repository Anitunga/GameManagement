package com.example.demo.DAO;

import com.example.demo.Entity.Participation;
import com.example.demo.Repository.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ParticipationDAO implements IDAO<Participation> {

    @Autowired
    private ParticipationRepository participationRepository;

    @Override
    public Participation save(Participation participation) {
        return participationRepository.save(participation);
    }

    @Override
    public Optional<Participation> findById(Long id) {
        return participationRepository.findById(id);
    }

    @Override
    public List<Participation> findAll() {
        return participationRepository.findAll();
    }

    @Override
    public void delete(Participation participation) {
        participationRepository.delete(participation);
    }
}