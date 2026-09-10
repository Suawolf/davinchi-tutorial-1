package edu.tutorial.davinchi1.product.infrastructure.api;

import edu.tutorial.davinchi1.common.domain.PaginationResult;
import edu.tutorial.davinchi1.product.infrastructure.api.dto.CreateProductDto;
import edu.tutorial.davinchi1.product.infrastructure.api.dto.ProductDto;
import edu.tutorial.davinchi1.product.infrastructure.api.dto.UpdateProductDto;
import edu.tutorial.davinchi1.product.infrastructure.database.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductApi {

    ResponseEntity<PaginationResult<ProductDto>> getAllProducts(@RequestParam(defaultValue = "0") int pageNumber,
                                                                @RequestParam(defaultValue = "5") int pageSize);

    ResponseEntity<ProductDto> getProductById(@PathVariable Long id);

    ResponseEntity<Void> saveProduct(@RequestBody CreateProductDto productDto);

    ResponseEntity<ProductDto> updateProduct(@RequestBody UpdateProductDto productDto);

    ResponseEntity<Void> deleteProduct(@PathVariable Long id);
}
