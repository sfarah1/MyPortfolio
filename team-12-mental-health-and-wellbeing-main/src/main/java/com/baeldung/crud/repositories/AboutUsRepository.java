package com.baeldung.crud.repositories;
import com.baeldung.crud.entities.AboutUs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AboutUsRepository extends CrudRepository<AboutUs, Long> {

    List<AboutUs> findByName(String name);

}

