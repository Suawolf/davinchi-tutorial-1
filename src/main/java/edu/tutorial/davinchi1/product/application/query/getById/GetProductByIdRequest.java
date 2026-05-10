package edu.tutorial.davinchi1.product.application.query.getById;

import edu.tutorial.davinchi1.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetProductByIdRequest implements Request<GetProductByIdResponse> {

    private Long id;
}
