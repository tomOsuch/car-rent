package pl.tomaszosuch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.tomaszosuch.domain.User;
import pl.tomaszosuch.dto.UserDto;
import pl.tomaszosuch.exception.UserNotFoundException;
import pl.tomaszosuch.mapper.UserMapper;
import pl.tomaszosuch.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserServiceImpl userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/getAllUser")
    public List<UserDto> getAllUser() {
        List<User> users = userService.getAllUsers();
        return userMapper.mapToUserDtoList(users);
    }

    @GetMapping("/getUser/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userMapper.mapToUserDto(userService.getUserById(id).orElseThrow(() -> new UserNotFoundException("User not found")));
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PostMapping(value = "/createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userService.saveUser(user);
    }

    @PutMapping("/updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User saveUser = userService.saveUser(user);
        return userMapper.mapToUserDto(saveUser);
    }
}
