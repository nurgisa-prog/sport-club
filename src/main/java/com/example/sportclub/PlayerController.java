package com.example.sportsclubapi;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {

    private final PlayerRepository repository;

    public PlayerController(PlayerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/players")
    public List<Player> getPlayers() {
        return repository.findAll();
    }

    @PostMapping("/players")
    public String addPlayer(@RequestBody Player player) {
        repository.save(player);
        return "Player added";
    }
}
