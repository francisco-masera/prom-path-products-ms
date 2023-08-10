package org.dargor.product.app.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WishListDto {

    private List<ProductDto> products;

}
