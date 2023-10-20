package org.dargor.product.core.util.mapper;

import org.dargor.product.app.dto.ProductDto;
import org.dargor.product.app.dto.WishListDto;
import org.dargor.product.app.dto.WishListRequestDto;
import org.dargor.product.core.entity.Product;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    default WishListDto productsToWishListResponse(List<Product> products) {
        var wishList = new WishListDto();
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
    Product productDtoToProduct(ProductDto productDto, UUID customerId);

    @IterableMapping(qualifiedByName = "productToProductDto")
    List<ProductDto> productsToProductDtoList(List<Product> products);

    @Named("productToProductDto")
    ProductDto productToProductDto(Product product);

}
