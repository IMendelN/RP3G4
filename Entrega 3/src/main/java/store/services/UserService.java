package store.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import store.models.User;
import store.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Salva um usuário no banco de dados. Caso ocorra algum erro na inserção
     * do objeto, o banco de dados fará um rollback.
     * 
     * @param user o usuário a ser salvo
     */
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * Retorna todos os usuários do banco de dados.
     * 
     * @return a lista de usuários
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Retorna um usuário do banco de dados. Caso o usuário não seja
     * encontrado, retorna null.
     */
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Verifica se o usuário digitou e-mail e senhas corretamente. 
     * 
     * Retorna um usuário do banco de dados. Caso o usuário não seja
     * encontrado com as credenciais informadas, retorna null.
     * 
     * @param email o e-mail do usuário
     * @param password a senha do usuário
     * @return o usuário com as credenciais informadas ou null
     */
    public User login(String email, String password) {
        return userRepository.login(email, password);
    }

    /**
     * Verifica se o e-mail informado já está cadastrado no banco de dados.
     * 
     * Caso o usuário não exista, retorna null.
     */
    public User findByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Remove um usuário do banco de dados.
     */
    public void remove(User user) {
        userRepository.delete(user);
    }
}
