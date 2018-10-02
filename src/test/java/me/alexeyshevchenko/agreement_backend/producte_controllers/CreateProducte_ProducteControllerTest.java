package me.alexeyshevchenko.agreement_backend.producte_controllers;

import me.alexeyshevchenko.agreement_backend.dto.ProductDTO;
import me.alexeyshevchenko.agreement_backend.services.ProductService;
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

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class CreateProducte_ProducteControllerTest {
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));//

    private MockMvc mockMvc;


    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @MockBean
    private ProductService productService;

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
           }

    @Test
    public void createProducteSuccessful() throws Exception {
        ProductDTO producte = new ProductDTO(1, "Milk");
        ProductDTO savedProducte = new ProductDTO();
        savedProducte.setName(producte.getName());
        savedProducte.setId(producte.getId());
        String userJson = json(producte);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/products")
                .contentType(contentType)
                .content(userJson);
        Mockito.when(productService.createProduct(Mockito.any())).thenReturn(savedProducte);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.name", Matchers.is(producte.getName())));
    }
    @Test
    public void createProducteWhenNameTooShort() throws Exception {
        ProductDTO producte = new ProductDTO(1, "Mi");
        ProductDTO savedProducte = new ProductDTO();
        savedProducte.setName(producte.getName());
        String userJson = json(producte);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/products")
                .contentType(contentType)
                .content(userJson);
        Mockito.when(productService.createProduct(Mockito.any())).thenReturn(savedProducte);
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",
                        Matchers.allOf(Matchers.notNullValue()
                       )));

    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}