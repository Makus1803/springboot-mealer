
package com.glocki.springbootmealer.web.gui;

import com.glocki.springbootmealer.domain.model.meal.ListOfIngredients;
import com.glocki.springbootmealer.domain.model.meal.Meal;
import com.glocki.springbootmealer.domain.services.ListOfIngredientsService;
import com.glocki.springbootmealer.utils.Utils;
import com.glocki.springbootmealer.web.clients.MealClient;
import com.glocki.springbootmealer.web.gui.components.Menu;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route
public class MainView extends VerticalLayout {

    private MealClient mealClient;
    private ListOfIngredientsService listOfIngredientsService;

    private TextField textField;
    private Button search;

    public MainView(MealClient mealClient, ListOfIngredientsService listOfIngredientsService){
        this.mealClient = mealClient;
        this.listOfIngredientsService = listOfIngredientsService;

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        textField = new TextField("Food");
        search = new Button("Search");
        search.addClickListener(buttonClickEvent -> findMeal(textField.getValue()));


        add(Menu.showMenu());
        add(textField);
        add(search);
    }

    private void findMeal(String mealSearch){
        removeAll(); //delete all previous results
        add(Menu.showMenu());
        add(textField); //add textfield again
        add(search); //add search button again

        List<Meal> meals = mealClient.getMeatByName(mealSearch).getMeals();

        // Let's go for each meal in meals
        for (Meal meal: meals)
        {
            MultiSelectListBox<String> listBox = new MultiSelectListBox<>();
            List<String> ingredients = new ArrayList<>();
            ingredientsToList(ingredients, meal);
            listBox.setItems(ingredients);

            Text strMealName = new Text(meal.getStrMeal());
            Image image = new Image(meal.getStrMealThumb(), meal.getStrMeal());

            Details ingredientsList = new Details("Ingredients",
                    listBox);

            Details instructions = new Details("Instructions",
                    new Text(meal.getStrInstructions()));

            Button addToList = new Button("Add ingredients to shopping list");
            addToList.addClickListener(buttonClickEvent -> {
                if (listBox.getSelectedItems().isEmpty()){
                    Notification.show("No Ingredients selected");
                }
                else if(!Utils.isLogged()){
                    Notification.show("You must be logged in to safe your list.");
                }
                else {
                    listOfIngredientsService.save(new ListOfIngredients(meal.getStrMeal(), listBox.getSelectedItems(), Utils.currentUserName()));
                    Notification.show("Ingredients added to your list");
                }
            });
            ingredientsList.addContent(addToList);

            add(strMealName);
            add(image);
            add(ingredientsList);
            add(instructions);
        }
    }

    private void ingredientsToList(List<String> ingredients, Meal meal){
        if (!meal.getStrIngredient1().equals(""))
            ingredients.add(meal.getStrMeasure1() + " - " + meal.getStrIngredient1());
        if (!meal.getStrIngredient2().equals(""))
            ingredients.add(meal.getStrMeasure2() + " - " + meal.getStrIngredient2());
        if (!meal.getStrIngredient3().equals(""))
            ingredients.add(meal.getStrMeasure3() + " - " + meal.getStrIngredient3());
        if (!meal.getStrIngredient4().equals(""))
            ingredients.add(meal.getStrMeasure4() + " - " + meal.getStrIngredient4());
        if (!meal.getStrIngredient5().equals(""))
            ingredients.add(meal.getStrMeasure5() + " - " + meal.getStrIngredient5());
        if (!meal.getStrIngredient6().equals(""))
            ingredients.add(meal.getStrMeasure6() + " - " + meal.getStrIngredient6());
        if (!meal.getStrIngredient7().equals(""))
            ingredients.add(meal.getStrMeasure7() + " - " + meal.getStrIngredient7());
        if (!meal.getStrIngredient8().equals(""))
            ingredients.add(meal.getStrMeasure8() + " - " + meal.getStrIngredient8());
        if (!meal.getStrIngredient9().equals(""))
            ingredients.add(meal.getStrMeasure9() + " - " + meal.getStrIngredient9());
        if (!meal.getStrIngredient10().equals(""))
            ingredients.add(meal.getStrMeasure10() + " - " + meal.getStrIngredient10());
        if (!meal.getStrIngredient11().equals(""))
            ingredients.add(meal.getStrMeasure11() + " - " + meal.getStrIngredient11());
        if (!meal.getStrIngredient12().equals(""))
            ingredients.add(meal.getStrMeasure12() + " - " + meal.getStrIngredient12());
        if (!meal.getStrIngredient13().equals(""))
            ingredients.add(meal.getStrMeasure13() + " - " + meal.getStrIngredient13());
        if (!meal.getStrIngredient14().equals(""))
            ingredients.add(meal.getStrMeasure14() + " - " + meal.getStrIngredient14());
        if (!meal.getStrIngredient15().equals(""))
            ingredients.add(meal.getStrMeasure15() + " - " + meal.getStrIngredient15());
        if (!meal.getStrIngredient16().equals(""))
            ingredients.add(meal.getStrMeasure16() + " - " + meal.getStrIngredient16());
        if (!meal.getStrIngredient17().equals(""))
            ingredients.add(meal.getStrMeasure17() + " - " + meal.getStrIngredient17());
        if (!meal.getStrIngredient18().equals(""))
            ingredients.add(meal.getStrMeasure18() + " - " + meal.getStrIngredient18());
        if (!meal.getStrIngredient19().isEmpty())
            ingredients.add(meal.getStrMeasure19() + " - " + meal.getStrIngredient19());
        if (!meal.getStrIngredient20().equals(""))
            ingredients.add(meal.getStrMeasure20() + " - " + meal.getStrIngredient20());

    }

}
