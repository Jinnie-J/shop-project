package com.maker.shop.service;

import com.maker.shop.dto.PageRequestDTO;
import com.maker.shop.dto.PageResultDTO;
import com.maker.shop.dto.ProductDTO;
import com.maker.shop.entity.Product;
import com.maker.shop.entity.ProductImage;
import com.maker.shop.entity.ProductSize;
import com.maker.shop.repository.ProductImageRepository;
import com.maker.shop.repository.ProductRepository;
import com.maker.shop.repository.ProductSizeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductImageRepository productImageRepository;

    private final ProductSizeRepository productSizeRepository;

    @Override
    public PageResultDTO<ProductDTO, Object[]> getProductList(PageRequestDTO requestDTO, String gender,String category) {

        Pageable pageable = requestDTO.getPageable(Sort.by("pno").descending());

        Page<Object[]> result = productRepository.getProductListPage(pageable,gender, category);

        Function<Object[], ProductDTO> fn = (arr -> entitiesToDTO(
                (Product) arr[0],
                (List<ProductImage>)(Arrays.asList((ProductImage)arr[1]))

        ));

                return new PageResultDTO<>(result,fn);
    }

    @Transactional
    @Override
    public String register(ProductDTO productDTO) {

        Map<String, Object> entityMap = dtoToEntity(productDTO);
        Product product = (Product) entityMap.get("product");
        List<ProductImage> productImageList = (List<ProductImage>) entityMap.get("imgList");
        List<ProductSize> productSizeList = (List<ProductSize>) entityMap.get("sizeList");
        productRepository.save(product);

        productImageList.forEach(productImage -> {
            productImageRepository.save(productImage);
        });

        productSizeList.forEach(productSize -> {
            productSizeRepository.save(productSize);
        });

        return product.getPno();
    }
}
