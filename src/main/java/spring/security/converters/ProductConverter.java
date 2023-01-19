package spring.security.converters;

import org.springframework.stereotype.Component;
import spring.security.entities.Product;
import spring.security.dto.ProductDto;

@Component
public class ProductConverter {

    public Product dtoToEntity(ProductDto productDto){
    return new Product(productDto.getId(), productDto.getTitle(), productDto.getPrice());
}

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }
}
