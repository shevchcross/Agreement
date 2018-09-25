package me.alexeyshevchenko.agreement_backend.services;

import me.alexeyshevchenko.agreement_backend.dto.ProductDTO;
import me.alexeyshevchenko.agreement_backend.errors.ProductNotFoundException;
import me.alexeyshevchenko.agreement_backend.models.ProductEntity;
import me.alexeyshevchenko.agreement_backend.repository.ProductEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
     private ProductEntityRepository  productEntityRepository;

    public ProductDTO createProduct (ProductDTO product) {
        ProductEntity producteToSave = new ProductEntity(product);
        ProductEntity savedProduct = productEntityRepository.save(producteToSave);
        return new ProductDTO(savedProduct);
            }

    public ProductDTO getProductById(Long id) throws ProductNotFoundException {
        Optional<ProductEntity> product = productEntityRepository.findById(id);
        ProductEntity productEntity = product.orElseThrow(() -> new ProductNotFoundException(id));
        return new ProductDTO(productEntity);
    }

    public ProductDTO findProductByName(String name) throws ProductNotFoundException {
        Optional<ProductEntity> product = productEntityRepository.findByName(name);
        ProductEntity productEntity = product.orElseThrow(() -> new ProductNotFoundException(name));
        return new ProductDTO(productEntity);
    }

    public ProductDTO updateProduct(ProductDTO product) throws ProductNotFoundException {
        ProductEntity oldProduct = productEntityRepository.findById(product.getId())
                .orElseThrow(() -> new ProductNotFoundException(product.getId()));
        oldProduct.setName(product.getName());
        productEntityRepository.save(oldProduct);
        return new ProductDTO(oldProduct);
    }

        public ProductDTO deleteProduct(Long id) throws ProductNotFoundException {
            Optional<ProductEntity> existedProduct = productEntityRepository.findById(id);
            ProductEntity product = existedProduct.orElseThrow(() -> new ProductNotFoundException(id));
            productEntityRepository.delete(product);
            return new ProductDTO(product);
    }
    public List<ProductDTO> findAllProduct(int pageNumber, int pageSize) {
        PageRequest request = PageRequest.of(pageNumber, pageSize);
        List<ProductEntity> productEntities = productEntityRepository.findAll(request).getContent();
        return productEntities.stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }
}
