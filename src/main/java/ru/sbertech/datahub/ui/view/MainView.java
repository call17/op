package ru.sbertech.datahub.ui.view;


import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.ui.*;
import ru.sbertech.datahub.ui.PortalNavigator;


@Theme("portal")
@Widgetset("com.vaadin.demo.dashboard.DashboardWidgetSet")
@Title("Опер")
public class MainView extends HorizontalLayout {


    public MainView() {
        setSizeFull();
        addStyleName("mainview");
        setSpacing(false);

        addComponent(new PortalMenu());

        ComponentContainer content = new CssLayout();
        content.addStyleName("view-content");
        content.setSizeFull();
        addComponent(content);
        setExpandRatio(content, 1.0f);

        new PortalNavigator(content);
    }
}
