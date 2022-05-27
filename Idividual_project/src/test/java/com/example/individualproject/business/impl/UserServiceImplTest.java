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

        when(normalUserRepository.findAll())
                .thenReturn(List.of(worker));
        when(adminRepository.findAll())
                .thenReturn(List.of(boss));

        List<GetUserDTO> actualResult = userServiceMock.getAllUsers();

        GetUserDTO WorkerDTO = new GetUserDTO(worker);
        GetUserDTO AdminDTO = new GetUserDTO(boss);

        List<GetUserDTO> expectedResult = List.of(WorkerDTO,AdminDTO);

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findAll();
        verify(adminRepository).findAll();
    }

    //getAllNormalUsers
    @Test
    void getAllNormalUsers() {
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(normalUserRepository.findAll())
                .thenReturn(List.of(worker));

        List<GetUserDTO> actualResult = userServiceMock.getAllNormalUsers();

        GetUserDTO WorkerDTO = new GetUserDTO(worker);

        List<GetUserDTO> expectedResult = List.of(WorkerDTO);

        assertEquals(expectedResult, actualResult);

        verify(normalUserRepository).findAll();
    }

    //getAllAdmins
    @Test
    void getAllAdmins() {
        Admin boss = new Admin(1l,"Admin","Admin");

        when(adminRepository.findAll())
                .thenReturn(List.of(boss));

        List<GetUserDTO> actualResult = userServiceMock.getAllAdmins();

        GetUserDTO AdminDTO = new GetUserDTO(boss);

        List<GetUserDTO> expectedResult = List.of(AdminDTO);

        assertEquals(expectedResult, actualResult);

        verify(adminRepository).findAll();
    }

    //getUserByID
    @Test
    void getUserByID_ResultAdmin()  {
        Admin boss = new Admin(2L,"Admin","Admin");

        when(normalUserRepository.findAllByIdIs(1L))
                .thenReturn(null);
        when(adminRepository.findAllByIdIsLike(1L))
                .thenReturn(boss);
        when(accessTokenDTO.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(accessTokenDTO.getUserId())
                .thenReturn(1l);

        GetUserDTO actualResult = userServiceMock.getUserByID(1L);

        GetUserDTO AdminDTO = new GetUserDTO(boss);

        GetUserDTO expectedResult = AdminDTO;

        assertEquals(expectedResult, actualResult);

        verify(accessTokenDTO).hasRole("NORMALUSER");
        verify(accessTokenDTO).getUserId();
        verify(normalUserRepository).findAllByIdIs(1L);
        verify(adminRepository).findAllByIdIsLike(1L);
    }
    @Test
    void getUserByID_ResultNormalUser()  {
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        when(accessTokenDTO.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(accessTokenDTO.getUserId())
                .thenReturn(1l);
        when(normalUserRepository.findAllByIdIs(1L))
                .thenReturn(worker);

        GetUserDTO actualResult = userServiceMock.getUserByID(1L);

        GetUserDTO WorkerDTO = new GetUserDTO(worker);

        GetUserDTO expectedResult = WorkerDTO;

        assertEquals(expectedResult, actualResult);

        verify(accessTokenDTO).hasRole("NORMALUSER");
        verify(accessTokenDTO).getUserId();
        verify(normalUserRepository).findAllByIdIs(1L);
    }
    @Test
    void getUserByID_ResultNull()  {
        when(normalUserRepository.findAllByIdIs(1L))
                .thenReturn(null);
        when(adminRepository.findAllByIdIsLike(1L))
                .thenReturn(null);
        when(accessTokenDTO.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(accessTokenDTO.getUserId())
                .thenReturn(1l);

        GetUserDTO actualResult = userServiceMock.getUserByID(1L);

        assertEquals(null, actualResult);

        verify(accessTokenDTO).hasRole("NORMALUSER");
        verify(accessTokenDTO).getUserId();
        verify(normalUserRepository).findAllByIdIs(1L);
        verify(adminRepository).findAllByIdIsLike(1L);
    }
    @Test
    void getUserByID_isntNormalUser()  {
        when(accessTokenDTO.hasRole("NORMALUSER"))
                .thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () -> userServiceMock.getUserByID(1L));

        verify(accessTokenDTO).hasRole("NORMALUSER");
    }
    @Test
    void getUserByID_IDNotMatched()  {
        when(accessTokenDTO.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(accessTokenDTO.getUserId())
                .thenReturn(2l);

        assertThrows(InvalidCredentialsException.class, () -> userServiceMock.getUserByID(1L));

        verify(accessTokenDTO).hasRole("NORMALUSER");
        verify(accessTokenDTO).getUserId();
    }

    //getAllUserByName
    @Test
    void getAllUserByName_ResultNormalUser() {
        NormalUser worker = new NormalUser(1l, "Worker", "Worker", "Worker", "Worker", "Worker", "Worker");

        when(normalUserRepository.findAllByFirstnameIsLikeOrLastnameIsLikeOrUsernameIsLike("%Worker%","%Worker%","%Worker%"))
                .thenReturn(List.of(worker));
        when(adminRepository.findAllByUsernameIsLike("%Worker%"))
                .thenReturn(List.of());

        List<GetUserDTO> actualResult = userServiceMock.getAllUserByName("Worker");

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

        List<GetUserDTO> actualResult = userServiceMock.getAllUserByName("Admin");

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
        when(normalUserRepository.findAllByIdIs(1l))
                .thenReturn(null);
        when(accessTokenDTO.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(accessTokenDTO.getUserId())
                .thenReturn(1l);

        UpdateUserRequestDTO updateRequestDTO = new UpdateUserRequestDTO(1l,"Worker","Worker","Worker","Worker", null);
        UpdateUserResponseDTO actualResult = userServiceMock.updateUser(updateRequestDTO);

        assertEquals(null, actualResult);

        verify(accessTokenDTO).hasRole("NORMALUSER");
        verify(accessTokenDTO).getUserId();
        verify(normalUserRepository).findAllByIdIs(1l);
    }
    @Test
    void updateUser_ResultSuccess() {
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        UpdateUserRequestDTO updateRequestDTO = new UpdateUserRequestDTO(2l,"Worker","Worker","Worker1","Worker2", Collections.emptyList() );
        NormalUser newUser = new NormalUser(updateRequestDTO.getId(), "Worker", "Worker", updateRequestDTO.getFirstName(), updateRequestDTO.getLastName(), updateRequestDTO.getPhoneNumber(), updateRequestDTO.getEmail(), updateRequestDTO.getProductsSelling());

        when(accessTokenDTO.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(accessTokenDTO.getUserId())
                .thenReturn(2L);
        when(normalUserRepository.findAllByIdIs(2l))
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
        verify(normalUserRepository).findAllByIdIs(2l);
        verify(normalUserRepository).existsByPhonenumber("Worker1");
        verify(normalUserRepository).existsByEmail("Worker2");
        verify(normalUserRepository).save(newUser);
    }
    @Test
    void updateUser_PhoneNumberNotUnique() {
        NormalUser worker = new NormalUser(2l,"Worker","Worker","Worker","Worker","Worker1","Worker1");

        UpdateUserRequestDTO updateRequestDTO = new UpdateUserRequestDTO(2l,"Worker","Worker","Worker","Worker", null );

        when(accessTokenDTO.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(accessTokenDTO.getUserId())
                .thenReturn(2l);
        when(normalUserRepository.findAllByIdIs(2l))
                .thenReturn(worker);
        when(normalUserRepository.existsByPhonenumber("Worker"))
                .thenReturn(true);

        assertThrows(PhoneNumberAlreadyExistsExeption.class, () ->  userServiceMock.updateUser(updateRequestDTO));

        verify(accessTokenDTO).hasRole("NORMALUSER");
        verify(accessTokenDTO).getUserId();
        verify(normalUserRepository).findAllByIdIs(2l);
        verify(normalUserRepository).existsByPhonenumber("Worker");
    }
    @Test
    void updateUser_PhoneNumberNotUnique_IsUsedByUserThatIsBeingUpdated() {
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        UpdateUserRequestDTO updateRequestDTO = new UpdateUserRequestDTO(2l,"Worker","Worker","Worker","Worker2" , Collections.emptyList());
        NormalUser newUser = new NormalUser(updateRequestDTO.getId(), "Worker", "Worker", updateRequestDTO.getFirstName(), updateRequestDTO.getLastName(), updateRequestDTO.getPhoneNumber(), updateRequestDTO.getEmail(), updateRequestDTO.getProductsSelling());

        when(accessTokenDTO.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(accessTokenDTO.getUserId())
                .thenReturn(2L);
        when(normalUserRepository.findAllByIdIs(2l))
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
        verify(normalUserRepository).findAllByIdIs(2l);
        verify(normalUserRepository).existsByEmail("Worker2");
        verify(normalUserRepository).save(newUser);
    }
    @Test
    void updateUser_EmailNotUnique() {
        NormalUser worker = new NormalUser(2l,"Worker","Worker","Worker","Worker","Worker1","Worker1");

        UpdateUserRequestDTO updateRequestDTO = new UpdateUserRequestDTO(2l,"Worker","Worker","Worker","Worker", null );

        when(accessTokenDTO.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(accessTokenDTO.getUserId())
                .thenReturn(2L);
        when(normalUserRepository.findAllByIdIs(2l))
                .thenReturn(worker);
        when(normalUserRepository.existsByEmail("Worker"))
                .thenReturn(true);

        assertThrows(EmailAlreadyExistsExeption.class, () ->  userServiceMock.updateUser(updateRequestDTO));

        verify(accessTokenDTO).hasRole("NORMALUSER");
        verify(accessTokenDTO).getUserId();
        verify(normalUserRepository).findAllByIdIs(2l);
        verify(normalUserRepository).existsByEmail("Worker");

    }
    @Test
    void updateUser_EmailNotUnique_IsUsedByUserThatIsBeingUpdated() {
        NormalUser worker = new NormalUser(1l,"Worker","Worker","Worker","Worker","Worker","Worker");

        UpdateUserRequestDTO updateRequestDTO =  UpdateUserRequestDTO.builder()
                .id(2l)
                .firstName("Worker")
                .lastName( "Worker")
                .email( "Worker")
                .phoneNumber("Worker2")
                .productsSelling(Collections.emptyList())
                .build();
        NormalUser newUser = new NormalUser(updateRequestDTO.getId(), "Worker", "Worker", updateRequestDTO.getFirstName(), updateRequestDTO.getLastName(), updateRequestDTO.getPhoneNumber(), updateRequestDTO.getEmail(), updateRequestDTO.getProductsSelling());

        when(accessTokenDTO.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(accessTokenDTO.getUserId())
                .thenReturn(2L);
        when(normalUserRepository.findAllByIdIs(2l))
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
        verify(normalUserRepository).findAllByIdIs(2l);
        verify(normalUserRepository).existsByPhonenumber("Worker2");
        verify(normalUserRepository).save(newUser);
    }
    @Test
    void updateUser_isntNormalUser()  {
        UpdateUserRequestDTO updateRequestDTO =  UpdateUserRequestDTO.builder()
                .id(2l)
                .firstName("Worker")
                .lastName( "Worker")
                .email( "Worker")
                .phoneNumber( "Worker")
                .productsSelling(Collections.emptyList())
                .build();

        when(accessTokenDTO.hasRole("NORMALUSER"))
                .thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () -> userServiceMock.updateUser(updateRequestDTO));

        verify(accessTokenDTO).hasRole("NORMALUSER");
    }
    @Test
    void updateUser_IDNotMatched()  {
        UpdateUserRequestDTO updateRequestDTO =  UpdateUserRequestDTO.builder()
                .id(2l)
                .firstName("Worker")
                .lastName( "Worker")
                .email( "Worker")
                .phoneNumber( "Worker")
                .productsSelling(Collections.emptyList())
                .build();

        when(accessTokenDTO.hasRole("NORMALUSER"))
                .thenReturn(true);
        when(accessTokenDTO.getUserId())
                .thenReturn(1l);

        assertThrows(InvalidCredentialsException.class, () -> userServiceMock.updateUser(updateRequestDTO));

        verify(accessTokenDTO).hasRole("NORMALUSER");
        verify(accessTokenDTO).getUserId();
    }
}