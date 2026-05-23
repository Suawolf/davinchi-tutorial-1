package edu.tutorial.davinchi1.product.infrastructure.api;

import edu.tutorial.davinchi1.common.mediator.Mediator;
import edu.tutorial.davinchi1.product.application.command.create.CreateProductRequest;
import edu.tutorial.davinchi1.product.application.command.delete.DeleteProductRequest;
import edu.tutorial.davinchi1.product.application.command.update.UpdateProductRequest;
import edu.tutorial.davinchi1.product.application.query.getAll.GetAllProductRequest;
import edu.tutorial.davinchi1.product.application.query.getAll.GetAllProductResponse;
import edu.tutorial.davinchi1.product.application.query.getById.GetProductByIdRequest;
import edu.tutorial.davinchi1.product.application.query.getById.GetProductByIdResponse;
import edu.tutorial.davinchi1.product.infrastructure.api.dto.CreateProductDto;
import edu.tutorial.davinchi1.product.infrastructure.api.dto.ProductDto;
import edu.tutorial.davinchi1.product.infrastructure.api.dto.UpdateProductDto;
import edu.tutorial.davinchi1.product.infrastructure.api.mapper.ProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final Mediator mediator;

    private final ProductMapper productMapper;

    //  {{BASE URL}} = http://localhost:8080/api/v1
    //  {{BASE URL}}/products?pageSize=5
    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getAllProducts(@RequestParam(required = false) String pageSize) {
        GetAllProductResponse response = mediator.dispatch(new GetAllProductRequest());
        List<ProductDto> productDtos = response.getProducts().stream().map(productMapper::mapToProduct).toList();
        return ResponseEntity.ok(productDtos);
    }

    //  {{BASE URL}}/products/1
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        GetProductByIdResponse response = mediator.dispatch(new GetProductByIdRequest(id));
        ProductDto productDto = productMapper.mapToProduct(response.getProduct());
        return ResponseEntity.ok(productDto);
    }

    //  {{BASE URL}}/products {JSON: BODY}
    @PostMapping("")
    public ResponseEntity<Void> saveProduct(@ModelAttribute @Valid CreateProductDto productDto) {
        CreateProductRequest createProductRequest = productMapper.mapToCreateProductRequest(productDto);
        mediator.dispatch(createProductRequest);
        return ResponseEntity.created(URI.create("/api/v1/products/".concat(productDto.getId().toString()))).build();
    }

    @PutMapping("")
    public ResponseEntity<ProductDto> updateProduct(@ModelAttribute @Valid UpdateProductDto productDto) {
        UpdateProductRequest request = productMapper.mapToUpdateProductRequest(productDto);
        mediator.dispatch(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        mediator.dispatchAsync(new DeleteProductRequest(id));
        return ResponseEntity.noContent().build();
    }


}
