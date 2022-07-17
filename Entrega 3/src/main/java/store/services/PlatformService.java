package store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import store.models.Platform;
import store.repositories.PlatformRepository;

@Service
public class PlatformService {
    @Autowired
    private PlatformRepository platformRepository;
    
    public List<Platform> findAll() {
        return platformRepository.findAll();
    }

    public Platform findById(Long id) {
        return platformRepository.findById(id).orElse(null);
    }
}
