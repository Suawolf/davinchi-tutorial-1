package edu.tutorial.davinchi1.product.infrastructure.database;

import edu.tutorial.davinchi1.common.domain.PaginationQuery;
import edu.tutorial.davinchi1.common.domain.PaginationResult;
import edu.tutorial.davinchi1.product.domain.entity.Product;
import edu.tutorial.davinchi1.product.domain.port.ProductRepository;
import edu.tutorial.davinchi1.product.infrastructure.database.entity.ProductEntity;
import edu.tutorial.davinchi1.product.infrastructure.database.mapper.ProductEntityMapper;
import edu.tutorial.davinchi1.product.infrastructure.database.repository.QueryProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductRepositoryImpl implements ProductRepository {

//    private final List<ProductEntity> products = new ArrayList<>(); //Eliminated when upgraded to JPA

    private final QueryProductRepository repository;

    private final ProductEntityMapper productEntityMapper;

    @Override
    public Product upsert(Product product) {
        ProductEntity productEntity = productEntityMapper.mapToProductEntity(product);
        //Old Method
//        products.removeIf(p -> p.getId().equals(productEntity.getId()));
//        products.add(productEntity);

        ProductEntity save = repository.save(productEntity);
        return productEntityMapper.mapToProduct(save);
    }

    @Cacheable(value = "products", key = "#id")
    @Override
    public Optional<Product> findById(Long id) {
        log.info("Finding product with id {}", id);
        return repository.findById(id).map(productEntityMapper::mapToProduct);
//        return products.stream()
//                .filter(p -> p.getId().equals(id))
//                .findFirst()
//                .map(productEntityMapper::mapToProduct);
    }

    @Override
    public PaginationResult<Product> findAll(PaginationQuery query) {
//        return repository.findAll().stream().map(productEntityMapper::mapToProduct).toList();
//        return products.stream()
//                .map(productEntityMapper::mapToProduct)
//                .toList();
        PageRequest pageRequest = PageRequest.of(query.getPage(), query.getSize());

        Page<ProductEntity> page= repository.findAll(pageRequest);

        return new PaginationResult<>(
                page.getContent().stream().map(productEntityMapper::mapToProduct).toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                (int) page.getTotalElements());
    }

    @CacheEvict(value = "products", key = "#id")
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
//        products.removeIf(p -> p.getId().equals(id));
    }
}
