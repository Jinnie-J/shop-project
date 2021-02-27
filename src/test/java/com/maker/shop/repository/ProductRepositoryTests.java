package com.maker.shop.repository;

import com.maker.shop.entity.Product;
import com.maker.shop.entity.ProductImage;
import com.maker.shop.entity.ProductSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@SpringBootTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductSizeRepository productSizeRepository;

    @Commit
    @Transactional
    @Test
    public void insertProducts(){

        Product product = Product.builder()
                .pno("test2")
                .name("나이키 에어맥스")
                .price(20000L)
                .category("운동화")
                .brand("나이키")
                .sale(0L)
                .gender("남성")
                .build();

        productRepository.save(product);

       ProductImage productImage = ProductImage.builder()
                .uuid(UUID.randomUUID().toString())
                .product(product)
                .imgName("test2.jpg").build();

        productImageRepository.save(productImage);

        ProductSize productSize = ProductSize.builder()
                .size(275L)
                .amount(50L)
                .product(product)
                .build();

        productSizeRepository.save(productSize);
    }

    @Test
    public void getSizeDTO(){

        List<Object[]> result = productRepository.getProductSizeList("FLVSAA1U09");

        result.forEach(arr -> {
            System.out.println( "[1] :" + arr[0]);
        });
    }
}
