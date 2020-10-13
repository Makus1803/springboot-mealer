package com.glocki.springbootmealer.domain.services;

import com.glocki.springbootmealer.domain.model.meal.ListOfIngredients;
import com.glocki.springbootmealer.domain.model.meal.ListOfIngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ListOfIngredientsService {
    private static final Logger LOGGER = Logger.getLogger(ListOfIngredientsService.class.getName());
    private ListOfIngredientsRepository listOfIngredientsRepository;

    @Autowired
    public ListOfIngredientsService(ListOfIngredientsRepository listOfIngredientsRepository) {
        this.listOfIngredientsRepository = listOfIngredientsRepository;
    }


    public List<ListOfIngredients> findAll() {
        return listOfIngredientsRepository.findAll();
    }

    public List<ListOfIngredients> findByUsername(String username){
        return listOfIngredientsRepository.findByUsername(username);
    }

    public void delete(ListOfIngredients listOfIngredients) {
        listOfIngredientsRepository.delete(listOfIngredients);
    }

    public void updateIngredients(ListOfIngredients listOfIngredients) {
        listOfIngredientsRepository.save(listOfIngredients);
    }

    public void save(ListOfIngredients listOfIngredients) {
        if (listOfIngredients == null) {


            LOGGER.log(Level.SEVERE,
                    "List is null!");
            return;
        }
        listOfIngredientsRepository.save(listOfIngredients);
    }}
