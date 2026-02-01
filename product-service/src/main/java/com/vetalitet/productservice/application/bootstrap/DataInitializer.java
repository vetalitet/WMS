package com.vetalitet.productservice.application.bootstrap;

import com.vetalitet.productservice.application.usecase.InitProductCategoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final InitProductCategoryUseCase initProductCategoryUseCase;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initProductCategoryUseCase.initData();
    }

}
