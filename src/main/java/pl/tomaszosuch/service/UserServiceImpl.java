package pl.tomaszosuch.service;

import org.springframework.stereotype.Service;
import pl.tomaszosuch.exception.UserNotFoundException;
import pl.tomaszosuch.domain.User;
import pl.tomaszosuch.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("User with pointed ID does not exist");
        }
    }
}
