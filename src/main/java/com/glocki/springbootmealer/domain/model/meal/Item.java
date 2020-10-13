
package com.glocki.springbootmealer.domain.model.meal;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "meals"
})
public class Item {

    @JsonProperty("meals")
    private List<Meal> meals = null;

    @JsonProperty("meals")
    public List<Meal> getMeals() {
        return meals;
    }

    @JsonProperty("meals")
    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

}
