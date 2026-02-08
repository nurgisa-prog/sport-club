package com.example.sportclub;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerRepository repository;

    public PlayerController(PlayerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Player> getPlayers() {
        return repository.findAll();
    }

    @PostMapping
    public String addPlayer(@RequestBody Player player) {
        repository.save(player);
        return "Player added";
    }

    @PutMapping("/{id}")
    public String updatePlayer(@PathVariable int id, @RequestBody Player player) {
        boolean updated = repository.update(id, player);
        if (!updated) {
            throw new RuntimeException("Player not found");
        }
        return "Player updated";
    }

    @DeleteMapping("/{id}")
    public String deletePlayer(@PathVariable int id) {
        boolean deleted = repository.delete(id);
        if (!deleted) {
            throw new RuntimeException("Player not found");
        }
        return "Player deleted";
    }
}
