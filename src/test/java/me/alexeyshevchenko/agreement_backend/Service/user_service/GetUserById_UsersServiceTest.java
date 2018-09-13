package me.alexeyshevchenko.agreement_backend.Service.user_service;

import me.alexeyshevchenko.agreement_backend.App;
import me.alexeyshevchenko.agreement_backend.repository.UserEntityRepository;
import me.alexeyshevchenko.agreement_backend.services.UsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by ${Aleksey} on 31.08.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class GetUserById_UsersServiceTest {

    @Autowired
    private UsersService usersService;



    @Test
    public void getUserById() throws Exception{

    }

}
