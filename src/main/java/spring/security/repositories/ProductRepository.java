package spring.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import spring.security.entities.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>/*, JpaSpecificationExecutor<Product>*/ {
    //Page<Product> findAll(Specification<Product> spec, Pageable p);
    //void deleteById(Integer id);
   //Product save(Product product);
    //Optional<Product> findById(Integer id);
}
