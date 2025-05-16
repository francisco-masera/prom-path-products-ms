package org.dargor.product.app.controller;

import java.util.List;

import org.dargor.product.app.dto.response.ProductResponseDto;
import org.dargor.product.app.dto.response.WishListResponseDto;
import org.dargor.product.app.dto.request.WishListRequestDto;
import org.dargor.product.app.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/wish-list/{customerId}")
    public ResponseEntity<List<ProductResponseDto>> getWishList(@PathVariable String customerId) {
        return ResponseEntity.ok(productService.getWishList(customerId));
    }

    @PostMapping("/create")
    public ResponseEntity<WishListResponseDto> createProducts(@RequestBody @Valid WishListRequestDto request) {
        var response = productService.createProducts(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
