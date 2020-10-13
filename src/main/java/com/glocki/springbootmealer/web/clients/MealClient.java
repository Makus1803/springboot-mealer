package com.glocki.springbootmealer.web.clients;

import com.glocki.springbootmealer.domain.model.meal.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class MealClient {
    @GetMapping("/meat")
    public Item getMeatByName( String meatName){

        RestTemplate restTemplate = new RestTemplate();

        Item item = restTemplate.getForObject("https://www.themealdb.com/api/json/v1/1/search.php?s=" + meatName,
                Item.class);

        if (item == null)
            return new Item();

        return item;
    }
}
