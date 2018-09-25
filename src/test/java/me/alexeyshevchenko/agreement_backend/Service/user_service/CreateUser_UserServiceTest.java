package me.alexeyshevchenko.agreement_backend.Service.user_service;


import me.alexeyshevchenko.agreement_backend.dto.UserDTO;
import me.alexeyshevchenko.agreement_backend.errors.LoginPasswordException;
import me.alexeyshevchenko.agreement_backend.errors.UserNotFoundException;
import me.alexeyshevchenko.agreement_backend.repository.UserEntityRepository;
import me.alexeyshevchenko.agreement_backend.services.UsersService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by ${Aleksey} on 31.08.2018.
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class CreateUser_UserServiceTest {
    @Autowired
    private UsersService usersService;

    @Autowired
    private UserEntityRepository userRepository;

    @BeforeEach
    public void init(){
        userRepository.deleteAll();
    }

    @AfterEach
    public void after(){
        userRepository.deleteAll();
    }

    @Test
    public void createUserSuccessfull() throws Exception{
       UserDTO userInput = usersService.createUser(new UserDTO("Login1234", "password123", "LastName", "FirstName"));
       UserDTO userFomeBase = usersService.findUserByLogin("Login1234");
       Long a = userFomeBase.getId();
       assertThat(a, greaterThan(0L));
    }
    @Test
    public void getUserByIdSuccessfull() throws Exception{
        UserDTO userInput = usersService.createUser(new UserDTO("Login1234", "password123", "LastName", "FirstName"));
        UserDTO userFromDB = usersService.getUserById(userInput.getId());

        assertThat(userFromDB.getLogin(), equalToIgnoringCase(userInput.getLogin()));
        assertThat(userFromDB.getFirstName(), equalToIgnoringCase(userInput.getFirstName()));
        assertThat(userFromDB.getLastName(), equalToIgnoringCase(userInput.getLastName()));
    }
    @Test
    public void getUserByIdUserNotFound() throws Exception{
        UserDTO userInput = usersService.createUser(new UserDTO("Login1234", "password123", "LastName", "FirstName"));
        assertThrows(UserNotFoundException.class,
        ()->{
            usersService.getUserById(userInput.getId()+1);
        });
    }
    @Test
    public void findUserByLoginSuccessfull() throws Exception{
        UserDTO userInput = usersService.createUser(new UserDTO("Login1234", "password123", "LastName", "FirstName"));
        UserDTO userFromDB = usersService.findUserByLogin(userInput.getLogin());

        assertThat(userFromDB.getId(), equalTo(userInput.getId()));
        assertThat(userFromDB.getFirstName(), equalToIgnoringCase(userInput.getFirstName()));
        assertThat(userFromDB.getLastName(), equalToIgnoringCase(userInput.getLastName()));
    }
    @Test
    public void findUserByLoginUserNotFound() throws Exception{
        UserDTO userInput = usersService.createUser(new UserDTO("Login1234", "password123", "LastName", "FirstName"));
        assertThrows(LoginPasswordException.class,
                ()->{
                    usersService.findUserByLogin("Login123456");
                });
    }
    @Test
    public void updateUserSuccessfull() throws Exception{
        UserDTO user = new UserDTO("Login1234", "password123", "LastName",
                "FirstName");
        UserDTO userFromDB = usersService.createUser(user);
        UserDTO user1 = new UserDTO(userFromDB.getLogin(), userFromDB.getPassword(), userFromDB.getId(),
                "ChangeLast", "ChangeFirst");
        UserDTO userUpdate = usersService.updateUser(user1);
        UserDTO newUser = usersService.getUserById(userFromDB.getId());
        assertThat(newUser.getFirstName(),equalToIgnoringCase (userUpdate.getFirstName()));
        assertThat(newUser.getLastName(),equalToIgnoringCase (userUpdate.getLastName()));

    }

    @Test
    public void updateUserUserNotFound() throws Exception{
        UserDTO user = new UserDTO("Login1234", "password123", "LastName",
                "FirstName");
        UserDTO userFromDB = usersService.createUser(user);
        UserDTO user1 = new UserDTO(userFromDB.getLogin(), userFromDB.getPassword(), userFromDB.getId()+1,
                "ChangeLast", "ChangeFirst");
                assertThrows(UserNotFoundException.class,
                ()->{
                    usersService.updateUser(user1);
                });
    }

    @Test
    public void deleteUserNotFound() throws Exception{
        UserDTO user = new UserDTO("Login1234", "password123", "LastName",
                "FirstName");
        UserDTO userFromDB = usersService.createUser(user);
             assertThrows(UserNotFoundException.class,
                ()->{
                    usersService.deleteUser(userFromDB.getId()+1);
                });
    }
    @Test
    public void deleteUserSuccessfull() throws Exception{
        UserDTO user = new UserDTO("Login1234", "password123", "LastName",
                "FirstName");
        UserDTO userFromDB = usersService.createUser(user);
        usersService.deleteUser(userFromDB.getId());
        assertThrows(LoginPasswordException.class,
                ()->{
                    usersService.findUserByLogin(user.getLogin());;
                });}

        @Test
        public void findAllUsers() throws Exception{
            UserDTO user = new UserDTO("Login1234", "password123", "LastName1",
                    "FirstName1");
            UserDTO user1 = new UserDTO("Login12345", "password123", "LastName2",
                    "FirstName2");
            UserDTO user2 = new UserDTO("Login123456", "password123", "LastName3",
                    "FirstName3");
            UserDTO user3 = new UserDTO("Login123478", "password123", "LastName4",
                    "FirstName4");
            UserDTO user4 = new UserDTO("Login1234789", "password123", "LastName5",
                    "FirstName4");
            UserDTO user5 = new UserDTO("Login12347891", "password123", "LastName6",
                    "FirstName4");
            UserDTO user6 = new UserDTO("Login123478911", "password123", "LastName7",
                    "FirstName4");
            UserDTO userFromDB = usersService.createUser(user);
            UserDTO userFromDB1 = usersService.createUser(user1);
            UserDTO userFromDB2 = usersService.createUser(user2);
            UserDTO userFromDB3 = usersService.createUser(user3);
            UserDTO userFromDB4 = usersService.createUser(user4);
            UserDTO userFromDB5 = usersService.createUser(user5);
            UserDTO userFromDB6 = usersService.createUser(user6);

            List <UserDTO> list = usersService.findAllUsers(2,2);
//            assertThat(list, contains(user));
//            assertThat(list, contains(user1));
            assertThat(list, hasSize(2));
    }}

