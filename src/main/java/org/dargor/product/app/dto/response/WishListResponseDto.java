package org.dargor.product.app.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WishListResponseDto {

    private List<ProductResponseDto> products;

}
