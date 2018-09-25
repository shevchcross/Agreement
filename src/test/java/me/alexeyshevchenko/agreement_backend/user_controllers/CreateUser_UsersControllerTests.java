package me.alexeyshevchenko.agreement_backend.user_controllers;

import me.alexeyshevchenko.agreement_backend.dto.UserDTO;
import me.alexeyshevchenko.agreement_backend.services.UsersService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by ${Aleksey} on 03.08.2018.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class CreateUser_UsersControllerTests {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));//

    private MockMvc mockMvc;


    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @MockBean
    private UsersService usersService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
       // this.mockMvc =  MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

    }

    @Test
    public void createUserSuccessful() throws Exception {
        UserDTO user = new UserDTO("user1111", "user1111", 1, "Ivanov", "Ivan");
        UserDTO savedUser = new UserDTO();
        savedUser.setLogin(user.getLogin());
        savedUser.setFirstName(user.getFirstName());
        savedUser.setLastName(user.getLastName());
        String userJson = json(user);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/users")
                .contentType(contentType)
                .content(userJson);
        Mockito.when(usersService.createUser(Mockito.any())).thenReturn(savedUser);
                mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.login", Matchers.is(user.getLogin())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.nullValue()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is(user.getLastName())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is(user.getFirstName())));
    }
    @Test
    public void createUserWhenLoginToShort() throws Exception {
        UserDTO user = new UserDTO("user", "user1111", 1, "Ivanov", "Ivan");
        UserDTO savedUser = new UserDTO();
        savedUser.setLogin(user.getLogin());
        savedUser.setFirstName(user.getFirstName());
        savedUser.setLastName(user.getLastName());
        savedUser.setPassword(user.getPassword());
        String userJson = json(user);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/users")
                .contentType(contentType)
                .content(userJson);
        Mockito.when(usersService.createUser(Mockito.any())).thenReturn(savedUser);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.allOf(Matchers.notNullValue(),
                        Matchers.is("Incorrect Login or password, Please check and try again"))));

    }
    @Test
    public void createUserWhenPasswordTooShort() throws Exception {
        UserDTO user = new UserDTO("user", "user", 1, "Ivanov", "Ivan");
        UserDTO savedUser = new UserDTO();
        savedUser.setLogin(user.getLogin());
        savedUser.setFirstName(user.getFirstName());
        savedUser.setLastName(user.getLastName());
        savedUser.setPassword(user.getPassword());
        String userJson = json(user);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/users")
                .contentType(contentType)
                .content(userJson);
        Mockito.when(usersService.createUser(Mockito.any())).thenReturn(savedUser);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.allOf(Matchers.notNullValue(),
                        Matchers.is("Incorrect Login or password, Please check and try again"))));

    }

    @Test
    public void createUserWhenPasswordNull() throws Exception {
        UserDTO user = new UserDTO("user", null, 1, "Ivanov", "Ivan");
        UserDTO savedUser = new UserDTO();
        savedUser.setLogin(user.getLogin());
        savedUser.setFirstName(user.getFirstName());
        savedUser.setLastName(user.getLastName());
        savedUser.setPassword(user.getPassword());
        String userJson = json(user);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/users")
                .contentType(contentType)
                .content(userJson);
        Mockito.when(usersService.createUser(Mockito.any())).thenReturn(savedUser);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.allOf(Matchers.notNullValue(),
                        Matchers.is("Incorrect Login or password, Please check and try again"))));

    }
    @Test
    public void createUserWhenLoginNull() throws Exception {
        UserDTO user = new UserDTO(null, "user", 1, "Ivanov", "Ivan");
        UserDTO savedUser = new UserDTO();
        savedUser.setLogin(user.getLogin());
        savedUser.setFirstName(user.getFirstName());
        savedUser.setLastName(user.getLastName());
        savedUser.setPassword(user.getPassword());
        String userJson = json(user);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/users")
                .contentType(contentType)
                .content(userJson);
        Mockito.when(usersService.createUser(Mockito.any())).thenReturn(savedUser);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.allOf(Matchers.notNullValue(),
                        Matchers.is("Incorrect Login or password, Please check and try again"))));
    }


    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }


}
