package org.dargor.product.app.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    private String denomination;
    private String brand;
    private Long quantity;
    private BigDecimal unitPrice;
    private String customerId;

}
