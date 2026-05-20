package edu.tutorial.davinchi1.product.application.query.getAll;

import edu.tutorial.davinchi1.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class GetAllProductResponse {

    private List<Product> products;
}
