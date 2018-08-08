package me.alexeyshevchenko.agreement_backend.controllers;

import me.alexeyshevchenko.agreement_backend.App;
import me.alexeyshevchenko.agreement_backend.dto.UserDTO;
import me.alexeyshevchenko.agreement_backend.Services.UsersService;
import me.alexeyshevchenko.agreement_backend.errors.LoginPasswordException;
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


/**
 * Created by ${Aleksey} on 07.08.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class FindUserByLogin_UsersControllerTest {
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

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void findUserByLogin() throws Exception {
        UserDTO user = new UserDTO("user1111", "user1111");
        String userJson = json(user);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/users/auth")
                .contentType(contentType)
                .content(userJson);
        Mockito.when(usersService.findUserByLogin(user.getLogin())).thenReturn(user);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.login", Matchers.is(user.getLogin())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is(user.getPassword())));
            }

    @Test
    public void findUserByLoginPasswordIncorrect() throws Exception {
        UserDTO user = new UserDTO("user1111", "user1111");
        UserDTO user2 = new UserDTO("user2222", "user2222");
        String userJson = json(user);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/users/auth")
                .contentType(contentType)
                .content(userJson);
        Mockito.when(usersService.findUserByLogin(user.getLogin())).thenReturn(user2);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.notNullValue()));
    }
    @Test
    public void findUserByLoginLoginIncorrect() throws Exception {
        UserDTO user = new UserDTO("us", "user1111");
        String userJson = json(user);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/users/auth")
                .contentType(contentType)
                .content(userJson);
        Mockito.when(usersService.findUserByLogin(user.getLogin())).thenReturn(user);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.notNullValue()));
    }
    @Test
    public void findUserByLoginLoginIsNull() throws Exception {
        UserDTO user = new UserDTO(null, "user1111");
        String userJson = json(user);
              MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/users/auth")
                .contentType(contentType)
                .content(userJson);
        Mockito.when(usersService.findUserByLogin(user.getLogin())).thenReturn(user);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.notNullValue()));
    }
    @Test
    public void findUserByLoginJsonIsEmpty() throws Exception {
        UserDTO user = new UserDTO("user1111", "user1111");
        String userJson = "";
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/users/auth")
                .contentType(contentType)
                .content(userJson);
        Mockito.when(usersService.findUserByLogin(user.getLogin())).thenReturn(user);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.notNullValue()));

    }
    @Test
    public void findUserByLoginJsonIsSomeText() throws Exception {
        UserDTO user = new UserDTO("user1111", "user1111");
        String userJson = "dbfdbfd";
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/users/auth")
                .contentType(contentType)
                .content(userJson);
        Mockito.when(usersService.findUserByLogin(user.getLogin())).thenReturn(user);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.notNullValue()));

    }
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
