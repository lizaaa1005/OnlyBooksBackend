package bs.lf10.service;

import bs.lf10.entity.User;
import bs.lf10.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Benutzername bereits vergeben!");
        }
        return userRepository.save(user);
    }

    public User loginUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new RuntimeException("Ungültiger Benutzername oder Passwort!");
    }
}