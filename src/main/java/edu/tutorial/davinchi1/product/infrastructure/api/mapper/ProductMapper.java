package edu.tutorial.davinchi1.product.infrastructure.api.mapper;

import edu.tutorial.davinchi1.product.application.command.create.CreateProductRequest;
import edu.tutorial.davinchi1.product.application.command.update.UpdateProductRequest;
import edu.tutorial.davinchi1.product.domain.entity.Product;
import edu.tutorial.davinchi1.product.infrastructure.api.dto.CreateProductDto;
import edu.tutorial.davinchi1.product.infrastructure.api.dto.ProductDto;
import edu.tutorial.davinchi1.product.infrastructure.api.dto.UpdateProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {

    CreateProductRequest mapToCreateProductRequest(CreateProductDto createProductDto);

    UpdateProductRequest mapToUpdateProductRequest(UpdateProductDto updateProductDto);

    ProductDto mapToProduct(Product product);
}
