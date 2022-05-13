package com.example.individualproject.business.impl;

import com.example.individualproject.business.UserService;
import com.example.individualproject.business.exception.EmailAlreadyExistsExeption;
import com.example.individualproject.business.exception.PhoneNumberAlreadyExistsExeption;
import com.example.individualproject.business.exception.UsernameAlreadyExistsExeption;
import com.example.individualproject.dto.login.AccessTokenDTO;
import com.example.individualproject.dto.users.*;
import com.example.individualproject.repository.AdminRepository;
import com.example.individualproject.repository.ImageRepository;
import com.example.individualproject.repository.NormalUserRepository;
import com.example.individualproject.repository.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

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

    @InjectMocks
    private UserServiceImpl userServiceeMock;

    @Test
    void getAllUsers() {
        Admin boss = new Admin(1l,"Admin","Admin");
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(normalUserRepository.findAll())
                .thenReturn(List.of(worker));
        when(adminRepository.findAll())
                .thenReturn(List.of(boss));

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
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(normalUserRepository.findAll())
                .thenReturn(List.of(worker));

        List<GetUserDTO> actualResult = userServiceeMock.getAllNormalUsers();

        GetUserDTO WorkerDTO = new GetUserDTO(worker);

        List<GetUserDTO> expectedResult = List.of(WorkerDTO);

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findAll();
    }

    @Test
    void getAllAdmins() {
        Admin boss = new Admin(1l,"Admin","Admin");

        when(adminRepository.findAll())
                .thenReturn(List.of(boss));

        List<GetUserDTO> actualResult = userServiceeMock.getAllAdmins();

        GetUserDTO AdminDTO = new GetUserDTO(boss);

        List<GetUserDTO> expectedResult = List.of(AdminDTO);

        assertEquals(expectedResult, actualResult);

        verify(adminRepository).findAll();
    }

    @Test
    void getUserByID_ResultAdmin()  {
        Admin boss = new Admin(2L,"Admin","Admin");

        when(normalUserRepository.findAllByIdIs(1L))
                .thenReturn(null);
        when(adminRepository.findAllByIdIsLike(1L))
                .thenReturn(boss);

        GetUserDTO actualResult = userServiceeMock.getUserByID(1L);

        GetUserDTO AdminDTO = new GetUserDTO(boss);

        GetUserDTO expectedResult = AdminDTO;

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findAllByIdIs(1L);
        verify(adminRepository).findAllByIdIsLike(1L);


    }
    @Test
    void getUserByID_ResultNormalUser()  {
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(normalUserRepository.findAllByIdIs(1L))
                .thenReturn(worker);

        GetUserDTO actualResult = userServiceeMock.getUserByID(1L);

        GetUserDTO WorkerDTO = new GetUserDTO(worker);

        GetUserDTO expectedResult = WorkerDTO;

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findAllByIdIs(1L);
    }
    @Test
    void getUserByID_ResultNull()  {
        when(normalUserRepository.findAllByIdIs(1L))
                .thenReturn(null);
        when(adminRepository.findAllByIdIsLike(1L))
                .thenReturn(null);

        GetUserDTO actualResult = userServiceeMock.getUserByID(1L);

        assertEquals(null, actualResult);

        verify(normalUserRepository).findAllByIdIs(1L);
        verify(adminRepository).findAllByIdIsLike(1L);
    }

    @Test
    void getAllUserByName_ResultNormalUser() {
        NormalUser worker = new NormalUser(1l, "Worker", "Worker", "Worker", "Worker", "Worker", "Worker");

        when(normalUserRepository.findAllByFirstnameIsLikeOrLastnameIsLikeOrUsernameIsLike("%Worker%","%Worker%","%Worker%"))
                .thenReturn(List.of(worker));
        when(adminRepository.findAllByUsernameIsLike("%Worker%"))
                .thenReturn(List.of());

        List<GetUserDTO> actualResult = userServiceeMock.getAllUserByName("Worker");

        GetUserDTO WorkerDTO = new GetUserDTO(worker);

        List<GetUserDTO> expectedResult = List.of(WorkerDTO);

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findAllByFirstnameIsLikeOrLastnameIsLikeOrUsernameIsLike("%Worker%","%Worker%","%Worker%");
        verify(adminRepository).findAllByUsernameIsLike("%Worker%");
    }
    @Test
    void getAllUserByName_ResultAdmin() {
        Admin boss = new Admin(1l, "Admin", "Admin");

        when(normalUserRepository.findAllByFirstnameIsLikeOrLastnameIsLikeOrUsernameIsLike("%Admin%","%Admin%","%Admin%"))
                .thenReturn(List.of());
        when(adminRepository.findAllByUsernameIsLike("%Admin%"))
                .thenReturn(List.of(boss));

        List<GetUserDTO> actualResult = userServiceeMock.getAllUserByName("Admin");

        GetUserDTO AdminDTO = new GetUserDTO(boss);

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

        List<GetUserDTO> actualResult = userServiceeMock.getAllUserByName("Admin");

        List<GetUserDTO> expectedResult = List.of();

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findAllByFirstnameIsLikeOrLastnameIsLikeOrUsernameIsLike("%Admin%","%Admin%","%Admin%");
        verify(adminRepository).findAllByUsernameIsLike("%Admin%");
    }

    @Test
    void deleteUser_userDosntExist() {
        when(normalUserRepository.findAllByIdIs(1l))
                .thenReturn(null);

        boolean actualResult = userServiceeMock.deleteUser(1l);

        assertEquals(false, actualResult);

        verify(normalUserRepository).findAllByIdIs(1l);
    }
    @Test
    void deleteUser_userExists() {
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(normalUserRepository.findAllByIdIs(1l))
                .thenReturn(worker);

        boolean actualResult = userServiceeMock.deleteUser(1l);

        assertEquals(true, actualResult);

        verify(normalUserRepository).findAllByIdIs(1l);
        verify(normalUserRepository).deleteById(1l);
    }

    @Test
    void isUsernameUnique_ResultAdmin() {
        when(normalUserRepository.existsByUsername("Admin"))
                .thenReturn(false);
        when(adminRepository.existsByUsername("Admin"))
                .thenReturn(true);

        Boolean actualResult = userServiceeMock.isUsernameUnique("Admin");

        assertEquals(false, actualResult);

        verify(normalUserRepository).existsByUsername("Admin");
        verify(adminRepository).existsByUsername("Admin");
    }
    @Test
    void isUsernameUnique_ResultNormalUser() {

        Boolean actualResult = userServiceeMock.isUsernameUnique("Worker");

        assertEquals(true, actualResult);

        verify(normalUserRepository).existsByUsername("Worker");
    }
    @Test
    void isUsernameUnique_ResultNothingFound() {
        when(normalUserRepository.existsByUsername("Admin2"))
                .thenReturn(false);
        when(adminRepository.existsByUsername("Admin2"))
                .thenReturn(false);

        Boolean actualResult = userServiceeMock.isUsernameUnique("Admin2");

        assertEquals(true, actualResult);

        verify(normalUserRepository).existsByUsername("Admin2");
        verify(adminRepository).existsByUsername("Admin2");
    }

    @Test
    void isPhoneNumberUnique_ResultNormalUser() {
        when(normalUserRepository.existsByPhonenumber("Worker"))
                .thenReturn(true);

        Boolean actualResult = userServiceeMock.isPhoneNumberUnique("Worker");

        assertEquals(true, actualResult);

        verify(normalUserRepository).existsByPhonenumber("Worker");
    }
    @Test
    void isPhoneNumberUnique_ResultNothingFound() {
        when(normalUserRepository.existsByPhonenumber("Worker2"))
                .thenReturn(false);

        Boolean actualResult = userServiceeMock.isPhoneNumberUnique("Worker2");

        assertEquals(false, actualResult);

        verify(normalUserRepository).existsByPhonenumber("Worker2");
    }

    @Test
    void isEmailUnique_ResultNormalUser() {
        when(normalUserRepository.existsByEmail("Worker"))
                .thenReturn(false);

        Boolean actualResult = userServiceeMock.isEmailUnique("Worker");

        assertEquals(false, actualResult);

        verify(normalUserRepository).existsByEmail("Worker");
    }
    @Test
    void isEmailUnique_ResultNothingFound() {
        when(normalUserRepository.existsByEmail("Worker2"))
                .thenReturn(false);

        Boolean actualResult = userServiceeMock.isEmailUnique("Worker2");

        assertEquals(false, actualResult);

        verify(normalUserRepository).existsByEmail("Worker2");
    }

    @Test
    void addUser_UsernameNotUnique_NormalUser()  {
        when(normalUserRepository.existsByUsername("Worker"))
                .thenReturn(true);


        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("Worker","Worker","Worker","Worker","Worker","Worker");
        assertThrows(UsernameAlreadyExistsExeption.class, () ->  userServiceeMock.addUser(createUserRequestDTO));

        verify(normalUserRepository).existsByUsername("Worker");
    }
    @Test
    void addUser_UsernameNotUnique_Admin()  {
        when(normalUserRepository.existsByUsername("Admin"))
                .thenReturn(false);
        when(adminRepository.existsByUsername("Admin"))
                .thenReturn(true);

        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("Admin","Admin","Admin","Admin","Admin","Admin");

        assertThrows(UsernameAlreadyExistsExeption.class, () -> userServiceeMock.addUser(createUserRequestDTO));

        verify(normalUserRepository).existsByUsername("Admin");
        verify(adminRepository).existsByUsername("Admin");
    }
    @Test
    void addUser_EmailNotUnique() {
        when(normalUserRepository.existsByEmail("Worker"))
                .thenReturn(true);

        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("Worker","Worker","Worker","Worker","Worker","Worker");
        assertThrows(EmailAlreadyExistsExeption.class, () -> userServiceeMock.addUser(createUserRequestDTO));

        verify(normalUserRepository).existsByEmail("Worker");
    }
    @Test
    void addUser_PhoneNumberNotUnique() {
        when(normalUserRepository.existsByPhonenumber("Worker"))
                .thenReturn(true);

        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("Worker","Worker","Worker","Worker","Worker","Worker");

        assertThrows(PhoneNumberAlreadyExistsExeption.class, () -> userServiceeMock.addUser(createUserRequestDTO));

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

        CreateUserResponseDTO actualResult = userServiceeMock.addUser(createUserRequestDTO);

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

    @Test
    void updateUser_UserDoesntExists() {
        when(normalUserRepository.findAllByIdIs(1l))
                .thenReturn(null);

        UpdateUserRequestDTO updateRequestDTO = new UpdateUserRequestDTO(1l,"Worker","Worker","Worker","Worker", null);
        UpdateUserResponseDTO actualResult = userServiceeMock.updateUser(updateRequestDTO);

        assertEquals(null, actualResult);

        verify(normalUserRepository).findAllByIdIs(1l);
    }
    @Test
    void updateUser_ResultSuccess() {
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        UpdateUserRequestDTO updateRequestDTO = new UpdateUserRequestDTO(2l,"Worker","Worker","Worker1","Worker2", Collections.emptyList() );
        NormalUser newUser = new NormalUser(updateRequestDTO.getId(), "Worker", "Worker", updateRequestDTO.getFirstName(), updateRequestDTO.getLastName(), updateRequestDTO.getPhoneNumber(), updateRequestDTO.getEmail(), updateRequestDTO.getProductsSelling());

        when(normalUserRepository.findAllByIdIs(2l))
                .thenReturn(worker);
        when(normalUserRepository.existsByPhonenumber("Worker1"))
                .thenReturn(false);
        when(normalUserRepository.existsByEmail("Worker2"))
                .thenReturn(false);
        when(normalUserRepository.save(newUser))
                .thenReturn(null);

        UpdateUserResponseDTO actualResult = userServiceeMock.updateUser(updateRequestDTO);

        UpdateUserResponseDTO excpectedResult = UpdateUserResponseDTO.builder()
                .firstName(newUser.getFirstname())
                .build();

        assertEquals(excpectedResult, actualResult);

        verify(normalUserRepository).findAllByIdIs(2l);
        verify(normalUserRepository).existsByPhonenumber("Worker1");
        verify(normalUserRepository).existsByEmail("Worker2");
        verify(normalUserRepository).save(newUser);
    }
    @Test
    void updateUser_PhoneNumberNotUnique() {
        NormalUser worker = new NormalUser(2l,"Worker","Worker","Worker","Worker","Worker1","Worker1");

        UpdateUserRequestDTO updateRequestDTO = new UpdateUserRequestDTO(2l,"Worker","Worker","Worker","Worker", null );

        when(normalUserRepository.findAllByIdIs(2l))
                .thenReturn(worker);
        when(normalUserRepository.existsByPhonenumber("Worker"))
                .thenReturn(true);

        assertThrows(PhoneNumberAlreadyExistsExeption.class, () ->  userServiceeMock.updateUser(updateRequestDTO));

        verify(normalUserRepository).findAllByIdIs(2l);
        verify(normalUserRepository).existsByPhonenumber("Worker");
    }
    @Test
    void updateUser_PhoneNumberNotUnique_IsUsedByUserThatIsBeingUpdated() {
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        UpdateUserRequestDTO updateRequestDTO = new UpdateUserRequestDTO(2l,"Worker","Worker","Worker","Worker2" , Collections.emptyList());
        NormalUser newUser = new NormalUser(updateRequestDTO.getId(), "Worker", "Worker", updateRequestDTO.getFirstName(), updateRequestDTO.getLastName(), updateRequestDTO.getPhoneNumber(), updateRequestDTO.getEmail(), updateRequestDTO.getProductsSelling());

        when(normalUserRepository.findAllByIdIs(2l))
                .thenReturn(worker);
        when(normalUserRepository.existsByEmail("Worker2"))
                .thenReturn(false);
        when(normalUserRepository.save(newUser))
                .thenReturn(null);

        UpdateUserResponseDTO actualResult = userServiceeMock.updateUser(updateRequestDTO);

        UpdateUserResponseDTO excpectedResult = UpdateUserResponseDTO.builder()
                .firstName(newUser.getFirstname())
                .build();

        assertEquals(excpectedResult, actualResult);

        verify(normalUserRepository).findAllByIdIs(2l);
        verify(normalUserRepository).existsByEmail("Worker2");
        verify(normalUserRepository).save(newUser);
    }
    @Test
    void updateUser_EmailNotUnique() {
        NormalUser worker = new NormalUser(2l,"Worker","Worker","Worker","Worker","Worker1","Worker1");

        UpdateUserRequestDTO updateRequestDTO = new UpdateUserRequestDTO(2l,"Worker","Worker","Worker","Worker", null );

        when(normalUserRepository.findAllByIdIs(2l))
                .thenReturn(worker);
        when(normalUserRepository.existsByEmail("Worker"))
                .thenReturn(true);

        assertThrows(EmailAlreadyExistsExeption.class, () ->  userServiceeMock.updateUser(updateRequestDTO));

        verify(normalUserRepository).findAllByIdIs(2l);
    }
    @Test
    void updateUser_EmailNotUnique_IsUsedByUserThatIsBeingUpdated() {
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        UpdateUserRequestDTO updateRequestDTO = new UpdateUserRequestDTO(2l,"Worker","Worker","Worker2","Worker", Collections.emptyList() );
        NormalUser newUser = new NormalUser(updateRequestDTO.getId(), "Worker", "Worker", updateRequestDTO.getFirstName(), updateRequestDTO.getLastName(), updateRequestDTO.getPhoneNumber(), updateRequestDTO.getEmail(), updateRequestDTO.getProductsSelling());

        when(normalUserRepository.findAllByIdIs(2l))
                .thenReturn(worker);
        when(normalUserRepository.existsByPhonenumber("Worker2"))
                .thenReturn(false);

        UpdateUserResponseDTO actualResult = userServiceeMock.updateUser(updateRequestDTO);

        UpdateUserResponseDTO excpectedResult = UpdateUserResponseDTO.builder()
                .firstName(newUser.getFirstname())
                .build();

        assertEquals(excpectedResult, actualResult);

        verify(normalUserRepository).findAllByIdIs(2l);
        verify(normalUserRepository).existsByPhonenumber("Worker2");
        verify(normalUserRepository).save(newUser);
    }
}