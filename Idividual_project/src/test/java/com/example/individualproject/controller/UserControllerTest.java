package com.example.individualproject.controller;

import com.example.individualproject.business.impl.UserServiceImpl;
import com.example.individualproject.dto.users.GetUserDTO;
import com.example.individualproject.dto.users.UserAccountRequestDTO;
import com.example.individualproject.repository.entity.Admin;
import com.example.individualproject.repository.entity.NormalUser;

import com.example.individualproject.repository.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserServiceImpl userService;

    @Test
    void getAllUsers_UsersFound() throws Exception {
        NormalUser worker = new NormalUser(1l, "worker","worker","worker","worker","worker","worker");
        Admin boss = new Admin(2l, "boss","boss");

       GetUserDTO workerDTO = new GetUserDTO(worker);
       GetUserDTO bossDTO = new GetUserDTO(boss);

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
    void getAllUsers_NotFound() throws Exception {
        when(userService.getAllUsers())
                .thenReturn(null);

        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(userService).getAllUsers();
    }

    @Test
    void getUserByID_UserFound() throws Exception {
        NormalUser worker = new NormalUser(1l, "worker","worker","worker","worker","worker","worker");
        GetUserDTO workerDTO = new GetUserDTO(worker);

        when(userService.getUserByID(1l))
                .thenReturn(workerDTO);

        mockMvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                {"username":"worker","firstName":"worker","lastName":"worker","phoneNumber":"worker","email":"worker","position":"NORMAL"}
                """ ));

        verify(userService).getUserByID(1l);
    }
    @Test
    void getUserByID_NotFound() throws Exception {
        when(userService.getUserByID(1l))
                .thenReturn(null);

        mockMvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(userService).getUserByID(1l);
    }

    @Test
    void getAllUserByName_UserFound() throws Exception {
        NormalUser worker = new NormalUser(1l, "worker","worker","worker","worker","worker","worker");
        Admin boss = new Admin(2l, "boss","boss");

        GetUserDTO workerDTO = new GetUserDTO(worker);
        GetUserDTO bossDTO = new GetUserDTO(boss);

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
    void getAllUserByName_NotFound() throws Exception {

        when(userService.getAllUserByName("worker"))
                .thenReturn(null);

        mockMvc.perform(get("/users/search/worker"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(userService).getAllUserByName("worker");
    }



    @Test
    void getAllNormalUser_UsersFound() throws Exception {
        NormalUser worker = new NormalUser(1l, "worker","worker","worker","worker","worker","worker");
        Admin boss = new Admin(2l, "boss","boss");

        GetUserDTO workerDTO = new GetUserDTO(worker);
        GetUserDTO bossDTO = new GetUserDTO(boss);

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
    void getAllNormalUser_NotFound() throws Exception {

        when(userService.getAllNormalUsers())
                .thenReturn(null);

        mockMvc.perform(get("/users/NormalUser"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(userService).getAllNormalUsers();
    }

    @Test
    void getAllAdmin_UsersFound() throws Exception {
        Admin boss = new Admin(2l, "boss","boss");

        GetUserDTO bossDTO = new GetUserDTO(boss);

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
    void getAllAdmin_NotFound() throws Exception {

        when(userService.getAllAdmins())
                .thenReturn(null);

        mockMvc.perform(get("/users/Admin"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(userService).getAllAdmins();
    }

    @Test
    void getUsersByUsernameAndPassword_UserFound()throws Exception {
        Admin boss = new Admin(2l, "boss","boss");
        GetUserDTO bossDTO = new GetUserDTO(boss);

        when(userService.getUser(new UserAccountRequestDTO("boss","boss")))
                .thenReturn(bossDTO);

        mockMvc.perform(get("/users/login/boss/boss"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                {"username":"boss","firstName":"-None-","lastName":"-None-","phoneNumber":"-None-","email":"-None-","position":"ADMIN"}
                """ ));

        verify(userService).getUser((new UserAccountRequestDTO("boss","boss")));
    }
    @Test
    void getUsersByUsernameAndPassword_NotFound() throws Exception {
        when(userService.getUser(new UserAccountRequestDTO("null","null")))
                .thenReturn(null);

        mockMvc.perform(get("/users/login/null/null"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(userService).getUser(new UserAccountRequestDTO("null","null"));
    }

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


    @Test
    void deleteUser() throws Exception {

        mockMvc.perform(delete("/users/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(userService).deleteUser(1l);
    }

    @Test
    void createUser() {
    }

    @Test
    void updateUser() {
    }
}