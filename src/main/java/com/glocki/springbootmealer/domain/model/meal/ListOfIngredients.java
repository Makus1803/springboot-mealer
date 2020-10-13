package com.glocki.springbootmealer.domain.model.meal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Document
public class ListOfIngredients {
    @Id
    private String id;

    private String mealName;

    private Set<String> listOfIngredients;

    @NotNull
    private String username;

    public ListOfIngredients() {
    }

    public ListOfIngredients(String mealName, Set<String> listOfIngredients, String user) {
        this.mealName = mealName;
        this.listOfIngredients = listOfIngredients;
        this.username = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public Set<String> getListOfIngredients() {
        return listOfIngredients;
    }

    public void setListOfIngredients(Set<String> listOfIngredients) {
        this.listOfIngredients = listOfIngredients;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String user) {
        this.username = user;
    }
}
