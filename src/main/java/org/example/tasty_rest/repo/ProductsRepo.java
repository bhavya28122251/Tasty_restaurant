package org.example.tasty_rest.repo;

import org.example.tasty_rest.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Long> {
    @Query("SELECT p FROM Products p WHERE p.price BETWEEN :minPrice AND :maxPrice ORDER BY p.price ASC")
    List<Products> findTop2ProductsInPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice, Pageable pageable);
}