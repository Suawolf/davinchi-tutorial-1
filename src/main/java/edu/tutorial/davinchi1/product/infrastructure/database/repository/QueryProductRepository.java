package edu.tutorial.davinchi1.product.infrastructure.database.repository;

import edu.tutorial.davinchi1.product.domain.entity.Product;
import edu.tutorial.davinchi1.product.infrastructure.database.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryProductRepository extends JpaRepository<ProductEntity,Long> {


}
