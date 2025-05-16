package org.dargor.product.core.util.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.dargor.product.app.dto.request.ProductRequestDto;
import org.dargor.product.app.dto.request.WishListRequestDto;
import org.dargor.product.app.dto.response.ProductResponseDto;
import org.dargor.product.app.dto.response.WishListResponseDto;
import org.dargor.product.core.entity.Product;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    default WishListResponseDto productsToWishListResponse(List<Product> products) {
        var wishList = new WishListResponseDto();
        wishList.setProducts(this.productsToProductDtoList(products));
        return wishList;
    }

    default List<Product> wishListRequestToProductList(WishListRequestDto wishList) {
        return wishList.getProducts()
                .stream()
                .map((productDto) -> productDtoToProduct(productDto, wishList.getCustomerId()))
                .collect(Collectors.toList());
    }

    @Mapping(target = "id", ignore = true)
    Product productDtoToProduct(ProductRequestDto productResponseDto, String customerId);

    @IterableMapping(qualifiedByName = "productToProductDto")
    List<ProductResponseDto> productsToProductDtoList(List<Product> products);

    @Named("productToProductDto")
    ProductResponseDto productToProductDto(Product product);

}
