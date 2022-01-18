package pl.tomaszosuch.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.domain.Rent;
import pl.tomaszosuch.domain.User;
import pl.tomaszosuch.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testGetAllUser() {
        //Given
        List<User> userList = List.of(new User("Jan", "Kowalski" , "1234ABC", "ABC1234"));
        when(userRepository.findAll()).thenReturn(userList);
        //When
        List<User> resultFindAll = userService.getAllUsers();
        //Then
        assertEquals(1, resultFindAll.size());
    }

    @Test
    public void testGetByIdUser() {
        //Given
        List<User> userList = List.of(new User(1L, "Jan", "Kowalski" , "1234ABC", "ABC1234", List.of(new Rent())));
        when(userRepository.findById(userList.get(0).getId())).thenReturn(Optional.ofNullable(userList.get(0)));
        //When
        boolean resultFindById = userService.getUserById(1L).isPresent();
        //Then
        assertTrue(resultFindById);
    }

    @Test
    public void testSaveUser() {
        //Given
        User user = new User("Jan", "Kowalski" , "1234ABC", "ABC1234");
        when(userRepository.save(user)).thenReturn(user);
        //When
        User resultSaveUser = userService.saveUser(user);
        //THen
        assertEquals(user.getFirstName(), resultSaveUser.getFirstName());
        assertEquals(user.getLastName(), resultSaveUser.getLastName());
        assertEquals(user.getCardIdNumber(), resultSaveUser.getCardIdNumber());
        assertEquals(user.getDrivingLicenseNumber(), resultSaveUser.getDrivingLicenseNumber());
    }

    @Test
    public void testDeleteUserById() {
        //Given
        User user = new User(1L, "Jan", "Kowalski" , "1234ABC", "ABC1234", List.of(new Rent()));
        Long id = user.getId();
        //When
        userService.deleteUserById(id);
        //Then
        verify(userRepository, times(1)).deleteById(id);
    }
}
