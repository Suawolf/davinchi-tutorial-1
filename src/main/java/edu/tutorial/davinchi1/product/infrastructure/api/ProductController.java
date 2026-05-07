package edu.tutorial.davinchi1.product.infrastructure.api;

import edu.tutorial.davinchi1.product.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController implements ProductApi {

    List<Product> products;

    public ProductController() {
        this.products = new ArrayList<>();
        products.add(Product.builder()
                .id(1L)
                .name("Product 1")
                .description("Description 1")
                .price(100.0)
                .image("image1")
                .build()
        );
        products.add(Product.builder()
                .id(2L)
                .name("Product 2")
                .description("Description 2")
                .price(200.0)
                .image("image2")
                .build()
        );
    }

    //  {{BASE URL}} = http://localhost:8080/api/v1
    //  {{BASE URL}}/products?pageSize=5
    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String pageSize) {
        return ResponseEntity.ok(products);
    }

    //  {{BASE URL}}/products/1
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        return productOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }


    //  {{BASE URL}}/products {JSON: BODY}
    @PostMapping("")
    public ResponseEntity<Void> saveProduct(@RequestBody Product product) {
        products.add(product);
        return ResponseEntity.created(URI.create("/api/v1/products/".concat(product.getId().toString()))).build();
    }

    @PutMapping("")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product selectedProduct = products.stream()
                .filter(p -> p.getId().equals(product.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));

        selectedProduct.setName(product.getName());
        selectedProduct.setDescription(product.getDescription());
        selectedProduct.setPrice(product.getPrice());
        selectedProduct.setImage(product.getImage());

        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        products.removeIf(p -> p.getId().equals(id));
        return ResponseEntity.noContent().build();
    }


}
