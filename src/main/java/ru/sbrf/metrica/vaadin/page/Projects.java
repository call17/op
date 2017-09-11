package ru.sbrf.metrica.vaadin.page;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.Editor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sbrf.metrica.vaadin.model.Project;
import ru.sbrf.metrica.vaadin.rest.ProjectRestClient;

import java.util.List;

@Theme("valo")
@SpringUI(path = "/projects")
public class Projects extends UI {
    @Autowired
    ProjectRestClient projectRestClient;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout content = new VerticalLayout();
        content.setWidth(100, Unit.PERCENTAGE);
        content.setHeight(100, Unit.PERCENTAGE);

        List<Project> projects = projectRestClient.getProjects();

        Grid<Project> grid = new Grid<>();
        grid.setItems(projects);
        grid.addColumn(Project::getCode).setCaption("Код проекта").setEditorComponent(new TextField(), Project::setCode).setEditable(true);
        grid.addColumn(Project::getName).setCaption("Название проекта ").setEditorComponent(new TextField(), Project::setName).setEditable(true);
        grid.addColumn(Project::getAltName).setCaption("Альтернативное название").setEditorComponent(new TextField(), Project::setAltName).setEditable(true);
        grid.setWidth(100, Unit.PERCENTAGE);
        grid.setHeight(100, Unit.PERCENTAGE);

        Editor<Project> editor = grid.getEditor();
        editor.setEnabled(true);
        editor.addSaveListener((editorSaveEvent) -> projectRestClient.saveProject(editorSaveEvent.getBean()));
        editor.setSaveCaption("Сохранить");
        editor.setCancelCaption("Отмена");

        content.addComponent(grid);
        setContent(content);
    }
}
