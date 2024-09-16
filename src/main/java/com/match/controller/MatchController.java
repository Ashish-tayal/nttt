package com.match.controller;

import com.match.entity.Match;
import com.match.exception.ResourceNotFoundException;
import com.match.service.MatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "Match Management System", description = "Operations pertaining to matches in the Match Management System")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping("/matches")
    @ApiOperation(value = "Create a new match", response = Match.class)
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        Match createdMatch = matchService.createMatch(match);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMatch);
    }

    @GetMapping("/matches/{id}")
    @ApiOperation(value = "Get a match by ID", response = Match.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved match"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<Match> getMatchById(@PathVariable Long id) throws ResourceNotFoundException {
        Match match = matchService.getMatchById(id);
        return ResponseEntity.ok(match);
    }

    @PutMapping("/matches/{id}")
    @ApiOperation(value = "Update an existing match", response = Match.class)
    public ResponseEntity<Match> updateMatch(@PathVariable Long id, @RequestBody Match matchDetails) throws ResourceNotFoundException {
        Match updatedMatch = matchService.updateMatch(id, matchDetails);
        return ResponseEntity.ok(updatedMatch);
    }

    @DeleteMapping("/matches//{id}")
    @ApiOperation(value = "Delete a match", response = Void.class)
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/matches/")
    @ApiOperation(value = "View a list of available matches", response = List.class)
    public ResponseEntity<List<Match>> getAllMatches() {
        List<Match> matches = matchService.getAllMatches();
        return ResponseEntity.ok(matches);
    }
}
