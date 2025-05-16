package org.dargor.product.core.repository;

import java.util.List;

import org.dargor.product.core.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByCustomerId(String customerId);

}
