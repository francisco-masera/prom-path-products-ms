package org.dargor.product.app.service;

import java.util.List;

import org.dargor.product.app.dto.response.ProductResponseDto;
import org.dargor.product.app.dto.response.WishListResponseDto;
import org.dargor.product.app.dto.request.WishListRequestDto;

public interface ProductService {

    List<ProductResponseDto> getWishList(String customerId);

    WishListResponseDto createProducts(WishListRequestDto request);

}
