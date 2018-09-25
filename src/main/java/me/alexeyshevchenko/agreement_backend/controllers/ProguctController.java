package me.alexeyshevchenko.agreement_backend.controllers;

import me.alexeyshevchenko.agreement_backend.dto.ProductDTO;
import me.alexeyshevchenko.agreement_backend.errors.IdException;
import me.alexeyshevchenko.agreement_backend.errors.ProductNotFoundException;
import me.alexeyshevchenko.agreement_backend.errors.ProductValidException;
import me.alexeyshevchenko.agreement_backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.regex.Pattern;

@RequestMapping("/products")
@RestController
@Validated
public class ProguctController {

    @Autowired(required = false)
    private ProductService productService;

    @PostMapping()
    public
    @ResponseBody
    ProductDTO createProduct(@RequestBody @Valid ProductDTO product, BindingResult result)
            throws ProductValidException {
        if (result.hasErrors()) {
            throw new ProductValidException(product.getName());
        }
        return productService.createProduct(product);
    }

    @GetMapping(value = "/byname/{name}", consumes = {"application/json"})
    public
    @ResponseBody
    ProductDTO findProductByName(@PathVariable("name") String name)
            throws ProductValidException, ProductNotFoundException {
        if (!Pattern.matches("[0-9a-zA-Z]{3,45}", name)) {
            throw new ProductValidException(name);
        }
        ProductDTO productByName = productService.findProductByName(name);
        if (name == null) {
            throw new ProductNotFoundException(name);
        }
        return  productByName;}

    @GetMapping(value = "/{id}", produces = "application/json")
    public
    @ResponseBody
    ProductDTO getproductById(@PathVariable("id") Long productId)
            throws IdException, ProductNotFoundException {
        if (productId < 0) {
            throw new IdException( productId + "  -Is Incorrect Id");
        }
        ProductDTO productById = productService.getProductById(productId);
        if (productById != null && productId == (productById.getId())) {
            return productById;
        } else {
            throw new ProductNotFoundException(productId);
        }
    }

    @PutMapping(value = "/{id}", consumes = {"application/json"}, produces = "application/json")
    public
    @ResponseBody
    ProductDTO updateProducte(@PathVariable("id") Long productId,
                       @RequestBody @Valid ProductDTO product) throws IdException, ProductNotFoundException {
        if (productId <= 0) {
            throw new IdException( productId + "  -Is Incorrect Id");
        }
        ProductDTO oldProducte = productService.getProductById(productId);
        if (oldProducte != null && oldProducte.getId() == productId) {
            oldProducte.setName(product.getName());
            ProductDTO updatedProducte = productService.updateProduct(oldProducte);
            return updatedProducte;
        } else {
            throw new ProductNotFoundException(productId);
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public
    @ResponseBody
    ProductDTO deleteProducte(@PathVariable("id") Long productId) throws IdException, ProductNotFoundException {
        if (productId <= 0) {
            throw new IdException( productId + "  -Is Incorrect Id");
        }
        ProductDTO producteById = productService.getProductById(productId);
        if (producteById == null) {
            throw new ProductNotFoundException(productId);
        }
        return producteById;
    }
    @GetMapping()
    public
    @ResponseBody
    List<ProductDTO> findAllProductes(@RequestParam(value = "pageNumber" ,defaultValue = "1")
                               @Valid @Min(value = 1, message = "Page number should be more or equal 1") int pageNumber,
                               @RequestParam(value = "pageSize", defaultValue = "20")
                               @Valid @Min(value = 10, message = "Page size should be more or equal 10") int pageSize) {
        return  productService.findAllProduct(pageNumber, pageSize);
    }
}

