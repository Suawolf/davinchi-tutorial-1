package edu.tutorial.davinchi1.product.application.command.delete;

import edu.tutorial.davinchi1.common.mediator.RequestHandler;
import edu.tutorial.davinchi1.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteProductHandler implements RequestHandler<DeleteProductRequest, Void> {

    private final ProductRepository productRepository;

    @Override
    public Void handle(DeleteProductRequest request) {

        log.info("Deleting product with id: {} ...", request.getId());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        productRepository.deleteById(request.getId());

        log.info("Product with id: {} deleted!", request.getId());

        return null;
    }

    @Override
    public Class<DeleteProductRequest> getRequestType() {
        return DeleteProductRequest.class;
    }
}
