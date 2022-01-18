package pl.tomaszosuch.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.domain.Rent;
import pl.tomaszosuch.domain.User;
import pl.tomaszosuch.dto.UserDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapToUserDto() {
        //Given
        User user = new User(1L, "Jan", "Kowalski","Test", "Test", List.of(new Rent()));
        //When
        UserDto userDto = userMapper.mapToUserDto(user);
        //Then
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getCardIdNumber(), userDto.getCardIdNumber());
        assertEquals(user.getDrivingLicenseNumber(), userDto.getDrivingLicenseNumber());
    }

    @Test
    public void testUserDtoMapToUser() {
        //Given
        UserDto userDto = new UserDto(1L, "Jan", "Kowalski","Test", "Test");
        //When
        User user = userMapper.mapToUser(userDto);
        //Then
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getCardIdNumber(), userDto.getCardIdNumber());
        assertEquals(user.getDrivingLicenseNumber(), userDto.getDrivingLicenseNumber());
    }

    @Test
    public void testMapToUserDtoList() {
        //Given
        List<User> userList = List.of(new User(1L, "Jan", "Kowalski","Test", "Test", List.of(new Rent())));
        //When
        List<UserDto> userDtoList = userMapper.mapToUserDtoList(userList);
        //Then
        assertEquals(userList.get(0).getFirstName(), userDtoList.get(0).getFirstName());
        assertEquals(userList.get(0).getLastName(), userDtoList.get(0).getLastName());
    }

    @Test
    public void testMapToUserList() {
        //Given
        List<UserDto> userDtoList = List.of(new UserDto(1L, "Jan", "Kowalski","Test", "Test"));
        //When
        List<User> userList = userMapper.mapToUserList(userDtoList);
        //Then
        assertEquals(userList.get(0).getFirstName(), userDtoList.get(0).getFirstName());
        assertEquals(userList.get(0).getLastName(), userDtoList.get(0).getLastName());
    }
}
