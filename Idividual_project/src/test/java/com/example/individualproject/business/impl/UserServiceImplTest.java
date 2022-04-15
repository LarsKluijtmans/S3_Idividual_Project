package com.example.individualproject.business.impl;

import com.example.individualproject.business.UserService;
import com.example.individualproject.dto.users.GetUserDTO;
import com.example.individualproject.dto.users.UserAccountRequestDTO;
import com.example.individualproject.repository.AdminRepository;
import com.example.individualproject.repository.NormalUserRepository;
import com.example.individualproject.repository.entity.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Test
    void getAllUsers() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        Admin boss = new Admin(1l,"Admin","Admin");
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(normalUserRepository.findAll())
                .thenReturn(List.of(worker));
        when(adminRepository.findAll())
                .thenReturn(List.of(boss));

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        List<GetUserDTO> actualResult = userServiceeMock.getAllUsers();

        GetUserDTO WorkerDTO = new GetUserDTO(worker);
        GetUserDTO AdminDTO = new GetUserDTO(boss);

        List<GetUserDTO> expectedResult = List.of(WorkerDTO,AdminDTO);

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findAll();
        verify(adminRepository).findAll();
    }

    @Test
    void getAllNormalUsers() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(normalUserRepository.findAll())
                .thenReturn(List.of(worker));

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        List<GetUserDTO> actualResult = userServiceeMock.getAllNormalUsers();

        GetUserDTO WorkerDTO = new GetUserDTO(worker);

        List<GetUserDTO> expectedResult = List.of(WorkerDTO);

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findAll();
    }

    @Test
    void getAllAdmins() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        Admin boss = new Admin(1l,"Admin","Admin");


        when(adminRepository.findAll())
                .thenReturn(List.of(boss));

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        List<GetUserDTO> actualResult = userServiceeMock.getAllAdmins();

        GetUserDTO AdminDTO = new GetUserDTO(boss);

        List<GetUserDTO> expectedResult = List.of(AdminDTO);

        assertEquals(expectedResult, actualResult);

        verify(adminRepository).findAll();
    }

    @Test
    void getUserByID_ResultAdmin()  {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        Admin boss = new Admin(2L,"Admin","Admin");

        when(normalUserRepository.findAllByIdIsLike(1L))
                .thenReturn(null);
        when(adminRepository.findAllByIdIsLike(1L))
                .thenReturn(boss);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        GetUserDTO actualResult = userServiceeMock.getUserByID(1L);

        GetUserDTO AdminDTO = new GetUserDTO(boss);

        GetUserDTO expectedResult = AdminDTO;

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findAllByIdIsLike(1L);
        verify(adminRepository).findAllByIdIsLike(1L);


    }
    @Test
    void getUserByID_ResultNormalUser()  {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(normalUserRepository.findAllByIdIsLike(1L))
                .thenReturn(worker);
        when(adminRepository.findAllByIdIsLike(1L))
                .thenReturn(null);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        GetUserDTO actualResult = userServiceeMock.getUserByID(1L);

        GetUserDTO WorkerDTO = new GetUserDTO(worker);

        GetUserDTO expectedResult = WorkerDTO;

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findAllByIdIsLike(1L);
    }
    @Test
    void getUserByID_ResultNull()  {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        when(normalUserRepository.findAllByIdIsLike(1L))
                .thenReturn(null);
        when(adminRepository.findAllByIdIsLike(1L))
                .thenReturn(null);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        GetUserDTO actualResult = userServiceeMock.getUserByID(1L);

        assertEquals(null, actualResult);

        verify(normalUserRepository).findAllByIdIsLike(1L);
        verify(adminRepository).findAllByIdIsLike(1L);
    }

    @Test
    void getAllUserByName_ResultNormalUser() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        Admin boss = new Admin(1l, "Admin", "Admin");
        NormalUser worker = new NormalUser(1l, "Worker", "Worker", "Worker", "Worker", "Worker", "Worker");

        when(normalUserRepository.findAllByFirstnameIsLikeOrLastnameIsLikeOrUsernameIsLike("%Worker%","%Worker%","%Worker%"))
                .thenReturn(List.of(worker));
        when(adminRepository.findAllByUsernameIsLike("%Worker%"))
                .thenReturn(List.of());

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository, adminRepository);

        List<GetUserDTO> actualResult = userServiceeMock.getAllUserByName("Worker");

        GetUserDTO WorkerDTO = new GetUserDTO(worker);

        List<GetUserDTO> expectedResult = List.of(WorkerDTO);

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findAllByFirstnameIsLikeOrLastnameIsLikeOrUsernameIsLike("%Worker%","%Worker%","%Worker%");
        verify(adminRepository).findAllByUsernameIsLike("%Worker%");
    }
    @Test
    void getAllUserByName_ResultAdmin() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        Admin boss = new Admin(1l, "Admin", "Admin");
        NormalUser worker = new NormalUser(1l, "Worker", "Worker", "Worker", "Worker", "Worker", "Worker");

        when(normalUserRepository.findAllByFirstnameIsLikeOrLastnameIsLikeOrUsernameIsLike("%Admin%","%Admin%","%Admin%"))
                .thenReturn(List.of());
        when(adminRepository.findAllByUsernameIsLike("%Admin%"))
                .thenReturn(List.of(boss));

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository, adminRepository);

        List<GetUserDTO> actualResult = userServiceeMock.getAllUserByName("Admin");

        GetUserDTO AdminDTO = new GetUserDTO(boss);

        List<GetUserDTO> expectedResult = List.of(AdminDTO);

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findAllByFirstnameIsLikeOrLastnameIsLikeOrUsernameIsLike("%Admin%","%Admin%","%Admin%");
        verify(adminRepository).findAllByUsernameIsLike("%Admin%");
    }
    @Test
    void getAllUserByName_ResultNull() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        when(normalUserRepository.findAllByFirstnameIsLikeOrLastnameIsLikeOrUsernameIsLike("%Admin%","%Admin%","%Admin%"))
                .thenReturn(List.of());
        when(adminRepository.findAllByUsernameIsLike("%Admin%"))
                .thenReturn(List.of());

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository, adminRepository);

        List<GetUserDTO> actualResult = userServiceeMock.getAllUserByName("Admin");

        List<GetUserDTO> expectedResult = List.of();

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findAllByFirstnameIsLikeOrLastnameIsLikeOrUsernameIsLike("%Admin%","%Admin%","%Admin%");
        verify(adminRepository).findAllByUsernameIsLike("%Admin%");
    }

    @Test
    void deleteUser_userDosntExist() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        when(normalUserRepository.findAllByIdIsLike(1l))
                .thenReturn(null);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository, adminRepository);

        boolean actualResult = userServiceeMock.deleteUser(1l);

        assertEquals(false, actualResult);

        verify(normalUserRepository).findAllByIdIsLike(1l);
    }
    @Test
    void deleteUser_userExists() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(normalUserRepository.findAllByIdIsLike(1l))
                .thenReturn(worker);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository, adminRepository);

        boolean actualResult = userServiceeMock.deleteUser(1l);


        assertEquals(true, actualResult);

        verify(normalUserRepository).findAllByIdIsLike(1l);
        verify(normalUserRepository).deleteById(1l);
    }

    @Test
    void getUser_ResultNormalUser() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(normalUserRepository.getUserByUsernameIsLikeAndPasswordIsLike("Worker","Worker"))
                .thenReturn(worker);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        GetUserDTO actualResult = userServiceeMock.getUser(new UserAccountRequestDTO("Worker","Worker"));

        GetUserDTO WorkerDTO = new GetUserDTO(worker);

        GetUserDTO expectedResult = WorkerDTO;

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).getUserByUsernameIsLikeAndPasswordIsLike("Worker","Worker");
    }
    @Test
    void getUser_ResultAdmin() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        Admin boss = new Admin(1l,"Admin","Admin");

        when(normalUserRepository.getUserByUsernameIsLikeAndPasswordIsLike("Admin","Admin"))
                .thenReturn(null);
        when(adminRepository.getUserByUsernameIsLikeAndPasswordIsLike("Admin","Admin"))
                .thenReturn(boss);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        GetUserDTO actualResult = userServiceeMock.getUser(new UserAccountRequestDTO("Admin","Admin"));

        GetUserDTO AdminDTO = new GetUserDTO(boss);

        GetUserDTO expectedResult = AdminDTO;

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).getUserByUsernameIsLikeAndPasswordIsLike("Admin","Admin");
        verify(adminRepository).getUserByUsernameIsLikeAndPasswordIsLike("Admin","Admin");
    }

    @Test
    void updateUser() {
    }

    @Test
    void isUsernameUnique() {
    }

    @Test
    void isPhoneNumberUnique() {
    }

    @Test
    void isEmailUnique() {
    }

    @Test
    void addUser() {
    }
}