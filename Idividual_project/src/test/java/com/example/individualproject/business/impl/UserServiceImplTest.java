package com.example.individualproject.business.impl;

import com.example.individualproject.business.exception.*;
import com.example.individualproject.dto.login.AccessTokenDTO;
import com.example.individualproject.dto.users.*;
import com.example.individualproject.repository.AdminRepository;
import com.example.individualproject.repository.NormalUserRepository;
import com.example.individualproject.repository.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private NormalUserRepository normalUserRepository;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AccessTokenDTO accessTokenDTO;

    @InjectMocks
    private UserServiceImpl userServiceMock;

    //getAllUsers
    @Test
    void getAllUsers() {
        Admin boss = new Admin(1l,"Admin","Admin");
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        GetUserDTO WorkerDTO = GetUserDTO.builder()
                .username(worker.getUsername())
                .firstName(worker.getFirstname())
                .lastName(worker.getLastname())
                .phoneNumber(worker.getPhonenumber())
                .email(worker.getEmail())
                .position("NORMAL")
                .build();

        String none = "-None-";
        GetUserDTO AdminDTO = GetUserDTO.builder()
                .username(boss.getUsername())
                .firstName(none)
                .lastName(none)
                .phoneNumber(none)
                .email(none)
                .position("ADMIN")
                .build();

        when(normalUserRepository.findAll())
                .thenReturn(List.of(worker));
        when(adminRepository.findAll())
                .thenReturn(List.of(boss));

        List<GetUserDTO> actualResult = userServiceMock.getAllUsers();

        List<GetUserDTO> expectedResult = List.of(WorkerDTO,AdminDTO);

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findAll();
        verify(adminRepository).findAll();
    }

    //getAllNormalUsers
    @Test
    void getAllNormalUsers() {
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        GetUserDTO WorkerDTO = GetUserDTO.builder()
                .username(worker.getUsername())
                .firstName(worker.getFirstname())
                .lastName(worker.getLastname())
                .phoneNumber(worker.getPhonenumber())
                .email(worker.getEmail())
                .position("NORMAL")
                .build();

        when(normalUserRepository.findAll())
                .thenReturn(List.of(worker));

        List<GetUserDTO> actualResult = userServiceMock.getAllNormalUsers();

        List<GetUserDTO> expectedResult = List.of(WorkerDTO);

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findAll();
    }

    //getAllAdmins
    @Test
    void getAllAdmins() {
        Admin boss = new Admin(1l,"Admin","Admin");

        String none = "-None-";
        GetUserDTO AdminDTO = GetUserDTO.builder()
                .username(boss.getUsername())
                .firstName(none)
                .lastName(none)
                .phoneNumber(none)
                .email(none)
                .position("ADMIN")
                .build();

        when(adminRepository.findAll())
                .thenReturn(List.of(boss));

        List<GetUserDTO> actualResult = userServiceMock.getAllAdmins();

        List<GetUserDTO> expectedResult = List.of(AdminDTO);

        assertEquals(expectedResult, actualResult);

        verify(adminRepository).findAll();
    }

    //getUserByName
    @Test
    void getUserByName_NormalUser() {
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        GetUserDTO WorkerDTO = GetUserDTO.builder()
                .username(worker.getUsername())
                .firstName(worker.getFirstname())
                .lastName(worker.getLastname())
                .phoneNumber(worker.getPhonenumber())
                .email(worker.getEmail())
                .position("NORMAL")
                .build();

        when(normalUserRepository.findByUsername("Worker"))
                .thenReturn(worker);

        GetUserDTO actualResult = userServiceMock.getUserByName("Worker");

        GetUserDTO expectedResult = WorkerDTO;
        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findByUsername("Worker");
    }
    @Test
    void getUserByName_Admin() {
        Admin boss = new Admin(1l,"Admin","Admin");

        String none = "-None-";
        GetUserDTO AdminDTO = GetUserDTO.builder()
                .username(boss.getUsername())
                .firstName(none)
                .lastName(none)
                .phoneNumber(none)
                .email(none)
                .position("ADMIN")
                .build();

        when(normalUserRepository.findByUsername("Admin"))
                .thenReturn(null);
        when(adminRepository.findByUsername("Admin"))
                .thenReturn(boss);

        GetUserDTO actualResult = userServiceMock.getUserByName("Admin");

        GetUserDTO expectedResult = AdminDTO;
        assertEquals(expectedResult, actualResult);

        verify(adminRepository).findByUsername("Admin");
        verify(normalUserRepository).findByUsername("Admin");
    }
    @Test
    void getUserByName_NotingFound() {
        when(normalUserRepository.findByUsername("Admin"))
                .thenReturn(null);
        when(adminRepository.findByUsername("Admin"))
                .thenReturn(null);

        GetUserDTO actualResult = userServiceMock.getUserByName("Admin");

        assertEquals(null, actualResult);

        verify(adminRepository).findByUsername("Admin");
        verify(normalUserRepository).findByUsername("Admin");
    }

    //getUserByNameNormalUser
    @Test
    void getUserByNameNormalUser_UserNotFound () {

        when(normalUserRepository.findByUsername("Worker"))
                .thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> userServiceMock.getUserByNameNormalUser("Worker"));

        verify(normalUserRepository).findByUsername("Worker");
    }
    @Test
    void getUserByNameNormalUser_IdNotMatch() {
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(normalUserRepository.findByUsername("Worker"))
                .thenReturn(worker);
        when(accessTokenDTO.getUserId())
                .thenReturn(2l);

        assertThrows(InvalidCredentialsException.class, () -> userServiceMock.getUserByNameNormalUser("Worker"));

        verify(normalUserRepository).findByUsername("Worker");
        verify(accessTokenDTO).getUserId();

    }
    @Test
    void getUserByNameNormalUser() {
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        GetUserDTO WorkerDTO = GetUserDTO.builder()
                .username(worker.getUsername())
                .firstName(worker.getFirstname())
                .lastName(worker.getLastname())
                .phoneNumber(worker.getPhonenumber())
                .email(worker.getEmail())
                .position("NORMAL")
                .build();

        when(normalUserRepository.findByUsername("Worker"))
                .thenReturn(worker);
        when(accessTokenDTO.getUserId())
                .thenReturn(1l);

        GetUserDTO actualResult = userServiceMock.getUserByNameNormalUser("Worker");

        assertEquals(WorkerDTO,actualResult);

        verify(normalUserRepository).findByUsername("Worker");
        verify(accessTokenDTO).getUserId();

    }

    //getAllUserByName
    @Test
    void getAllUserByName_ResultNormalUser() {
        NormalUser worker = new NormalUser(1l, "Worker", "Worker", "Worker", "Worker", "Worker", "Worker");

        GetUserDTO WorkerDTO = GetUserDTO.builder()
                .username(worker.getUsername())
                .firstName(worker.getFirstname())
                .lastName(worker.getLastname())
                .phoneNumber(worker.getPhonenumber())
                .email(worker.getEmail())
                .position("NORMAL")
                .build();

        when(normalUserRepository.findAllByFirstnameIsLikeOrLastnameIsLikeOrUsernameIsLike("%Worker%","%Worker%","%Worker%"))
                .thenReturn(List.of(worker));
        when(adminRepository.findAllByUsernameIsLike("%Worker%"))
                .thenReturn(List.of());

        List<GetUserDTO> actualResult = userServiceMock.getAllUserByName("Worker");

        List<GetUserDTO> expectedResult = List.of(WorkerDTO);

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findAllByFirstnameIsLikeOrLastnameIsLikeOrUsernameIsLike("%Worker%","%Worker%","%Worker%");
        verify(adminRepository).findAllByUsernameIsLike("%Worker%");
    }
    @Test
    void getAllUserByName_ResultAdmin() {
        Admin boss = new Admin(1l, "Admin", "Admin");

        String none = "-None-";
        GetUserDTO AdminDTO = GetUserDTO.builder()
                .username(boss.getUsername())
                .firstName(none)
                .lastName(none)
                .phoneNumber(none)
                .email(none)
                .position("ADMIN")
                .build();

        when(normalUserRepository.findAllByFirstnameIsLikeOrLastnameIsLikeOrUsernameIsLike("%Admin%","%Admin%","%Admin%"))
                .thenReturn(List.of());
        when(adminRepository.findAllByUsernameIsLike("%Admin%"))
                .thenReturn(List.of(boss));

        List<GetUserDTO> actualResult = userServiceMock.getAllUserByName("Admin");

        List<GetUserDTO> expectedResult = List.of(AdminDTO);

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findAllByFirstnameIsLikeOrLastnameIsLikeOrUsernameIsLike("%Admin%","%Admin%","%Admin%");
        verify(adminRepository).findAllByUsernameIsLike("%Admin%");
    }
    @Test
    void getAllUserByName_ResultNull() {
        when(normalUserRepository.findAllByFirstnameIsLikeOrLastnameIsLikeOrUsernameIsLike("%Admin%","%Admin%","%Admin%"))
                .thenReturn(List.of());
        when(adminRepository.findAllByUsernameIsLike("%Admin%"))
                .thenReturn(List.of());

        List<GetUserDTO> actualResult = userServiceMock.getAllUserByName("Admin");

        List<GetUserDTO> expectedResult = List.of();

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findAllByFirstnameIsLikeOrLastnameIsLikeOrUsernameIsLike("%Admin%","%Admin%","%Admin%");
        verify(adminRepository).findAllByUsernameIsLike("%Admin%");
    }

    //deleteUser
    @Test
    void deleteUser_userDosntExist() {
        when(normalUserRepository.findByUsername("Worker"))
                .thenReturn(null);

        assertThrows(UserNotFoundException.class, () ->  userServiceMock.deleteUser("Worker"));

        verify(normalUserRepository).findByUsername("Worker");}
    @Test
    void deleteUser_userExists() {
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(normalUserRepository.findByUsername("Worker"))
                .thenReturn(worker);

        boolean actualResult = userServiceMock.deleteUser("Worker");

        assertEquals(true, actualResult);

        verify(normalUserRepository).findByUsername("Worker");
        verify(normalUserRepository).deleteById(1l);
    }

    //isUsernameUnique
    @Test
    void isUsernameUnique_ResultAdmin() {
        when(normalUserRepository.existsByUsername("Admin"))
                .thenReturn(false);
        when(adminRepository.existsByUsername("Admin"))
                .thenReturn(true);

        Boolean actualResult = userServiceMock.isUsernameUnique("Admin");

        assertEquals(false, actualResult);

        verify(normalUserRepository).existsByUsername("Admin");
        verify(adminRepository).existsByUsername("Admin");
    }
    @Test
    void isUsernameUnique_ResultNormalUser() {
        Boolean actualResult = userServiceMock.isUsernameUnique("Worker");

        assertEquals(true, actualResult);

        verify(normalUserRepository).existsByUsername("Worker");
    }
    @Test
    void isUsernameUnique_ResultNothingFound() {
        when(normalUserRepository.existsByUsername("Admin2"))
                .thenReturn(false);
        when(adminRepository.existsByUsername("Admin2"))
                .thenReturn(false);

        Boolean actualResult = userServiceMock.isUsernameUnique("Admin2");

        assertEquals(true, actualResult);

        verify(normalUserRepository).existsByUsername("Admin2");
        verify(adminRepository).existsByUsername("Admin2");
    }
    @Test
    void isUsernameUnique_UserNameBelongsToUser_NormalUser() {
        when(normalUserRepository.existsByUsername("Admin"))
                .thenReturn(true);

        Boolean actualResult = userServiceMock.isUsernameUnique("Admin");

        assertEquals(false, actualResult);

        verify(normalUserRepository).existsByUsername("Admin");
    }
    @Test
    void isUsernameUnique_UserNameBelongsToUser_Admin() {
        when(normalUserRepository.existsByUsername("Admin2"))
                .thenReturn(false);
        when(adminRepository.existsByUsername("Admin2"))
                .thenReturn(true);

        Boolean actualResult = userServiceMock.isUsernameUnique("Admin2");

        assertEquals(false, actualResult);

        verify(normalUserRepository).existsByUsername("Admin2");
        verify(adminRepository).existsByUsername("Admin2");
    }

    //isPhoneNumberUnique
    @Test
    void isPhoneNumberUnique_ResultNormalUser() {
        when(normalUserRepository.existsByPhonenumber("Worker"))
                .thenReturn(true);

        Boolean actualResult = userServiceMock.isPhoneNumberUnique("Worker");

        assertEquals(true, actualResult);

        verify(normalUserRepository).existsByPhonenumber("Worker");
    }
    @Test
    void isPhoneNumberUnique_ResultNothingFound() {
        when(normalUserRepository.existsByPhonenumber("Worker2"))
                .thenReturn(false);

        Boolean actualResult = userServiceMock.isPhoneNumberUnique("Worker2");

        assertEquals(false, actualResult);

        verify(normalUserRepository).existsByPhonenumber("Worker2");
    }

    //isEmailUnique
    @Test
    void isEmailUnique_ResultNormalUser() {
        when(normalUserRepository.existsByEmail("Worker"))
                .thenReturn(false);

        Boolean actualResult = userServiceMock.isEmailUnique("Worker");

        assertEquals(false, actualResult);

        verify(normalUserRepository).existsByEmail("Worker");
    }
    @Test
    void isEmailUnique_ResultNothingFound() {
        when(normalUserRepository.existsByEmail("Worker2"))
                .thenReturn(false);

        Boolean actualResult = userServiceMock.isEmailUnique("Worker2");

        assertEquals(false, actualResult);

        verify(normalUserRepository).existsByEmail("Worker2");
    }

    //addUser
    @Test
    void addUser_UsernameNotUnique_NormalUser()  {
        when(normalUserRepository.existsByUsername("Worker"))
                .thenReturn(true);

        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("Worker","Worker","Worker","Worker","Worker","Worker");
        assertThrows(UsernameAlreadyExistsExeption.class, () ->  userServiceMock.addUser(createUserRequestDTO));

        verify(normalUserRepository).existsByUsername("Worker");
    }
    @Test
    void addUser_UsernameNotUnique_Admin()  {
        when(normalUserRepository.existsByUsername("Admin"))
                .thenReturn(false);
        when(adminRepository.existsByUsername("Admin"))
                .thenReturn(true);

        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("Admin","Admin","Admin","Admin","Admin","Admin");

        assertThrows(UsernameAlreadyExistsExeption.class, () -> userServiceMock.addUser(createUserRequestDTO));

        verify(normalUserRepository).existsByUsername("Admin");
        verify(adminRepository).existsByUsername("Admin");
    }
    @Test
    void addUser_EmailNotUnique() {
        when(normalUserRepository.existsByEmail("Worker"))
                .thenReturn(true);

        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("Worker","Worker","Worker","Worker","Worker","Worker");
        assertThrows(EmailAlreadyExistsExeption.class, () -> userServiceMock.addUser(createUserRequestDTO));

        verify(normalUserRepository).existsByEmail("Worker");
    }
    @Test
    void addUser_PhoneNumberNotUnique() {
        when(normalUserRepository.existsByPhonenumber("Worker"))
                .thenReturn(true);

        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("Worker","Worker","Worker","Worker","Worker","Worker");

        assertThrows(PhoneNumberAlreadyExistsExeption.class, () -> userServiceMock.addUser(createUserRequestDTO));

        verify(normalUserRepository).existsByPhonenumber("Worker");
    }
    @Test
    void addUser_AllInfoUnique()  {
        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("Worker1","Worker","Worker","Worker","Worker2","Worker3");

        NormalUser newUser = new NormalUser(createUserRequestDTO);
        NormalUser SavedUser = new NormalUser(1l,"Worker1","Worker","Worker","Worker","Worker2","Worker3");

        when(normalUserRepository.existsByPhonenumber("Worker2"))
                .thenReturn(false);
        when(normalUserRepository.existsByEmail("Worker3"))
                .thenReturn(false);
        when(normalUserRepository.save(newUser))
                .thenReturn(SavedUser);

        CreateUserResponseDTO actualResult = userServiceMock.addUser(createUserRequestDTO);

        CreateUserResponseDTO expectedResult = CreateUserResponseDTO.builder()
                .firstName(SavedUser.getFirstname())
                .build();
        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).existsByUsername("Worker1");
        verify(adminRepository).existsByUsername("Worker1");
        verify(normalUserRepository).existsByPhonenumber("Worker2");
        verify(normalUserRepository).existsByEmail("Worker3");
        verify(normalUserRepository).save(newUser);
    }

    //updateUser
    @Test
    void updateUser_UserDoesntExists() {
        when(normalUserRepository.findByUsername("Worker"))
                .thenReturn(null);

        UpdateUserRequestDTO updateRequestDTO = new UpdateUserRequestDTO("Worker","Worker","Worker","Worker","Worker");

        assertThrows(UserNotFoundException.class, () -> userServiceMock.updateUser(updateRequestDTO));

        verify(normalUserRepository).findByUsername("Worker");
    }
    @Test
    void updateUser_ResultSuccess() {
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker", Collections.emptyList());

        UpdateUserRequestDTO updateRequestDTO = new UpdateUserRequestDTO("Worker","Worker","Worker","Worker1","Worker2");
        NormalUser newUser = new NormalUser(1l, "Worker", "Worker", updateRequestDTO.getFirstName(), updateRequestDTO.getLastName(), updateRequestDTO.getPhoneNumber(), updateRequestDTO.getEmail(), Collections.emptyList());

        when(accessTokenDTO.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(accessTokenDTO.getUserId())
                .thenReturn(1L);
        when(normalUserRepository.findByUsername("Worker"))
                .thenReturn(worker);
        when(normalUserRepository.existsByPhonenumber("Worker1"))
                .thenReturn(false);
        when(normalUserRepository.existsByEmail("Worker2"))
                .thenReturn(false);
        when(normalUserRepository.save(newUser))
                .thenReturn(null);

        UpdateUserResponseDTO actualResult = userServiceMock.updateUser(updateRequestDTO);

        UpdateUserResponseDTO excpectedResult = UpdateUserResponseDTO.builder()
                .firstName(newUser.getFirstname())
                .build();

        assertEquals(excpectedResult, actualResult);

        verify(accessTokenDTO).hasRole("NORMALUSER");
        verify(accessTokenDTO).getUserId();
        verify(normalUserRepository).findByUsername("Worker");
        verify(normalUserRepository).existsByPhonenumber("Worker1");
        verify(normalUserRepository).existsByEmail("Worker2");
        verify(normalUserRepository).save(newUser);
    }
    @Test
    void updateUser_PhoneNumberNotUnique() {
        NormalUser worker = new NormalUser(2l,"Worker","Worker","Worker","Worker","Worker1","Worker1");

        UpdateUserRequestDTO updateRequestDTO = new UpdateUserRequestDTO("Worker","Worker","Worker","Worker","Worker" );

        when(accessTokenDTO.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(accessTokenDTO.getUserId())
                .thenReturn(2l);
        when(normalUserRepository.findByUsername("Worker"))
                .thenReturn(worker);
        when(normalUserRepository.existsByPhonenumber("Worker"))
                .thenReturn(true);

        assertThrows(PhoneNumberAlreadyExistsExeption.class, () ->  userServiceMock.updateUser(updateRequestDTO));

        verify(accessTokenDTO).hasRole("NORMALUSER");
        verify(accessTokenDTO).getUserId();
        verify(normalUserRepository).findByUsername("Worker");
        verify(normalUserRepository).existsByPhonenumber("Worker");
    }
    @Test
    void updateUser_PhoneNumberNotUnique_IsUsedByUserThatIsBeingUpdated() {
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker", Collections.emptyList());

        UpdateUserRequestDTO updateRequestDTO = new UpdateUserRequestDTO("Worker","Worker","Worker","Worker","Worker2");
        NormalUser newUser = new NormalUser(1l, "Worker", "Worker", updateRequestDTO.getFirstName(), updateRequestDTO.getLastName(), updateRequestDTO.getPhoneNumber(), updateRequestDTO.getEmail(), Collections.emptyList());

        when(accessTokenDTO.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(accessTokenDTO.getUserId())
                .thenReturn(1L);
        when(normalUserRepository.findByUsername("Worker"))
                .thenReturn(worker);
        when(normalUserRepository.existsByEmail("Worker2"))
                .thenReturn(false);
        when(normalUserRepository.save(newUser))
                .thenReturn(null);

        UpdateUserResponseDTO actualResult = userServiceMock.updateUser(updateRequestDTO);

        UpdateUserResponseDTO excpectedResult = UpdateUserResponseDTO.builder()
                .firstName(newUser.getFirstname())
                .build();

        assertEquals(excpectedResult, actualResult);

        verify(accessTokenDTO).hasRole("NORMALUSER");
        verify(accessTokenDTO).getUserId();
        verify(normalUserRepository).findByUsername("Worker");
        verify(normalUserRepository).existsByEmail("Worker2");
        verify(normalUserRepository).save(newUser);
    }
    @Test
    void updateUser_EmailNotUnique() {
        NormalUser worker = new NormalUser(2l,"Worker","Worker","Worker","Worker","Worker1","Worker1");

        UpdateUserRequestDTO updateRequestDTO = new UpdateUserRequestDTO("Worker","Worker","Worker","Worker","Worker" );

        when(accessTokenDTO.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(accessTokenDTO.getUserId())
                .thenReturn(2L);
        when(normalUserRepository.findByUsername("Worker"))
                .thenReturn(worker);
        when(normalUserRepository.existsByEmail("Worker"))
                .thenReturn(true);

        assertThrows(EmailAlreadyExistsExeption.class, () ->  userServiceMock.updateUser(updateRequestDTO));

        verify(accessTokenDTO).hasRole("NORMALUSER");
        verify(accessTokenDTO).getUserId();
        verify(normalUserRepository).findByUsername("Worker");
        verify(normalUserRepository).existsByEmail("Worker");

    }
    @Test
    void updateUser_EmailNotUnique_IsUsedByUserThatIsBeingUpdated() {
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker", Collections.emptyList());

        UpdateUserRequestDTO updateRequestDTO =  UpdateUserRequestDTO.builder()
                .username("Worker")
                .firstName("Worker")
                .lastName( "Worker")
                .email( "Worker")
                .phoneNumber("Worker2")
                .build();

        NormalUser newUser = new NormalUser(1l, "Worker", "Worker", updateRequestDTO.getFirstName(), updateRequestDTO.getLastName(), updateRequestDTO.getPhoneNumber(), updateRequestDTO.getEmail(),Collections.emptyList());

        when(accessTokenDTO.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(accessTokenDTO.getUserId())
                .thenReturn(1L);
        when(normalUserRepository.findByUsername("Worker"))
                .thenReturn(worker);
        when(normalUserRepository.existsByPhonenumber("Worker2"))
                .thenReturn(false);

        UpdateUserResponseDTO actualResult = userServiceMock.updateUser(updateRequestDTO);

        UpdateUserResponseDTO excpectedResult = UpdateUserResponseDTO.builder()
                .firstName(newUser.getFirstname())
                .build();

        assertEquals(excpectedResult, actualResult);

        verify(accessTokenDTO).hasRole("NORMALUSER");
        verify(accessTokenDTO).getUserId();
        verify(normalUserRepository).findByUsername("Worker");
        verify(normalUserRepository).existsByPhonenumber("Worker2");
        verify(normalUserRepository).save(newUser);
    }
    @Test
    void updateUser_isntNormalUser()  {
        UpdateUserRequestDTO updateRequestDTO =  UpdateUserRequestDTO.builder()
                .username("Worker")
                .firstName("Worker")
                .lastName( "Worker")
                .email( "Worker")
                .phoneNumber( "Worker")
                .build();

        NormalUser worker = new NormalUser(2l,"Worker","Worker","Worker","Worker","Worker1","Worker1");

        when(normalUserRepository.findByUsername("Worker"))
                .thenReturn(worker);

        when(accessTokenDTO.hasRole("NORMALUSER"))
                .thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () -> userServiceMock.updateUser(updateRequestDTO));

        verify(accessTokenDTO).hasRole("NORMALUSER");
    }
    @Test
    void updateUser_IDNotMatched()  {
        UpdateUserRequestDTO updateRequestDTO =  UpdateUserRequestDTO.builder()
                .username("Worker")
                .firstName("Worker")
                .lastName( "Worker")
                .email( "Worker")
                .phoneNumber( "Worker")
                .build();
        NormalUser worker = new NormalUser(2l,"Worker","Worker","Worker","Worker","Worker1","Worker1");

        when(normalUserRepository.findByUsername("Worker"))
                .thenReturn(worker);
        when(accessTokenDTO.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(accessTokenDTO.getUserId())
                .thenReturn(1l);

        assertThrows(InvalidCredentialsException.class, () -> userServiceMock.updateUser(updateRequestDTO));

        verify(accessTokenDTO).hasRole("NORMALUSER");
        verify(accessTokenDTO).getUserId();
    }
}