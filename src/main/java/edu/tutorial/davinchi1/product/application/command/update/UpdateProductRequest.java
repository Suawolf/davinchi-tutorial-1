package edu.tutorial.davinchi1.product.application.command.update;

import edu.tutorial.davinchi1.common.mediator.Request;
import lombok.Data;

@Data
public class UpdateProductRequest implements Request<Void> {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;
}
