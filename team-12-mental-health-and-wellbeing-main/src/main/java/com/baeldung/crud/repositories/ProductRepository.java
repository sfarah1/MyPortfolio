package com.baeldung.crud.repositories;

import com.baeldung.crud.entities.Category;
import com.baeldung.crud.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

    // The repository acts as an implementation for containing storage, retrieval and search behaviour
    // that emulates to the collection of objects.

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> { // This interface takes into consideration the primary key for the product.

}
