package com.example.individualproject.controller;

import com.example.individualproject.business.impl.ProductServiceImpl;
import com.example.individualproject.business.impl.UserServiceImpl;
import com.example.individualproject.dto.products.BasicProductInfo;
import com.example.individualproject.dto.products.GetProductDTO;
import com.example.individualproject.dto.users.*;
import com.example.individualproject.repository.entity.Admin;
import com.example.individualproject.repository.entity.Genre;
import com.example.individualproject.repository.entity.NormalUser;

import com.example.individualproject.repository.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    @MockBean
    private ProductServiceImpl productService;

    //getAllUsers
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

    //getUserByID
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

    //getAllUserByName
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


    //getAllNormalUser
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

    //getAllAdmin
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
    void deleteUser() throws Exception {

        mockMvc.perform(delete("/users/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(userService).deleteUser(1l);
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
    void createUser_Confilict()  throws Exception {
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
    void updateUser() throws Exception {
        UpdateUserRequestDTO updateUserRequestDTO = UpdateUserRequestDTO .builder()
                .id(1l)
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
    void updateUser_BadRequest() throws Exception {
        UpdateUserRequestDTO updateUserRequestDTO = UpdateUserRequestDTO .builder()
                .id(1l)
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

    //getUsersProducts
    @Test
    void getUsersProducts() throws Exception {

        GetProductDTO product1 = new GetProductDTO(1l, new BasicProductInfo(),null);

        GetProductDTO product2 = new GetProductDTO(2l,new BasicProductInfo(),null);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(List.of(product1,product2));

        when(productService.getAllOfAUsersProducts(1l))
                .thenReturn(List.of(product1,product2));

        mockMvc.perform(get("/users/products/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(json));

        verify(productService).getAllOfAUsersProducts(1l);
    }
    @Test
    void getUsersProducts_NotFound() throws Exception {

        GetProductDTO product1 = new GetProductDTO(1l, new BasicProductInfo(),null);

        GetProductDTO product2 = new GetProductDTO(2l,new BasicProductInfo(),null);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(List.of(product1,product2));

        when(productService.getAllOfAUsersProducts(1l))
                .thenReturn(null);

        mockMvc.perform(get("/users/products/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(productService).getAllOfAUsersProducts(1l);
    }
}