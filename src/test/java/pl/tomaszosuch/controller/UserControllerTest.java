package pl.tomaszosuch.controller;

import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.tomaszosuch.domain.User;
import pl.tomaszosuch.dto.UserDto;
import pl.tomaszosuch.mapper.UserMapper;
import pl.tomaszosuch.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private UserMapper userMapper;

    @Test
    public void testGetAllUser() throws Exception {
        //given
        List<UserDto> userDtoList = List.of(new UserDto(1L, "Jan", "Kowalski","Test", "Test"));
        when(userMapper.mapToUserDtoList(anyList())).thenReturn(userDtoList);
        //when & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/user/getAllUser/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is("Jan")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName", Matchers.is("Kowalski")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cardIdNumber", Matchers.is("Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].drivingLicenseNumber", Matchers.is("Test")));
    }

    @Test
    public void testGetUserById() throws Exception {
        //given
        UserDto userDto = new UserDto(1L, "Jan", "Kowalski","Test", "Test");
        User user = new User(1L, "Jan", "Kowalski","Test", "Test");

        when(userService.getUserById(anyLong())).thenReturn(Optional.of(user));
        when(userMapper.mapToUserDto(any(User.class))).thenReturn(userDto);
        //when & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/user/getUser/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("Jan")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("Kowalski")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cardIdNumber", Matchers.is("Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.drivingLicenseNumber", Matchers.is("Test")));
    }

    @Test
    public void testDeleteUserById() throws Exception {
        //given
        User user = new User(1L, "Jan", "Kowalski","Test", "Test");
        //when & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/user/deleteUser/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUserById(1L);
    }

    @Test
    public void testCreateUser() throws Exception {
        //given
        UserDto userDto = new UserDto(1L, "Jan", "Kowalski","Test", "Test");
        User user = new User(1L, "Jan", "Kowalski","Test", "Test");

        when(userMapper.mapToUser(any(UserDto.class))).thenReturn(user);
        when(userService.saveUser(any(User.class))).thenReturn(user);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);
        //when & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/user/createUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().isOk());

        verify(userService, times(1)).saveUser(user);
    }


}