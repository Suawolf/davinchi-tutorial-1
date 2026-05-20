package edu.tutorial.davinchi1.product.domain.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("The product with id: " + id + " was not found");
    }
}
