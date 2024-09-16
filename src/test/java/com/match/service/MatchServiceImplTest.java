package com.match.service;

import com.match.entity.Match;
import com.match.exception.ResourceNotFoundException;
import com.match.repository.MatchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MatchServiceImplTest {

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private MatchServiceImpl matchService;

    @Test
    void testCreateMatch() {
        Match match = new Match();
        when(matchRepository.save(any(Match.class))).thenReturn(match);
        Match created = matchService.createMatch(new Match());
        assertNotNull(created);
        verify(matchRepository).save(any(Match.class));
    }

    @Test
    void testGetMatchById() throws ResourceNotFoundException {
        Match match = new Match();
        match.setId(1L);
        when(matchRepository.findById(1L)).thenReturn(Optional.of(match));
        Match found = matchService.getMatchById(1L);
        assertNotNull(found);
        assertEquals(1L, found.getId());
    }

    @Test
    void testGetMatchById_NotFound() {
        when(matchRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> matchService.getMatchById(1L));
    }

    @Test
    void testUpdateMatch() throws ResourceNotFoundException {
        Match existingMatch = new Match();
        existingMatch.setId(1L);
        existingMatch.setTeamA("Team A");
        existingMatch.setTeamB("Team B");

        Match newDetails = new Match();
        newDetails.setTeamA("New Team A");
        newDetails.setTeamB("New Team B");

        when(matchRepository.findById(1L)).thenReturn(Optional.of(existingMatch));
        when(matchRepository.save(any(Match.class))).thenReturn(existingMatch);

        Match updated = matchService.updateMatch(1L, newDetails);
        assertNotNull(updated);
        assertEquals("New Team A", updated.getTeamA());
        assertEquals("New Team B", updated.getTeamB());
        verify(matchRepository).save(any(Match.class));
    }

}