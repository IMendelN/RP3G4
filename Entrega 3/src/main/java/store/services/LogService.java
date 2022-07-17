package store.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import store.models.Log;
import store.repositories.LogRepository;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    /**
     * Salva um log no banco de dados. Caso ocorra algum erro na inserção
     * do objeto, o banco de dados fará um rollback.
     * 
     * @param log o log a ser salvo
     */
    @Transactional
    public void save(Log log) {
        logRepository.save(log);
    }
}
