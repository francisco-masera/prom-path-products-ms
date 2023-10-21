package org.dargor.product.app.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dargor.product.app.dto.ProductDto;
import org.dargor.product.app.dto.WishListDto;
import org.dargor.product.app.dto.WishListRequestDto;
import org.dargor.product.app.util.RedisUtil;
import org.dargor.product.core.entity.Product;
import org.dargor.product.core.repository.ProductRepository;
import org.dargor.product.core.util.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final ProductMapper productMapper = ProductMapper.INSTANCE;
    private final ProductRepository productRepository;
    private final RedisUtil<List<Product>> redisUtil;

    @Override
    @SuppressWarnings("unchecked")
    public List<ProductDto> getWishList(UUID customerId) {
        try {
            var redisObject = Optional.of(redisUtil.getValues("products", String.valueOf(List.class))).orElse(Collections.emptyList());
            var products = (List<Product>) redisObject;
            log.info(String.format("Redis has retrieved products: [ %s ] : size --> %d", products, products.size()));
            if (products.isEmpty()) {
                products = productRepository.findByCustomerId(customerId);
                redisUtil.storeValues("products", products, 5);
            }
            var response = productMapper.productsToProductDtoList(products);
            log.info(String.format("Product fetched successfully [customer %s] [response: %s]", customerId, response));
            return response;
        } catch (ClassNotFoundException e) {
            log.error(String.format("Error found getting products [customer %s] -  message: %s", customerId, e.getMessage()));
            return null;
        } catch (Exception e) {
            log.error(String.format("Error found getting products [customer %s] -  message: %s", customerId, e.getMessage()));
            throw e;
        }
    }


    @Override
    public WishListDto createProducts(WishListRequestDto request) {
        try {
            log.info(String.format("WishListDTO request %s", request));
            var products = productMapper.wishListRequestToProductList(request);
            var savedProducts = productRepository.saveAll(products);
            return productMapper.productsToWishListResponse(savedProducts);
        } catch (Exception e) {
            log.error(String.format("Error found saving products [customer %s]", request.toString()));
            throw e;
        }
    }
}
