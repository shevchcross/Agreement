package me.alexeyshevchenko.agreement_backend.controllers;

import me.alexeyshevchenko.agreement_backend.App;
import me.alexeyshevchenko.agreement_backend.dto.UserDTO;
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
 * Created by ${Aleksey} on 08.08.2018.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class UpdateUser_UsersControllerTest {
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
    public void updateUser() throws Exception {
        UserDTO userToUpdate = new UserDTO("user1111", "user1111", 1, "Aleks", "Ivanov");
        UserDTO userFromStorage = new UserDTO("user1111", "user1111", 1, "Aleks", "Ivanov");
        String userJson = json(userToUpdate);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/users/"+  userToUpdate.getId())
                .contentType(contentType)
                .content(userJson);
        Mockito.when(usersService.getUserById(userToUpdate.getId())).thenReturn(userFromStorage);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is(userToUpdate.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is(userToUpdate.getFirstName())));
    }
    @Test
    public void updateUserIdIsNegative() throws Exception {
        UserDTO userToUpdate = new UserDTO("user1111", "user1111", -1, "Aleks", "Ivanov");
        UserDTO userFromStorage = new UserDTO("user1111", "user1111", -1, "Aleks", "Ivanov");
        String userJson = json(userToUpdate);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/users/"+  userToUpdate.getId())
                .contentType(contentType)
                .content(userJson);
        Mockito.when(usersService.getUserById(userToUpdate.getId())).thenReturn(userFromStorage);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.notNullValue()));
    }
    @Test
    public void updateUserlastNameNotValid() throws Exception {
        UserDTO userToUpdate = new UserDTO("user1111", "user1111", -1, "Al", "Ivanov");
        UserDTO userFromStorage = new UserDTO("user1111", "user1111", -1, "Al", "Ivanov");
        String userJson = json(userToUpdate);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/users/"+  userToUpdate.getId())
                .contentType(contentType)
                .content(userJson);
        Mockito.when(usersService.getUserById(userToUpdate.getId())).thenReturn(userFromStorage);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.notNullValue()));
    }
    @Test
    public void updateUserFirstNameNotValid() throws Exception {
        UserDTO userToUpdate = new UserDTO("user1111", "user1111", -1, "Aleksey", "Iv");
        UserDTO userFromStorage = new UserDTO("user1111", "user1111", -1, "Aleksey", "Iv");
        String userJson = json(userToUpdate);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/users/"+  userToUpdate.getId())
                .contentType(contentType)
                .content(userJson);
        Mockito.when(usersService.getUserById(userToUpdate.getId())).thenReturn(userFromStorage);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.notNullValue()));
    }
    @Test
    public void updateUserNonexistent() throws Exception {
        UserDTO userToUpdate = new UserDTO("user1111", "user1111", 1, "Aleksey", "Ivanov");
        UserDTO userFromStorage = new UserDTO("user1111", "user1111", 1, "Aleksey", "Ivanov");
        String userJson = json(userToUpdate);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/users/"+  userToUpdate.getId()+1)
                .contentType(contentType)
                .content(userJson);
        Mockito.when(usersService.getUserById(userToUpdate.getId())).thenReturn(userFromStorage);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.allOf(Matchers.notNullValue(),
                        Matchers.is("User not found"))));
    }
    @Test
    public void updateUserIdIncorect() throws Exception {
        UserDTO userToUpdate = new UserDTO("user1111", "user1111", 1, "Aleksey", "Ivanov");
        UserDTO userFromStorage = new UserDTO("user1111", "user1111", 1, "Aleksey", "Ivanov");
        String userJson = json(userToUpdate);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/users/" +(-2))
                .contentType(contentType)
                .content(userJson);
        Mockito.when(usersService.getUserById(userToUpdate.getId())).thenReturn(userFromStorage);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.allOf(Matchers.notNullValue(),
                        Matchers.is("Incorrect Id"))));
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
