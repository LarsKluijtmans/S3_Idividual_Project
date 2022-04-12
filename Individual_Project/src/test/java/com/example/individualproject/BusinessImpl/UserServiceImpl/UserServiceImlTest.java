package com.example.individualproject.BusinessImpl.UserServiceImpl;


public class UserServiceImlTest {

   /* @Mock
    private NormalUserRepository normalUserRepository;

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void test_GetAllUsers() {
        List<GetUserDTO> result = userService.getAllUsers();

        assertEquals(0, result.size());
    }

    @Test
    void test_GetAllNormalUsers() {
        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("lars","lars","lars","lars","lars","lars");

        userService.addUser(createUserRequestDTO);

        List<GetUserDTO> result = userService.getAllUsers();

        assertEquals(1, result.size());
    }

    @Test
    void test_GetAllAdmin() {
        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("lars","lars","lars","lars","lars","lars");

        userService.addUser(createUserRequestDTO);

        List<GetUserDTO> result = userService.getAllAdmins();

        assertEquals(0, result.size());
    }

    @Test
    void test_GetUserByID() {
        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("lars","lars","lars","lars","lars","lars");

        userService.addUser(createUserRequestDTO);

        GetUserDTO result = userService.getUserByID(1l);

        assertEquals("lars", result.getUsername());
        assertEquals("lars", result.getFirstName());
        assertEquals("lars", result.getLastName());
        assertEquals("lars", result.getPhoneNumber());
        assertEquals("lars", result.getEmail());
    }

    @Test
    void test_GetAllUserByName() {
        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("lars","lars","lars","lars","lars","lars");

        userService.addUser(createUserRequestDTO);

        List<GetUserDTO> result = userService.getAllUserByName("lars");

        assertEquals(1, result.size());


    }

    @Test
    void test_DeleteUser() {
        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("lars","lars","lars","lars","lars","lars");

        userService.addUser(createUserRequestDTO);

        List<GetUserDTO> result = userService.getAllUsers();

        assertEquals(1, result.size());

        userService.deleteUser(1l);

        result = userService.getAllUsers();

        assertEquals(0, result.size());

    }

    @Test
    void test_GetUserByAccount() {
        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("lars","lars","lars","lars","lars","lars");

        userService.addUser(createUserRequestDTO);

        GetUserDTO result = userService.getUser( new UserAccountRequestDTO("lars", "lars"));

        assertEquals("lars", result.getUsername());
    }

    @Test
    void test_AddUser() {
        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("lars","lars","lars","lars","lars","lars");

        userService.addUser(createUserRequestDTO);

        List<GetUserDTO> result = userService.getAllUsers();

        assertEquals(1, result.size());

         createUserRequestDTO = new CreateUserRequestDTO("lars2","lars","lars","lars","lars2","lars2");

        userService.addUser(createUserRequestDTO);

         result = userService.getAllUsers();

        assertEquals(2, result.size());
    }

    @Test
    void test_UpdateUser() {
        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO("lars","lars","lars","lars","lars","lars");

        userService.addUser(createUserRequestDTO);

        GetUserDTO result = userService.getUserByID(1l);

        assertEquals("lars", result.getUsername());
        assertEquals("lars", result.getFirstName());
        assertEquals("lars", result.getLastName());
        assertEquals("lars", result.getPhoneNumber());
        assertEquals("lars", result.getEmail());

        UpdateUserRequestDTO updateUserRequestDTO = new UpdateUserRequestDTO(1l,"lars2","lars2","lars2","lars2");

        userService.updateUser(updateUserRequestDTO);

        result = userService.getUserByID(1l);

        assertEquals("lars", result.getUsername());
        assertEquals("lars2", result.getFirstName());
        assertEquals("lars2", result.getLastName());
        assertEquals("lars2", result.getPhoneNumber());
        assertEquals("lars2", result.getEmail());
    }*/
}

