package ru.sbertech.datahub.ui.view.classifier;


import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Theme("portal")
@SpringUI(path = "/classifiers")
public class ClassifierView  extends VerticalLayout implements View {

    public ClassifierView() {

        setSizeFull();
        setMargin(false);
        setSpacing(true);

        addComponent(buildTitle());

    }

    private Component buildTitle() {
        HorizontalLayout header = new HorizontalLayout();

        Label title = new Label("<h2>Страница в разработке</h2>");
        title.setContentMode(ContentMode.HTML);
        title.setSizeUndefined();
        title.addStyleName(ValoTheme.LABEL_H1);
        title.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        header.addComponent(title);

        return header;
    }
}
