package com.maker.shop.service;

import com.maker.shop.dto.ProductDTO;
import com.maker.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public String register(ProductDTO productDTO){
        return null;
    }
}
