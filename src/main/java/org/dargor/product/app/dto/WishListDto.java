package org.dargor.product.app.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WishListDto {

    private UUID customerId;
    private List<ProductDto> products;

}
