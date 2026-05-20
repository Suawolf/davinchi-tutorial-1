package edu.tutorial.davinchi1.product.infrastructure.api.mapper;

import edu.tutorial.davinchi1.product.application.command.create.CreateProductRequest;
import edu.tutorial.davinchi1.product.application.command.update.UpdateProductRequest;
import edu.tutorial.davinchi1.product.domain.entity.Product;
import edu.tutorial.davinchi1.product.infrastructure.api.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {

    CreateProductRequest mapToCreateProductRequest(ProductDto productDto);

    UpdateProductRequest mapToUpdateProductRequest(ProductDto productDto);

    ProductDto mapToProduct(Product product);
}
