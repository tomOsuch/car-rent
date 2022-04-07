package pl.tomaszosuch.mapper;

import org.springframework.stereotype.Service;
import pl.tomaszosuch.domain.User;
import pl.tomaszosuch.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getCardIdNumber(),
                userDto.getDrivingLicenseNumber()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getCardIdNumber(),
                user.getDrivingLicenseNumber()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> users) {
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    public List<User> mapToUserList(final List<UserDto> userDtoList) {
        return userDtoList.stream()
                .map(this::mapToUser)
                .collect(Collectors.toList());
    }
}
