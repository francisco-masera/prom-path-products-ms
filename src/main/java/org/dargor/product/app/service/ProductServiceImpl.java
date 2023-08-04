package org.dargor.product.app.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dargor.product.app.dto.ProductDto;
import org.dargor.product.app.dto.WishListDto;
import org.dargor.product.core.repository.ProductRepository;
import org.dargor.product.core.util.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final ProductMapper productMapper = ProductMapper.INSTANCE;
    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> getWishList(UUID customerId) {
        try {
            var product = productRepository.findByCustomerId(customerId);
            var response = productMapper.productsToProductDtoList(product);
            log.info(String.format("Product fetched successfully [customer %s] [response: %s]", customerId, response));
            return response;
        } catch (Exception e) {
            log.error(String.format("Error found getting products [customer %s]", customerId));
            throw e;
        }
    }

    @Override
    public WishListDto createProducts(WishListDto request) {
        try {
            var products = productMapper.wishListRequestToProductList(request);
            var savedProducts = productRepository.saveAll(products);
            return productMapper.productsToWishListResponse(savedProducts);
        } catch (Exception e) {
            log.error(String.format("Error found saving products [customer %s]", request.toString()));
            throw e;
        }
    }
}
