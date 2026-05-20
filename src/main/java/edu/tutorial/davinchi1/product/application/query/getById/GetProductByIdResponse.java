package edu.tutorial.davinchi1.product.application.query.getById;

import edu.tutorial.davinchi1.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetProductByIdResponse {

    private Product product;
}
