package edu.tutorial.davinchi1.product.infrastructure.database.seeder;

import edu.tutorial.davinchi1.product.domain.entity.Product;
import edu.tutorial.davinchi1.product.infrastructure.database.entity.ProductEntity;
import edu.tutorial.davinchi1.product.infrastructure.database.mapper.ProductEntityMapper;
import edu.tutorial.davinchi1.product.infrastructure.database.repository.QueryProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductSeeder implements CommandLineRunner {

    private final QueryProductRepository repository;
    private final ProductEntityMapper mapper;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;


    /** This method will run an start and will populate the DB */
    @Override
    public void run(String... args) throws Exception {

        long count = repository.count();

        if(count==0) {
            Resource resource = resourceLoader.getResource("classpath:data.json");
            List<ProductEntity> products = objectMapper.readValue(resource.getInputStream(), new TypeReference<>(){});

            repository.saveAll(products);

        }

    }
}
