package com.match.service;

import com.match.entity.Match;
import com.match.exception.ResourceNotFoundException;

import java.util.List;

public interface MatchService {

     Match createMatch(Match match) ;
     Match getMatchById(Long id) throws ResourceNotFoundException;
     Match updateMatch(Long id, Match matchDetails) throws ResourceNotFoundException;
     void deleteMatch(Long id);
     List<Match> getAllMatches();
}
