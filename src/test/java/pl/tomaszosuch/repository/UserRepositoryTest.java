package pl.tomaszosuch.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.domain.User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUserFindAll() {
        //Given
        User user = new User("Jan", "Kowalski" , "1234ABC", "ABC1234");
        userRepository.save(user);
        Long idUser = user.getId();
        //When
        List<User> resultUserList = userRepository.findAll();
        //Then
        assertEquals(1, resultUserList.size());
        //CleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testUserFindByIdAndSave() {
        //Given
        User user = new User("Jan", "Kowalski" , "1234ABC", "ABC1234");
        userRepository.save(user);
        Long userId = user.getId();
        //When
        Optional<User> resultFindById = userRepository.findById(userId);
        //Then
        assertTrue(resultFindById.isPresent());
        //CleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testUserDelete() {
        //Given
        User user = new User("Jan", "Kowalski" , "1234ABC", "ABC1234");
        userRepository.save(user);
        Long userId = user.getId();
        //When
        userRepository.deleteById(userId);
        Optional<User> resultDeleteById = userRepository.findById(userId);
        //Then
        assertFalse(resultDeleteById.isPresent());
    }

    @Test
    public void testUserFindByLastName() {

    }
}
