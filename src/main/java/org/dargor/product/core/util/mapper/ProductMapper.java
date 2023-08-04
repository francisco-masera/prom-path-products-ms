package org.dargor.product.core.util.mapper;

import org.dargor.product.app.dto.ProductDto;
import org.dargor.product.app.dto.WishListDto;
import org.dargor.product.core.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = {Arrays.class})
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    default WishListDto productsToWishListResponse(List<Product> products) {
        var wishList = new WishListDto();
        wishList.setProducts(this.productsToProductDtoList(products));
        return wishList;
    }

    default List<Product> wishListRequestToProductList(WishListDto wishList) {
        return wishList.getProducts()
                .stream()
                .map(this::productDtoToProduct)
                .collect(Collectors.toList());
    }

    @Mapping(target = "id", ignore = true)
    Product productDtoToProduct(ProductDto productDto);

    List<ProductDto> productsToProductDtoList(List<Product> products);


}
