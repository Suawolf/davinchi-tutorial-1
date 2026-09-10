package edu.tutorial.davinchi1.product.domain.port;

import edu.tutorial.davinchi1.common.domain.PaginationQuery;
import edu.tutorial.davinchi1.common.domain.PaginationResult;
import edu.tutorial.davinchi1.product.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product upsert(Product product);

    Optional<Product> findById(Long id);

    PaginationResult<Product> findAll(PaginationQuery query);

    void deleteById(Long id);
}
