package edu.tutorial.davinchi1.product.application.query.getAll;

import edu.tutorial.davinchi1.common.domain.PaginationResult;
import edu.tutorial.davinchi1.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetAllProductResponse {

    private PaginationResult<Product> productsPage;
}
