package spring.security.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import spring.security.entities.Product;


public class ProductSpecifications {
    public static Specification<Product> priceGreaterOrEqualsThan(Integer min_price) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), min_price));
    }
    public static Specification<Product> priceLessOrEqualsThan(Integer max_price) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), max_price));
    }
}
