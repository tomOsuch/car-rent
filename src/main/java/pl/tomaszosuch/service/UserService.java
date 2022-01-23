package pl.tomaszosuch.service;

import pl.tomaszosuch.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User saveUser(final User user);
    User updateUser(final User user);
    void deleteUserById(Long id);
}
