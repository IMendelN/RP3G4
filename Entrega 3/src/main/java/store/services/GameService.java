package store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import store.models.Game;
import store.repositories.GameRepository;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public List<Game> findAll() {
        return gameRepository.findAll();
    }
}
