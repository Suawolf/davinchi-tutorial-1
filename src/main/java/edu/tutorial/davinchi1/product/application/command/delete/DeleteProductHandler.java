package edu.tutorial.davinchi1.product.application.command.delete;

import edu.tutorial.davinchi1.common.mediator.RequestHandler;
import edu.tutorial.davinchi1.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProductHandler implements RequestHandler<DeleteProductRequest, Void> {

    private final ProductRepository productRepository;

    @Override
    public Void handle(DeleteProductRequest request) {

        System.out.println("Deleting product with id: " + request.getId() + "...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        productRepository.deleteById(request.getId());

        System.out.println("Product with id: " + request.getId() + " deleted!");

        return null;
    }

    @Override
    public Class<DeleteProductRequest> getRequestType() {
        return DeleteProductRequest.class;
    }
}
