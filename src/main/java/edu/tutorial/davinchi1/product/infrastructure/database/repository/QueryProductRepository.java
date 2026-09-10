package edu.tutorial.davinchi1.product.infrastructure.database.repository;

import edu.tutorial.davinchi1.product.domain.entity.Product;
import edu.tutorial.davinchi1.product.infrastructure.database.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueryProductRepository extends JpaRepository<ProductEntity,Long> {
    List<ProductEntity> findByNameContaining(String name);

    List<ProductEntity> findAllByPriceBetween(Double priceStart, Double priceEnd);

    @Query("""
            SELECT p
            FROM ProductEntity p
            WHERE p.name LIKE concat('%', :name, '%')
            OR p.description LIKE concat('%', :description, '%')
            OR p.price BETWEEN :priceAfter AND :priceBefore
            """)
    List<ProductEntity> findProductDetails(String name, String description, Double priceAfter, Double priceBefore);

    boolean existsByName(String name);

    long countByPriceBetween(Double priceStart, Double priceEnd);

    Page<ProductEntity> findAll(Pageable pageable);


}
