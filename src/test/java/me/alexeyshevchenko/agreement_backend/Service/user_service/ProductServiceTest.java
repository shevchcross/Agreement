package me.alexeyshevchenko.agreement_backend.Service.user_service;

import me.alexeyshevchenko.agreement_backend.dto.ProductDTO;
import me.alexeyshevchenko.agreement_backend.errors.ProductNotFoundException;
import me.alexeyshevchenko.agreement_backend.repository.ProductEntityRepository;
import me.alexeyshevchenko.agreement_backend.services.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductEntityRepository productEntityRepository;

    @BeforeEach
    public void init() {
        productEntityRepository.deleteAll();
    }

    @AfterEach
    public void after() {
        productEntityRepository.deleteAll();
    }

    @Test
    public void createProductSuccessfull() throws Exception {
        ProductDTO productInput = productService.createProduct(new ProductDTO("Milk"));
        ProductDTO productFomeBase = productService.findProductByName("Milk");
        Long a = productFomeBase.getId();
        assertThat(a, greaterThan(0L));
    }

    @Test
    public void getProductById() throws Exception {
        ProductDTO productInput = productService.createProduct(new ProductDTO("Milk"));
        ProductDTO productFomeBase = productService.getProductById(productInput.getId());

        assertThat(productFomeBase.getName(), equalToIgnoringCase(productInput.getName()));
    }

    @Test
    public void getUserByIdUserNotFound() throws Exception {
        ProductDTO productInput = productService.createProduct(new ProductDTO("Milk"));
        assertThrows(ProductNotFoundException.class,
                () -> {
                    productService.getProductById(productInput.getId() + 1);
                });
    }

    @Test
    public void findProductByNameSuccessfull() throws Exception {
        ProductDTO productInput = productService.createProduct(new ProductDTO("Milk"));
        ProductDTO productFromDB = productService.findProductByName(productInput.getName());

        assertThat(productFromDB.getId(), equalTo(productInput.getId()));
    }

    @Test
    public void findProductByNameProductNotFound() throws Exception {
        ProductDTO productInput = productService.createProduct(new ProductDTO("Milk"));
        assertThrows(ProductNotFoundException.class,
                () -> {
                    productService.findProductByName("12k3j4g56");
                });
    }

    @Test
    public void updateProductSuccessfull() throws Exception {
        ProductDTO productFromDB = productService.createProduct(new ProductDTO("Milk"));

        ProductDTO productUpdate = productService.updateProduct(new ProductDTO(productFromDB.getId(), "Milk 2,5%"));
        ProductDTO newProduct = productService.getProductById(productFromDB.getId());
        assertThat(newProduct.getName(), equalToIgnoringCase(productUpdate.getName()));
    }

    @Test
    public void updateProductNotFound() throws Exception {
        ProductDTO productFromDB = productService.createProduct(new ProductDTO("Milk"));
        ProductDTO productUpdate = new ProductDTO(productFromDB.getId() + 1, "Milk 2,5%");

        assertThrows(ProductNotFoundException.class,
                () -> {
                    productService.updateProduct(productUpdate);
                });
    }
    @Test
    public void deleteProductNotFound() throws Exception{
        ProductDTO productFromDB = productService.createProduct(new ProductDTO("Milk"));
        assertThrows(ProductNotFoundException.class,
                ()->{
                    productService.deleteProduct(productFromDB.getId()+1);
                });
    }
    @Test
    public void deleteUserSuccessfull() throws Exception{
        ProductDTO productFromDB = productService.createProduct(new ProductDTO("Milk"));
        productService.deleteProduct(productFromDB.getId());
        assertThrows(ProductNotFoundException.class,
                ()->{
                    productService.findProductByName(productFromDB.getName()+"q");;
                });}

    @Test
    public void findAllUsers() throws Exception{
        ProductDTO productFromDB = productService.createProduct(new ProductDTO("Milk"));
        ProductDTO productFromDB1 = productService.createProduct(new ProductDTO("Milk1"));
        ProductDTO productFromDB2 = productService.createProduct(new ProductDTO("Milk2"));
        ProductDTO productFromDB3 = productService.createProduct(new ProductDTO("Milk3"));
        ProductDTO productFromDB4 = productService.createProduct(new ProductDTO("Milk4"));
        ProductDTO productFromDB5 = productService.createProduct(new ProductDTO("Milk5"));
        ProductDTO productFromDB6 = productService.createProduct(new ProductDTO("Milk6"));
        ProductDTO productFromDB7 = productService.createProduct(new ProductDTO("Milk7"));
        ProductDTO productFromDB8 = productService.createProduct(new ProductDTO("Milk8"));
        ProductDTO productFromDB9 = productService.createProduct(new ProductDTO("Milk9"));
        ProductDTO productFromDB10 = productService.createProduct(new ProductDTO("Milk10"));

        List<ProductDTO> list = productService.findAllProduct(2,3);

        assertThat(list, hasSize(3));
    }
}

