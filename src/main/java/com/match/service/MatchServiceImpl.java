package com.match.service;

import com.match.entity.Match;
import com.match.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.match.repository.MatchRepository;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService{
    @Autowired
    private MatchRepository matchRepository;

    public Match createMatch(Match match) {
        return matchRepository.save(match);
    }

    public Match getMatchById(Long id) throws ResourceNotFoundException {
        return matchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Match not found"));
    }

    public Match updateMatch(Long id, Match matchDetails) throws ResourceNotFoundException {
        Match match = matchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Match not found"));
        match.setTeamA(matchDetails.getTeamA());
        match.setTeamB(matchDetails.getTeamB());
        match.setDate(matchDetails.getDate());
        return matchRepository.save(match);
    }

    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }
}
