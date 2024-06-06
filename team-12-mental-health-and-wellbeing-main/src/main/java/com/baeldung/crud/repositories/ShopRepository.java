package com.baeldung.crud.repositories;

import com.baeldung.crud.entities.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends CrudRepository<Shop, Long> {

    List<Shop> findByName(String name);

}

