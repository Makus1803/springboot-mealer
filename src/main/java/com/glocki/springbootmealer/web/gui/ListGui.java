package com.glocki.springbootmealer.web.gui;

import com.glocki.springbootmealer.domain.model.meal.ListOfIngredients;
import com.glocki.springbootmealer.domain.services.ListOfIngredientsService;
import com.glocki.springbootmealer.web.apis.ShoppingListController;
import com.glocki.springbootmealer.web.gui.components.Menu;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("list")
public class ListGui extends VerticalLayout {

    public ListGui(ListOfIngredientsService listOfIngredientsService, ShoppingListController shoppingListController) {
        add(Menu.showMenu());

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        List<ListOfIngredients> myList = shoppingListController.showMyLists();

        for (ListOfIngredients list: myList
             ) {
            MultiSelectListBox<String> listBox = new MultiSelectListBox<>();
            listBox.setItems(list.getListOfIngredients());

            Details detailList = new Details(list.getMealName(),
                    listBox);

            Button deleteFromList = new Button("Delete ingredients from list");
            deleteFromList.addClickListener(buttonClickEvent -> {
                //deleting selected ingredients from set
                list.getListOfIngredients().removeAll(listBox.getSelectedItems());
                //setting new list (without deleted ingredents)
                listBox.setItems(list.getListOfIngredients());
                //updating record in db
                listOfIngredientsService.save(list);
                //showing notification to user
                Notification.show("Ingredients deleted from list");
//                UI.getCurrent().getPage().reload();
            });
            Button deleteList = new Button("Delete list");
            deleteList.addClickListener(buttonClickEvent -> {
                //deleting list in db
                listOfIngredientsService.delete(list);
                //removing list from gui
                remove(detailList);
                //showing notification to user
                Notification.show("List deleted");
            });
            detailList.addContent(deleteFromList);
            detailList.addContent(deleteList);
            add(detailList);
        }

    }
}
