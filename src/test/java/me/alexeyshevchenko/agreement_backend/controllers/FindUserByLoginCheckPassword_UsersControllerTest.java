package me.alexeyshevchenko.agreement_backend.controllers;

import me.alexeyshevchenko.agreement_backend.App;
import me.alexeyshevchenko.agreement_backend.dto.UserDTO;
import me.alexeyshevchenko.agreement_backend.models.UserEntity;
import me.alexeyshevchenko.agreement_backend.services.UsersService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class FindUserByLoginCheckPassword_UsersControllerTest {
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void findUserSuccessfull() throws Exception {
        UserDTO user = new UserDTO("user1111", "user1111", 1, "Ivanov", "Ivan");
        UserEntity savedUser = new UserEntity();
        savedUser.setLogin(user.getLogin());
        savedUser.setFirstName(user.getFirstName());
        savedUser.setLastName(user.getLastName());
        savedUser.setId(user.getId());
        String salt = "salt";
        savedUser.setSalt(salt);
        String hashedPassword = passwordEncoder.encode(user.getPassword() + salt);
        savedUser.setPassword(hashedPassword);
        String userJson = json(user);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/users/auth")
                .contentType(contentType)
                .content(userJson);
        Mockito.when(usersService.findUserByLogin(Mockito.any())).thenReturn(savedUser);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.login", Matchers.is(user.getLogin())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(user.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is(user.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.nullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is(user.getLastName())));}
    @Test
    public void findUserUserFromBaseIsNull() throws Exception {
        UserDTO user = new UserDTO("user1111", "user1111", 1, "Ivanov", "Ivan");
        UserEntity savedUser = null;
                String userJson = json(user);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/users/auth")
                .contentType(contentType)
                .content(userJson);
        Mockito.when(usersService.findUserByLogin(Mockito.any())).thenReturn(savedUser);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(404)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(404)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",  Matchers.allOf(Matchers.notNullValue(),
                        Matchers.is("User not found"))));}

    @Test
    public void findUserIncorrectPassword() throws Exception {
        UserDTO user = new UserDTO("user1111", "user1111", 1, "Ivanov", "Ivan");
        UserEntity savedUser = new UserEntity();
        savedUser.setLogin(user.getLogin());
        savedUser.setFirstName(user.getFirstName());
        savedUser.setLastName(user.getLastName());
        savedUser.setId(user.getId());
        String salt = "salt";
        savedUser.setSalt(salt);
        String hashedPassword = passwordEncoder.encode(user.getPassword() + salt+" ");
        savedUser.setPassword(hashedPassword);
        String userJson = json(user);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/users/auth")
                .contentType(contentType)
                .content(userJson);
        Mockito.when(usersService.findUserByLogin(Mockito.any())).thenReturn(savedUser);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",  Matchers.allOf(Matchers.notNullValue(),
                        Matchers.is("Incorect password"))));}
    @Test
    public void findUserIncorrectLogin() throws Exception {
        UserDTO user = new UserDTO("us", "user1111", 1, "Ivanov", "Ivan");
        UserEntity savedUser = new UserEntity();
        savedUser.setLogin(user.getLogin());
        savedUser.setFirstName(user.getFirstName());
        savedUser.setLastName(user.getLastName());
        savedUser.setId(user.getId());
        String salt = "salt";
        savedUser.setSalt(salt);
        String hashedPassword = passwordEncoder.encode(user.getPassword() + salt);
        savedUser.setPassword(hashedPassword);
        String userJson = json(user);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/users/auth")
                .contentType(contentType)
                .content(userJson);
        Mockito.when(usersService.findUserByLogin(Mockito.any())).thenReturn(savedUser);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",  Matchers.allOf(Matchers.notNullValue(),
                        Matchers.is("Incorrect Login, Please check and try again"))));}


    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
