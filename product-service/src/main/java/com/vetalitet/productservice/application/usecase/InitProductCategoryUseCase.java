package com.vetalitet.productservice.application.usecase;

import com.vetalitet.productservice.domain.model.ProductCategory;
import com.vetalitet.productservice.domain.repository.ProductCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class InitProductCategoryUseCase {

    private final ProductCategoryRepository productCategoryRepository;

    private final static List<ProductCategory> CATEGORIES = List.of(
            new ProductCategory("Home decor"),
            new ProductCategory("Jewelry"),
            new ProductCategory("Underwear"),
            new ProductCategory("Clothes"),
            new ProductCategory("Jackets")
    );

    public void initData() {
        if (productCategoryRepository.count() == 0) {
            productCategoryRepository.saveAll(CATEGORIES);
        }
    }

}
