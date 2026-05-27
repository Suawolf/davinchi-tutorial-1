package edu.tutorial.davinchi1.product.application.query.getById;

import edu.tutorial.davinchi1.product.domain.entity.Product;
import edu.tutorial.davinchi1.product.domain.exception.ProductNotFoundException;
import edu.tutorial.davinchi1.product.domain.port.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetProductByIdHandlerTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductByIdHandler getProductByIdHandler;

    @Test
    void shouldReturnProductWhenFound() {
        long id = 1L;
        Product product = Product.builder().id(id).build();
        GetProductByIdRequest request = new GetProductByIdRequest(id);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        GetProductByIdResponse response = getProductByIdHandler.handle(request);

        assertNotNull(response);
        assertEquals(product, response.getProduct());
    }

    @Test
    void shouldThrowExceptionWhenNotFound() {
        long id = 1L;
        GetProductByIdRequest request = new GetProductByIdRequest(id);
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> getProductByIdHandler.handle(request));
    }

}