package com.glocki.springbootmealer.domain.model.meal;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListOfIngredientsRepository extends MongoRepository<ListOfIngredients, String> {

    List<ListOfIngredients> findByUsername(String user);

}
