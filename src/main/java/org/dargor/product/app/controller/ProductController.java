package org.dargor.product.app.controller;

import lombok.AllArgsConstructor;
import org.dargor.product.app.dto.ProductDto;
import org.dargor.product.app.dto.WishListDto;
import org.dargor.product.app.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/wish-list/{customerId}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable UUID customerId) {
        return ResponseEntity.ok(productService.getWishList(customerId));
    }

    @PostMapping("/create")
    public ResponseEntity<WishListDto> createProducts(@RequestBody @Valid WishListDto request) {
        var response = productService.createProducts(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
