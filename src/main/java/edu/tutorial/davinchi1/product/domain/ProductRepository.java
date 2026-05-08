package edu.tutorial.davinchi1.product.domain;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    void update(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    void deleteById(Long id);
}
