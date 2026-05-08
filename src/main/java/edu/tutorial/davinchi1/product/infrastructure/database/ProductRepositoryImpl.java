package edu.tutorial.davinchi1.product.infrastructure.database;

import edu.tutorial.davinchi1.product.domain.Product;
import edu.tutorial.davinchi1.product.domain.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private List<Product> products;

    public ProductRepositoryImpl() {
        this.products = new ArrayList<>();
//        products.add(Product.builder()
//                .id(1L)
//                .name("Product 1")
//                .description("Description 1")
//                .price(100.0)
//                .image("image1")
//                .build()
//        );
//        products.add(Product.builder()
//                .id(2L)
//                .name("Product 2")
//                .description("Description 2")
//                .price(200.0)
//                .image("image2")
//                .build()
//        );
    }

    @Override
    public void update(Product product) {
        products.add(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void deleteById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}
