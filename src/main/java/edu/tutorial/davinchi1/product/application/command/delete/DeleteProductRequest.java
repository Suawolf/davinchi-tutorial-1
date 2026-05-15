package edu.tutorial.davinchi1.product.application.command.delete;

import edu.tutorial.davinchi1.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteProductRequest implements Request<Void> {

    private Long id;
}
