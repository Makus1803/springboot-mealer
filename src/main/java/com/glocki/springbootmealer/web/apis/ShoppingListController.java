package com.glocki.springbootmealer.web.apis;

import com.glocki.springbootmealer.domain.model.meal.ListOfIngredients;
import com.glocki.springbootmealer.domain.services.ListOfIngredientsService;
import com.glocki.springbootmealer.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/list")
public class ShoppingListController {
    private ListOfIngredientsService listOfIngredientsService;

    @Autowired
    public ShoppingListController(ListOfIngredientsService listOfIngredientsService) {
        this.listOfIngredientsService = listOfIngredientsService;
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<ListOfIngredients> showMyLists(){
        return listOfIngredientsService.findByUsername(Utils.currentUserName());
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ListOfIngredients> showAllLists(){
        return listOfIngredientsService.findAll();
    }
}
