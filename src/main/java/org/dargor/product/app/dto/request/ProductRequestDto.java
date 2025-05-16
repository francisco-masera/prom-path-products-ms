package org.dargor.product.app.dto.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    private String denomination;
    private String brand;
    private Long quantity;
    private BigDecimal unitPrice;
    private String customerId;

}
