package com.example.individualproject.controller;

import com.example.individualproject.business.impl.UserServiceImpl;
import com.example.individualproject.dto.users.*;
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

    private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    //getAllUsers
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getAllUsers_UsersFound() throws Exception {
        GetUserDTO workerDTO = GetUserDTO.builder()
                .username("worker")
                .firstName("worker")
                .lastName("worker")
                .phoneNumber("worker")
                .email("worker")
                .position("NORMAL")
                .build();

        String none = "-None-";
        GetUserDTO bossDTO = GetUserDTO.builder()
                .username("boss")
                .firstName(none)
                .lastName(none)
                .phoneNumber(none)
                .email(none)
                .position("ADMIN")
                .build();

        when(userService.getAllUsers())
                .thenReturn(List.of(workerDTO, bossDTO));

        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(ow.writeValueAsString(List.of(workerDTO, bossDTO))));

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
        GetUserDTO workerDTO = GetUserDTO.builder()
                .username("worker")
                .firstName("worker")
                .lastName("worker")
                .phoneNumber("worker")
                .email("worker")
                .position("NORMAL")
                .build();

        String none = "-None-";
        GetUserDTO bossDTO = GetUserDTO.builder()
                .username("boss")
                .firstName(none)
                .lastName(none)
                .phoneNumber(none)
                .email(none)
                .position("ADMIN")
                .build();


        when(userService.getAllUserByName("worker"))
                .thenReturn(List.of(workerDTO, bossDTO));

        mockMvc.perform(get("/users/search/worker"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(ow.writeValueAsString(List.of(workerDTO, bossDTO)) ));

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

        GetUserDTO workerDTO = GetUserDTO.builder()
                .username("worker")
                .firstName("worker")
                .lastName("worker")
                .phoneNumber("worker")
                .email("worker")
                .position("NORMAL")
                .build();

        when(userService.getAllNormalUsers())
                .thenReturn(List.of(workerDTO));

        mockMvc.perform(get("/users/NormalUser"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(ow.writeValueAsString(List.of(workerDTO))));

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
        String none = "-None-";
        GetUserDTO bossDTO = GetUserDTO.builder()
                .username("boss")
                .firstName(none)
                .lastName(none)
                .phoneNumber(none)
                .email(none)
                .position("ADMIN")
                .build();

        when(userService.getAllAdmins())
                .thenReturn(List.of(bossDTO));

        mockMvc.perform(get("/users/Admin"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(ow.writeValueAsString(List.of(bossDTO))));

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
                .andExpect(status().isNoContent());

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

        when(userService.addUser(createUserRequestDTO))
                .thenReturn(createUserResponseDTO);

        mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(ow.writeValueAsString(createUserRequestDTO)))
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

        when(userService.addUser(createUserRequestDTO))
                .thenReturn(null);

        mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(ow.writeValueAsString(createUserRequestDTO)))
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

        when(userService.updateUser(updateUserRequestDTO))
                .thenReturn(updateUserResponseDTO);

        mockMvc.perform(put("/users")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(ow.writeValueAsString(updateUserRequestDTO)))
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

        when(userService.updateUser(updateUserRequestDTO))
                .thenReturn(null);

        mockMvc.perform(put("/users")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content( ow.writeValueAsString(updateUserRequestDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(userService).updateUser(updateUserRequestDTO);
    }

    //getUserByUsernameAdmin
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getUserByUsernameAdmin() throws Exception {
        GetUserDTO workerDTO = GetUserDTO.builder()
                .username("worker")
                .firstName("worker")
                .lastName("worker")
                .phoneNumber("worker")
                .email("worker")
                .position("NORMAL")
                .build();


        when(userService.getUserByName("Worker"))
                .thenReturn(workerDTO);

        mockMvc.perform(get("/users/admin/Worker"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(ow.writeValueAsString(workerDTO)));


        verify(userService).getUserByName("Worker");
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getUserByUsernameAdmin_NotFound() throws Exception {
        when(userService.getUserByName("Worker"))
                .thenReturn(null);

        mockMvc.perform(get("/users/admin/Worker"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(userService).getUserByName("Worker");
    }

    //getUserByUsernameNormal
    @Test
    @WithMockUser(username = "me", roles = {"NORMALUSER"})
    void getUserByUsernameNormal() throws Exception {
        GetUserDTO workerDTO = GetUserDTO.builder()
                .username("worker")
                .firstName("worker")
                .lastName("worker")
                .phoneNumber("worker")
                .email("worker")
                .position("NORMAL")
                .build();


        when(userService.getUserByNameNormalUser("Worker"))
                .thenReturn(workerDTO);

        mockMvc.perform(get("/users/normal/Worker"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(ow.writeValueAsString(workerDTO)));


        verify(userService).getUserByNameNormalUser("Worker");
    }
    @Test
    @WithMockUser(username = "me", roles = {"NORMALUSER"})
    void getUserByUsernameNormal_NotFound() throws Exception {
        when(userService.getUserByNameNormalUser("Worker"))
                .thenReturn(null);

        mockMvc.perform(get("/users/normal/Worker"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(userService).getUserByNameNormalUser("Worker");
    }
}