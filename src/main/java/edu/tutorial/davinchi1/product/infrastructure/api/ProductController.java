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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Product", description = "Product API operations")
@RequiredArgsConstructor
@Slf4j
public class ProductController implements ProductApi {

    private final Mediator mediator;

    private final ProductMapper productMapper;

    //  {{BASE URL}} = http://localhost:8080/api/v1
    //  {{BASE URL}}/products?pageSize=5
    @Operation(summary = "Get all products", description = "Get all products")
    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getAllProducts(@RequestParam(required = false) String pageSize) {

        log.info("Getting all products...");

        GetAllProductResponse response = mediator.dispatch(new GetAllProductRequest());

        List<ProductDto> productDtos = response.getProducts().stream().map(productMapper::mapToProductDto).toList();

        log.info("A total of {} products found!", productDtos.size());

        return ResponseEntity.ok(productDtos);
    }

    //  {{BASE URL}}/products/1
    @Operation(summary = "Get product by id", description = "Get product by id")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {

        log.info("Getting product with ID: {} ...", id);

        GetProductByIdResponse response = mediator.dispatch(new GetProductByIdRequest(id));
        ProductDto productDto = productMapper.mapToProductDto(response.getProduct());

        log.info("Product with ID: {} found!", productDto.getId());

        return ResponseEntity.ok(productDto);
    }

    //  {{BASE URL}}/products {JSON: BODY}
    @Operation(summary = "Save product", description = "Save product")
    @PostMapping("")
    public ResponseEntity<Void> saveProduct(@ModelAttribute @Valid CreateProductDto productDto) {

        log.info("Saving product with ID: {} ...", productDto.getId());

        CreateProductRequest createProductRequest = productMapper.mapToCreateProductRequest(productDto);
        mediator.dispatch(createProductRequest);

        log.info("Product with ID: {} has been saved!", productDto.getId());

        return ResponseEntity.created(URI.create("/api/v1/products/".concat(productDto.getId().toString()))).build();
    }

    @Operation(summary = "Update product", description = "Update product")
    @PutMapping("")
    public ResponseEntity<ProductDto> updateProduct(@ModelAttribute @Valid UpdateProductDto productDto) {

        log.info("Updating product with ID: {} ...", productDto.getId());

        UpdateProductRequest request = productMapper.mapToUpdateProductRequest(productDto);
        mediator.dispatch(request);

        log.info("Product with ID: {} has been updated!", productDto.getId());

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete product", description = "Delete product")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        log.info("Deleting product with ID: {} ...", id);

        mediator.dispatchAsync(new DeleteProductRequest(id));

        log.info("Product with ID: {} has been deleted!", id);

        return ResponseEntity.noContent().build();
    }


}
