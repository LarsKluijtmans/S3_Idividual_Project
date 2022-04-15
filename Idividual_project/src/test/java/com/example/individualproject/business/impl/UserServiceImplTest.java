package com.example.individualproject.business.impl;

import com.example.individualproject.business.UserService;
import com.example.individualproject.dto.users.*;
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

        when(normalUserRepository.findAllByIdIs(1L))
                .thenReturn(null);
        when(adminRepository.findAllByIdIsLike(1L))
                .thenReturn(boss);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        GetUserDTO actualResult = userServiceeMock.getUserByID(1L);

        GetUserDTO AdminDTO = new GetUserDTO(boss);

        GetUserDTO expectedResult = AdminDTO;

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findAllByIdIs(1L);
        verify(adminRepository).findAllByIdIsLike(1L);


    }
    @Test
    void getUserByID_ResultNormalUser()  {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(normalUserRepository.findAllByIdIs(1L))
                .thenReturn(worker);
        when(adminRepository.findAllByIdIsLike(1L))
                .thenReturn(null);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        GetUserDTO actualResult = userServiceeMock.getUserByID(1L);

        GetUserDTO WorkerDTO = new GetUserDTO(worker);

        GetUserDTO expectedResult = WorkerDTO;

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findAllByIdIs(1L);
    }
    @Test
    void getUserByID_ResultNull()  {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        when(normalUserRepository.findAllByIdIs(1L))
                .thenReturn(null);
        when(adminRepository.findAllByIdIsLike(1L))
                .thenReturn(null);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        GetUserDTO actualResult = userServiceeMock.getUserByID(1L);

        assertEquals(null, actualResult);

        verify(normalUserRepository).findAllByIdIs(1L);
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

        when(normalUserRepository.findAllByIdIs(1l))
                .thenReturn(null);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository, adminRepository);

        boolean actualResult = userServiceeMock.deleteUser(1l);

        assertEquals(false, actualResult);

        verify(normalUserRepository).findAllByIdIs(1l);
    }
    @Test
    void deleteUser_userExists() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(normalUserRepository.findAllByIdIs(1l))
                .thenReturn(worker);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository, adminRepository);

        boolean actualResult = userServiceeMock.deleteUser(1l);


        assertEquals(true, actualResult);

        verify(normalUserRepository).findAllByIdIs(1l);
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
    void isUsernameUnique_ResultAdmin() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");
        Admin boss = new Admin(1l,"Admin","Admin");

        when(normalUserRepository.findByUsernameIs("Admin"))
                .thenReturn(null);
        when(adminRepository.findByUsernameIs("Admin"))
                .thenReturn(boss);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        Boolean actualResult = userServiceeMock.isUsernameUnique("Admin");

        assertEquals(false, actualResult);

        verify(normalUserRepository).findByUsernameIs("Admin");
        verify(adminRepository).findByUsernameIs("Admin");
    }
    @Test
    void isUsernameUnique_ResultNormalUser() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(normalUserRepository.findByUsernameIs("Worker"))
                .thenReturn(worker);
        when(adminRepository.findByUsernameIs("Worker"))
                .thenReturn(null);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        Boolean actualResult = userServiceeMock.isUsernameUnique("Worker");

        assertEquals(false, actualResult);

        verify(normalUserRepository).findByUsernameIs("Worker");
    }
    @Test
    void isUsernameUnique_ResultNothingFound() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        when(normalUserRepository.findByUsernameIs("Admin2"))
                .thenReturn(null);
        when(adminRepository.findByUsernameIs("Admin2"))
                .thenReturn(null);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        Boolean actualResult = userServiceeMock.isUsernameUnique("Admin2");

        assertEquals(true, actualResult);

        verify(normalUserRepository).findByUsernameIs("Admin2");
        verify(adminRepository).findByUsernameIs("Admin2");
    }

    @Test
    void isPhoneNumberUnique_ResultNormalUser() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(normalUserRepository.findByPhonenumberIs("Worker"))
                .thenReturn(worker);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        Boolean actualResult = userServiceeMock.isPhoneNumberUnique("Worker");

        assertEquals(false, actualResult);

        verify(normalUserRepository).findByPhonenumberIs("Worker");
    }
    @Test
    void isPhoneNumberUnique_ResultNothingFound() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(normalUserRepository.findByPhonenumberIs("Worker2"))
                .thenReturn(null);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        Boolean actualResult = userServiceeMock.isPhoneNumberUnique("Worker2");

        assertEquals(true, actualResult);

        verify(normalUserRepository).findByPhonenumberIs("Worker2");
    }

    @Test
    void isEmailUnique_ResultNormalUser() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(normalUserRepository.findByEmailIs("Worker"))
                .thenReturn(worker);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        Boolean actualResult = userServiceeMock.isEmailUnique("Worker");

        assertEquals(false, actualResult);

        verify(normalUserRepository).findByEmailIs("Worker");
    }
    @Test
    void isEmailUnique_ResultNothingFound() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        when(normalUserRepository.findByEmailIs("Worker2"))
                .thenReturn(null);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        Boolean actualResult = userServiceeMock.isEmailUnique("Worker2");

        assertEquals(true, actualResult);

        verify(normalUserRepository).findByEmailIs("Worker2");
    }

    @Test
    void addUser_UsernameNotUnique_NormalUser()  {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        Admin boss = new Admin(1l,"Admin","Admin");
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(normalUserRepository.findByUsernameIs("Worker"))
                .thenReturn(worker);
        when(adminRepository.findByUsernameIs("Worker"))
                .thenReturn(null);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("Worker","Worker","Worker","Worker","Worker","Worker");
        CreateUserResponseDTO actualResult = userServiceeMock.addUser(createUserRequestDTO);

        assertEquals(null, actualResult);

        verify(normalUserRepository).findByUsernameIs("Worker");
        verify(adminRepository).findByUsernameIs("Worker");
    }
    @Test
    void addUser_UsernameNotUnique_Admin()  {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        Admin boss = new Admin(1l,"Admin","Admin");

        when(normalUserRepository.findByUsernameIs("Admin"))
                .thenReturn(null);
        when(adminRepository.findByUsernameIs("Admin"))
                .thenReturn(boss);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("Admin","Admin","Admin","Admin","Admin","Admin");
        CreateUserResponseDTO actualResult = userServiceeMock.addUser(createUserRequestDTO);

        assertEquals(null, actualResult);

        verify(normalUserRepository).findByUsernameIs("Admin");
        verify(adminRepository).findByUsernameIs("Admin");
    }
    @Test
    void addUser_EmailNotUnique()  {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        Admin boss = new Admin(1l,"Admin","Admin");
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(normalUserRepository.findByEmailIs("Worker"))
                .thenReturn(worker);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("Worker","Worker","Worker","Worker","Worker","Worker");
        CreateUserResponseDTO actualResult = userServiceeMock.addUser(createUserRequestDTO);

        assertEquals(null, actualResult);

        verify(normalUserRepository).findByEmailIs("Worker");
    }
    @Test
    void addUser_PhoneNumberNotUnique()  {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(normalUserRepository.findByPhonenumberIs("Worker"))
                .thenReturn(worker);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("Worker","Worker","Worker","Worker","Worker","Worker");
        CreateUserResponseDTO actualResult = userServiceeMock.addUser(createUserRequestDTO);

        assertEquals(null, actualResult);

        verify(normalUserRepository).findByPhonenumberIs("Worker");
    }
    @Test
    void addUser_AllInfoUnique()  {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("Worker1","Worker","Worker","Worker","Worker2","Worker3");

        NormalUser newUser = new NormalUser(createUserRequestDTO);
        NormalUser SavedUser = new NormalUser(1l,"Worker1","Worker","Worker","Worker","Worker2","Worker3");

        when(normalUserRepository.findByUsernameIs("Worker1"))
                .thenReturn(null);
        when(normalUserRepository.findByPhonenumberIs("Worker2"))
                .thenReturn(null);
        when(normalUserRepository.findByEmailIs("Worker3"))
                .thenReturn(null);
        when(normalUserRepository.save(newUser))
                .thenReturn(SavedUser);


        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        CreateUserResponseDTO actualResult = userServiceeMock.addUser(createUserRequestDTO);


        CreateUserResponseDTO expectedResult = CreateUserResponseDTO.builder()
                .firstName(SavedUser.getFirstname())
                .build();
        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findByUsernameIs("Worker1");
        verify(adminRepository).findByUsernameIs("Worker1");
        verify(normalUserRepository).findByPhonenumberIs("Worker2");
        verify(normalUserRepository).findByEmailIs("Worker3");
        verify(normalUserRepository).save(newUser);
    }

    @Test
    void updateUser_UserDosntExists() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(normalUserRepository.findAllByIdIs(1l))
                .thenReturn(null);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);

        UpdateUserRequestDTO updateRequestDTO = new UpdateUserRequestDTO(1l,"Worker","Worker","Worker","Worker" );
        UpdateUserResponseDTO actualResult = userServiceeMock.updateUser(updateRequestDTO);

        assertEquals(null, actualResult);

        verify(normalUserRepository).findAllByIdIs(1l);
    }
    @Test
    void updateUser_ResultSuccess() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        UpdateUserRequestDTO updateRequestDTO = new UpdateUserRequestDTO(2l,"Worker","Worker","Worker1","Worker2" );
        NormalUser newUser = new NormalUser(updateRequestDTO.getId(), "Worker", "Worker", updateRequestDTO.getFirstName(), updateRequestDTO.getLastName(), updateRequestDTO.getPhoneNumber(), updateRequestDTO.getEmail());

        when(normalUserRepository.findAllByIdIs(2l))
                .thenReturn(worker);
        when(normalUserRepository.findByPhonenumberIs("Worker1"))
                .thenReturn(null);
        when(normalUserRepository.findByEmailIs("Worker2"))
                .thenReturn(null);
        when(normalUserRepository.save(newUser))
                .thenReturn(null);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);
        UpdateUserResponseDTO actualResult = userServiceeMock.updateUser(updateRequestDTO);

        UpdateUserResponseDTO excpectedResult = UpdateUserResponseDTO.builder()
                .firstName(newUser.getFirstname())
                .build();

        assertEquals(excpectedResult, actualResult);

        verify(normalUserRepository).findAllByIdIs(2l);
        verify(normalUserRepository).findByPhonenumberIs("Worker1");
        verify(normalUserRepository).findByEmailIs("Worker2");
        verify(normalUserRepository).save(newUser);
    }
    @Test
    void updateUser_PhoneNumberNotUnique() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        NormalUser worker = new NormalUser(2l,"Worker","Worker","Worker","Worker","Worker1","Worker1");

        UpdateUserRequestDTO updateRequestDTO = new UpdateUserRequestDTO(2l,"Worker","Worker","Worker","Worker" );

        when(normalUserRepository.findAllByIdIs(2l))
                .thenReturn(worker);
        when(normalUserRepository.findByPhonenumberIs("Worker"))
                .thenReturn(worker);


        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);
        UpdateUserResponseDTO actualResult = userServiceeMock.updateUser(updateRequestDTO);

        assertEquals(null, actualResult);

        verify(normalUserRepository).findAllByIdIs(2l);
        verify(normalUserRepository).findByPhonenumberIs("Worker");
    }
    @Test
    void updateUser_PhoneNumberNotUnique_IsUsedByUserThatIsBeingUpdated() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        UpdateUserRequestDTO updateRequestDTO = new UpdateUserRequestDTO(2l,"Worker","Worker","Worker","Worker2" );
        NormalUser newUser = new NormalUser(updateRequestDTO.getId(), "Worker", "Worker", updateRequestDTO.getFirstName(), updateRequestDTO.getLastName(), updateRequestDTO.getPhoneNumber(), updateRequestDTO.getEmail());

        when(normalUserRepository.findAllByIdIs(2l))
                .thenReturn(worker);
        when(normalUserRepository.findByPhonenumberIs("Worker"))
                .thenReturn(worker);
        when(normalUserRepository.findByEmailIs("Worker2"))
                .thenReturn(null);
        when(normalUserRepository.save(newUser))
                .thenReturn(null);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);
        UpdateUserResponseDTO actualResult = userServiceeMock.updateUser(updateRequestDTO);

        UpdateUserResponseDTO excpectedResult = UpdateUserResponseDTO.builder()
                .firstName(newUser.getFirstname())
                .build();

        assertEquals(excpectedResult, actualResult);

        verify(normalUserRepository).findAllByIdIs(2l);
        verify(normalUserRepository).findByPhonenumberIs("Worker");
        verify(normalUserRepository).findByEmailIs("Worker2");
        verify(normalUserRepository).save(newUser);
    }
    @Test
    void updateUser_EmailNotUnique() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        NormalUser worker = new NormalUser(2l,"Worker","Worker","Worker","Worker","Worker1","Worker1");

        UpdateUserRequestDTO updateRequestDTO = new UpdateUserRequestDTO(2l,"Worker","Worker","Worker","Worker" );

        when(normalUserRepository.findAllByIdIs(2l))
                .thenReturn(worker);
        when(normalUserRepository.findByPhonenumberIs("Worker"))
                .thenReturn(null);
        when(normalUserRepository.findByEmailIs("Worker"))
                .thenReturn(worker);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);
        UpdateUserResponseDTO actualResult = userServiceeMock.updateUser(updateRequestDTO);

        assertEquals(null, actualResult);

        verify(normalUserRepository).findAllByIdIs(2l);
        verify(normalUserRepository).findByPhonenumberIs("Worker");
    }
    @Test
    void updateUser_EmailNotUnique_IsUsedByUserThatIsBeingUpdated() {
        NormalUserRepository normalUserRepository = mock(NormalUserRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);

        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        UpdateUserRequestDTO updateRequestDTO = new UpdateUserRequestDTO(2l,"Worker","Worker","Worker2","Worker" );
        NormalUser newUser = new NormalUser(updateRequestDTO.getId(), "Worker", "Worker", updateRequestDTO.getFirstName(), updateRequestDTO.getLastName(), updateRequestDTO.getPhoneNumber(), updateRequestDTO.getEmail());

        when(normalUserRepository.findAllByIdIs(2l))
                .thenReturn(worker);
        when(normalUserRepository.findByPhonenumberIs("Worker2"))
                .thenReturn(null);
        when(normalUserRepository.findByEmailIs("Worker"))
                .thenReturn(worker);
        when(normalUserRepository.save(newUser))
                .thenReturn(null);

        UserService userServiceeMock = new UserServiceImpl(normalUserRepository,adminRepository);
        UpdateUserResponseDTO actualResult = userServiceeMock.updateUser(updateRequestDTO);

        UpdateUserResponseDTO excpectedResult = UpdateUserResponseDTO.builder()
                .firstName(newUser.getFirstname())
                .build();

        assertEquals(excpectedResult, actualResult);

        verify(normalUserRepository).findAllByIdIs(2l);
        verify(normalUserRepository).findByPhonenumberIs("Worker2");
        verify(normalUserRepository).findByEmailIs("Worker");
        verify(normalUserRepository).save(newUser);
    }
}