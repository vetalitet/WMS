package com.vetalitet.productservice.infrastructure.persistence;

import com.vetalitet.productservice.domain.model.ProductCategory;
import com.vetalitet.productservice.domain.repository.ProductCategoryRepository;
import com.vetalitet.productservice.infrastructure.persistence.entity.ProductCategoryEntity;
import com.vetalitet.productservice.infrastructure.persistence.mappers.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class SpringDataProductCategoryRepository implements ProductCategoryRepository {

    private final JpaProductCategoryRepository productCategoryRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        final ProductCategoryEntity entity = productMapper.toEntity(productCategory);
        productCategoryRepository.save(entity);

        productCategory.setId(entity.getId());
        return productCategory;
    }

    @Override
    public Optional<ProductCategory> findById(Long id) {
        Optional<ProductCategoryEntity> productCategoryEntity = productCategoryRepository.findById(id);
        return productCategoryEntity.map(productMapper::toDomain);
    }

}
