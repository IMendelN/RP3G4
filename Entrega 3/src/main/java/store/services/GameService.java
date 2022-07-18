package store.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import store.models.Game;
import store.repositories.GameRepository;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    /**
     * Salva um jogo no banco de dados. Caso ocorra algum erro na inserção
     * do objeto, o banco de dados fará um rollback.
     * 
     * @param user o jogo a ser salvo
     */
    @Transactional
    public void save(Game game) {
        gameRepository.save(game);
    }

    /**
     * Deleta um jogo do banco de dados.
     */
    @Transactional
    public void delete(Long id) {
        gameRepository.deleteById(id);
    }

    /**
     * Retorna todos os jogos do banco de dados.
     */
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    /**
     * Retorna um jogo pelo seu ID ou retorna null se não encontrar.
     */
    public Game findById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }
}
