package com.glocki.springbootmealer.web.gui.components;

import com.glocki.springbootmealer.utils.Utils;
import com.glocki.springbootmealer.web.gui.ListGui;
import com.glocki.springbootmealer.web.gui.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

public class Menu extends VerticalLayout{

    public static MenuBar showMenu(){
        MenuBar barmenu = new MenuBar();
        Button button = new Button("Vaadin button");
        barmenu.addItem(new RouterLink("Home", MainView.class));
        if (Utils.isLogged())
            barmenu.addItem(new RouterLink("My List", ListGui.class));
        if (Utils.isLogged())
            barmenu.addItem(new Anchor("/logout", "Logout"));
        else
            barmenu.addItem(new Anchor("/login", "Login"));

        return barmenu;
    }
}
