package com.example.individualproject.controller;

import com.example.individualproject.business.impl.UserServiceImpl;
import com.example.individualproject.dto.users.*;
import com.example.individualproject.repository.entity.Admin;
import com.example.individualproject.repository.entity.NormalUser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserServiceImpl userService;

    //getAllUsers
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getAllUsers_UsersFound() throws Exception {
        NormalUser worker = new NormalUser(1l, "worker","worker","worker","worker","worker","worker");
        Admin boss = new Admin(2l, "boss","boss");

        GetUserDTO workerDTO = GetUserDTO.builder()
                .username(worker.getUsername())
                .firstName(worker.getFirstname())
                .lastName(worker.getLastname())
                .phoneNumber(worker.getPhonenumber())
                .email(worker.getEmail())
                .position("NORMAL")
                .build();

        String none = "-None-";
        GetUserDTO bossDTO = GetUserDTO.builder()
                .username(boss.getUsername())
                .firstName(none)
                .lastName(none)
                .phoneNumber(none)
                .email(none)
                .position("ADMIN")
                .build();

        List<GetUserDTO> users = List.of(workerDTO, bossDTO);

        when(userService.getAllUsers())
                .thenReturn(users);

        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                [{"username":"worker","firstName":"worker","lastName":"worker","phoneNumber":"worker","email":"worker","position":"NORMAL"},{"username":"boss","firstName":"-None-","lastName":"-None-","phoneNumber":"-None-","email":"-None-","position":"ADMIN"}]
                """ ));

        verify(userService).getAllUsers();
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getAllUsers_NotFound() throws Exception {
        when(userService.getAllUsers())
                .thenReturn(null);

        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(userService).getAllUsers();
    }

    //getAllUserByName
    @Test
    @WithMockUser(username = "me", roles = {"ADMIN"})
    void getAllUserByName_UserFound() throws Exception {
        NormalUser worker = new NormalUser(1l, "worker","worker","worker","worker","worker","worker");
        Admin boss = new Admin(2l, "boss","boss");

        GetUserDTO workerDTO = GetUserDTO.builder()
                .username(worker.getUsername())
                .firstName(worker.getFirstname())
                .lastName(worker.getLastname())
                .phoneNumber(worker.getPhonenumber())
                .email(worker.getEmail())
                .position("NORMAL")
                .build();

        String none = "-None-";
        GetUserDTO bossDTO = GetUserDTO.builder()
                .username(boss.getUsername())
                .firstName(none)
                .lastName(none)
                .phoneNumber(none)
                .email(none)
                .position("ADMIN")
                .build();

        List<GetUserDTO> users = List.of(workerDTO, bossDTO);

        when(userService.getAllUserByName("worker"))
                .thenReturn(users);

        mockMvc.perform(get("/users/search/worker"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                [{"username":"worker","firstName":"worker","lastName":"worker","phoneNumber":"worker","email":"worker","position":"NORMAL"},{"username":"boss","firstName":"-None-","lastName":"-None-","phoneNumber":"-None-","email":"-None-","position":"ADMIN"}]
                """ ));

        verify(userService).getAllUserByName("worker");
    }
    @Test
    @WithMockUser(username = "me", roles = {"ADMIN"})
    void getAllUserByName_NotFound() throws Exception {

        when(userService.getAllUserByName("worker"))
                .thenReturn(null);

        mockMvc.perform(get("/users/search/worker"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(userService).getAllUserByName("worker");
    }


    //getAllNormalUser
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getAllNormalUser_UsersFound() throws Exception {
        NormalUser worker = new NormalUser(1l, "worker","worker","worker","worker","worker","worker");
        Admin boss = new Admin(2l, "boss","boss");

        GetUserDTO workerDTO = GetUserDTO.builder()
                .username(worker.getUsername())
                .firstName(worker.getFirstname())
                .lastName(worker.getLastname())
                .phoneNumber(worker.getPhonenumber())
                .email(worker.getEmail())
                .position("NORMAL")
                .build();

        String none = "-none-";
        GetUserDTO bossDTO = GetUserDTO.builder()
                .username(boss.getUsername())
                .firstName(none)
                .lastName(none)
                .phoneNumber(none)
                .email(none)
                .position("NORMAL")
                .build();

        List<GetUserDTO> users = List.of(workerDTO);

        when(userService.getAllNormalUsers())
                .thenReturn(users);

        mockMvc.perform(get("/users/NormalUser"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                [{"username":"worker","firstName":"worker","lastName":"worker","phoneNumber":"worker","email":"worker","position":"NORMAL"}]
                """ ));

        verify(userService).getAllNormalUsers();
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getAllNormalUser_NotFound() throws Exception {

        when(userService.getAllNormalUsers())
                .thenReturn(null);

        mockMvc.perform(get("/users/NormalUser"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(userService).getAllNormalUsers();
    }

    //getAllAdmin
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getAllAdmin_UsersFound() throws Exception {
        Admin boss = new Admin(2l, "boss","boss");

        String none = "-None-";
        GetUserDTO bossDTO = GetUserDTO.builder()
                .username(boss.getUsername())
                .firstName(none)
                .lastName(none)
                .phoneNumber(none)
                .email(none)
                .position("ADMIN")
                .build();

        List<GetUserDTO> users = List.of(bossDTO);

        when(userService.getAllAdmins())
                .thenReturn(users);

        mockMvc.perform(get("/users/Admin"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                [{"username":"boss","firstName":"-None-","lastName":"-None-","phoneNumber":"-None-","email":"-None-","position":"ADMIN"}]
                """ ));

        verify(userService).getAllAdmins();
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getAllAdmin_NotFound() throws Exception {

        when(userService.getAllAdmins())
                .thenReturn(null);

        mockMvc.perform(get("/users/Admin"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(userService).getAllAdmins();
    }

    //isUsernameUnique
    @Test
    void isUsernameUnique_true() throws Exception {
        when(userService.isUsernameUnique("boss"))
                .thenReturn(true);

        mockMvc.perform(get("/users/unique/name/boss"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(userService).isUsernameUnique("boss");
    }
    @Test
    void isUsernameUnique_false() throws Exception {

        when(userService.isUsernameUnique("boss"))
                .thenReturn(false);

        mockMvc.perform(get("/users/unique/name/boss"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(userService).isUsernameUnique("boss");
    }

    //isPhoneNumberUnique
    @Test
    void isPhoneNumberUnique_true() throws Exception {

        when(userService.isPhoneNumberUnique("boss"))
                .thenReturn(true);

        mockMvc.perform(get("/users/unique/phoneNumber/boss"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(userService).isPhoneNumberUnique("boss");
    }
    @Test
    void isPhoneNumberUnique_false() throws Exception {

        when(userService.isPhoneNumberUnique("boss"))
                .thenReturn(false);

        mockMvc.perform(get("/users/unique/phoneNumber/boss"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(userService).isPhoneNumberUnique("boss");
    }

    //isEmailUnique
    @Test
    void isEmailUnique_true() throws Exception {

        when(userService.isEmailUnique("boss"))
                .thenReturn(true);

        mockMvc.perform(get("/users/unique/email/boss"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(userService).isEmailUnique("boss");
    }
    @Test
    void isEmailUnique_false() throws Exception {

        when(userService.isEmailUnique("boss"))
                .thenReturn(false);

        mockMvc.perform(get("/users/unique/email/boss"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(userService).isEmailUnique("boss");
    }

    //deleteUser
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void deleteUser() throws Exception {

        mockMvc.perform(delete("/users/Worker"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(userService).deleteUser("Worker");
    }

    //createUser
    @Test
    void createUser()  throws Exception {
        CreateUserRequestDTO createUserRequestDTO = CreateUserRequestDTO .builder()
                .firstName("firstName")
                .lastName("lastName")
                .username("username")
                .password("password")
                .email("email")
                .phoneNumber("phoneNumber")
                .build();

        CreateUserResponseDTO createUserResponseDTO = new CreateUserResponseDTO("firstname");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(createUserRequestDTO);

        when(userService.addUser(createUserRequestDTO))
                .thenReturn(createUserResponseDTO);

        mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(userService).addUser(createUserRequestDTO);
    }
    @Test
    void createUser_Conflict()  throws Exception {
        CreateUserRequestDTO createUserRequestDTO = CreateUserRequestDTO .builder()
                .firstName("firstName")
                .lastName("lastName")
                .username("username")
                .password("password")
                .email("email")
                .phoneNumber("phoneNumber")
                .build();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(createUserRequestDTO);

        when(userService.addUser(createUserRequestDTO))
                .thenReturn(null);

        mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(print())
                .andExpect(status().isConflict());

        verify(userService).addUser(createUserRequestDTO);
    }

    //updateUser
    @Test
    @WithMockUser(username = "me", roles = {"NORMALUSER"})
    void updateUser() throws Exception {
        UpdateUserRequestDTO updateUserRequestDTO = UpdateUserRequestDTO .builder()
                .username("Worker")
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .phoneNumber("phoneNumber")
                .build();

        UpdateUserResponseDTO updateUserResponseDTO = new UpdateUserResponseDTO("firstname");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(updateUserRequestDTO);

        when(userService.updateUser(updateUserRequestDTO))
                .thenReturn(updateUserResponseDTO);

        mockMvc.perform(put("/users")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());

        verify(userService).updateUser(updateUserRequestDTO);
    }
    @Test
    @WithMockUser(username = "me", roles = {"NORMALUSER"})
    void updateUser_BadRequest() throws Exception {
        UpdateUserRequestDTO updateUserRequestDTO = UpdateUserRequestDTO .builder()
                .username("Worker")
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .phoneNumber("phoneNumber")
                .build();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(updateUserRequestDTO);

        when(userService.updateUser(updateUserRequestDTO))
                .thenReturn(null);

        mockMvc.perform(put("/users")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(userService).updateUser(updateUserRequestDTO);
    }
}