package com.vetalitet.productservice.infrastructure.persistence;

import com.vetalitet.CommonException;
import com.vetalitet.productservice.domain.model.Product;
import com.vetalitet.productservice.domain.repository.ProductRepository;
import com.vetalitet.productservice.infrastructure.persistence.entity.ProductCategoryEntity;
import com.vetalitet.productservice.infrastructure.persistence.entity.ProductEntity;
import com.vetalitet.productservice.infrastructure.persistence.mappers.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SpringDataProductRepository implements ProductRepository {

    private final JpaProductRepository productRepository;
    private final JpaProductCategoryRepository productCategoryRepository;
    private final ProductMapper productMapper;

    @Override
    public Product save(Product product) {
        final Long categoryId = product.getProductCategory().getId();
        final String categoryName = product.getProductCategory().getName();
        ProductCategoryEntity productCategoryEntity = productCategoryRepository
                .findByIdAndName(categoryId, categoryName)
                .orElseThrow(() -> new CommonException("Unknown product category", HttpStatus.BAD_REQUEST));

        final ProductEntity productEntity = productMapper.toEntity(product);
        productEntity.setProductCategory(productCategoryEntity);
        productRepository.save(productEntity);

        product.setId(productEntity.getId());
        return product;
    }

    @Override
    public List<Product> getAll() {
        final List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream().map(productMapper::toDomain).toList();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        final Optional<ProductEntity> productEntity = productRepository.findById(id);
        return productEntity.map(productMapper::toDomain);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

}
