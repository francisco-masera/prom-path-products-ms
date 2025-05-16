package org.dargor.product.app.service;

import java.util.List;

import org.dargor.product.app.dto.response.ProductResponseDto;
import org.dargor.product.app.dto.response.WishListResponseDto;
import org.dargor.product.app.dto.request.WishListRequestDto;
import org.dargor.product.app.util.RedisUtil;
import org.dargor.product.core.entity.Product;
import org.dargor.product.core.repository.ProductRepository;
import org.dargor.product.core.util.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final ProductMapper productMapper = ProductMapper.INSTANCE;
    private final ProductRepository productRepository;
    private final RedisUtil redisUtil;

    @Override
    public List<ProductResponseDto> getWishList(String customerId) {
        try {
            List<Product> products = redisUtil.getValues("products", customerId);
            if (products.isEmpty()) {
                products = productRepository.findByCustomerId(customerId);
                redisUtil.storeValues("products", String.valueOf(customerId), products, 5);
            }
            return productMapper.productsToProductDtoList(products);
        } catch (JsonProcessingException | ClassNotFoundException e) {
            log.error("Error found getting products [customer {}] -  message: {}", customerId, e.getMessage());
            return null;
        } catch (Exception e) {
            log.error("System error found getting products [customer {}] -  message: {}", customerId, e.getMessage());
            throw e;
        }
    }

    @Override
    public WishListResponseDto createProducts(WishListRequestDto request) {
        try {
            List<Product> products = productMapper.wishListRequestToProductList(request);
            List<Product> savedProducts = productRepository.saveAll(products);
            return productMapper.productsToWishListResponse(savedProducts);
        } catch (Exception e) {
            log.error("System error found saving products [customer {}]", request.toString());
            throw e;
        }
    }
}
