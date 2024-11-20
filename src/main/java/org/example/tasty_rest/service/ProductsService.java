package org.example.tasty_rest.service;

import org.example.tasty_rest.entity.Products;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService {



    @Autowired
    ProductsRepo repo;

    // To convert dto to entity
    private final ProductsMapper mapper;
    private final EncryptionService encryptionService;

    public Products addProduct(ProductsRequest request) {
        System.out.println("==================== create service");

        // This will convert our dto to entity using Mapper
        Products product = mapper.toEntity(request);
        repo.save(product);
        return product;
    }

    public Products getProductById(Long id) {
        System.out.println("==================== getProductById service");
        // return repo.findById(id).orElse(null); // Returns the product or null if not found
        System.out.println("==================== JPA REPO getProductById service");
        return repo.findById(id).orElse(null);
    }

    public List<Products> getAllProducts() {
        System.out.println("==================== getAllProducts service");
        // return repo.findAll(); // Returns all products
        return repo.findAll();
    }

    public List<Products> getTop2ProductsInPriceRange(double minPrice, double maxPrice) {
        Pageable pageable = PageRequest.of(0, 2);
        return repo.findTop2ProductsInPriceRange(minPrice, maxPrice, pageable);
    }
}
