package org.dargor.product.app.service;

import org.dargor.product.app.dto.ProductDto;
import org.dargor.product.app.dto.WishListDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<ProductDto> getWishList(UUID customerId);

    WishListDto createProducts(WishListDto request);
}
