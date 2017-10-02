package ru.sbertech.datahub.ui.page;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.Editor;
import com.vaadin.ui.renderers.ButtonRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sbertech.datahub.ui.model.Idle;
import ru.sbertech.datahub.ui.rest.IdleRestClient;
import ru.sbertech.datahub.ui.model.Project;
import ru.sbertech.datahub.ui.rest.ProjectRestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Theme("valo")
@SpringUI(path = "/idles")
public class Idles extends UI{
    
    @Autowired
    IdleRestClient idleRestClient;
    @Autowired
    ProjectRestClient projectRestClient;
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout content = new VerticalLayout();
        content.setWidth(100, Unit.PERCENTAGE);
        content.setHeight(100, Unit.PERCENTAGE);

        List<Idle> idles = idleRestClient.getIdles();
        List<Project> projects = projectRestClient.getProjects();
        List<String> projectsCodes = new ArrayList<>(projects.size());
        Map<String, Project> codesToProjects = new HashMap<>();
        projects.forEach((project) ->  {
            projectsCodes.add(project.getCode());
            codesToProjects.put(project.getCode(), project);
        });

        Grid<Idle> grid = new Grid<>();
        grid.setItems(idles);
        grid.addColumn(Idle::getId).setCaption("п/п");
        ComboBox<String> projectsCodesBox = new ComboBox<>();
        projectsCodesBox.setItems(projectsCodes);
        grid.addColumn(Idle::getProjectNumber).setCaption("Номер проекта").setEditorComponent(projectsCodesBox, Idle::setProjectNumber).setEditable(true);
        grid.addColumn(Idle::getProjectName).setCaption("Наименование проекта ");
        grid.addColumn(Idle::getBlockName).setCaption("Блок");
        grid.addColumn(Idle::getReleaseName).setCaption("Номер ПИР").setEditorComponent(new ComboBox<>(), Idle::setReleaseName).setEditable(true);
        grid.addColumn(Idle::getStageName).setCaption("Этап тестирования").setEditorComponent(new ComboBox<>(), Idle::setStageName).setEditable(true);
        grid.addColumn(Idle::getStartDate).setCaption("Начало простоя").setEditorComponent(new DateField(), Idle::setStartDate).setEditable(true);
        grid.addColumn(Idle::getEndDate).setCaption("Завершение простоя").setEditorComponent(new DateField(), Idle::setEndDate).setEditable(true);
        grid.addColumn(Idle::getEstimate).setCaption("Расчетное время простоя");
        grid.addColumn(Idle::getProblemTypeName).setCaption("Классификация проблемы").setEditorComponent(new ComboBox<>(), Idle::setProblemTypeName).setEditable(true);
        grid.addColumn(Idle::getAutomationSystemName).setCaption("АС").setEditorComponent(new ComboBox<>(), Idle::setAutomationSystemName).setEditable(true);
        grid.addColumn(Idle::getFunctionalSubsystemName).setCaption("ФП").setEditorComponent(new ComboBox<>(), Idle::setFunctionalSubsystemName).setEditable(true);
        grid.addColumn(Idle::getDescription).setCaption("Описание").setEditorComponent(new TextField(), Idle::setDescription).setEditable(true);
        grid.addColumn(Idle::getResponsible).setCaption("Ответственный").setEditorComponent(new TextField(), Idle::setResponsible).setEditable(true);
        grid.addColumn(idle -> "Удалить", new ButtonRenderer<>(clickEvent -> {
            Window popUp = new Window("Удаление");

            VerticalLayout popUpContent = new VerticalLayout();
            popUpContent.addComponent(new Label("Вы уверены что хотите удалить выбранную запись?"));
            Button yesButton = new Button("Да");
            Button noButton = new Button("Нет");
            noButton.addClickListener(event -> popUp.setVisible(false));
            yesButton.addClickListener(event -> {
                popUp.setVisible(false);
                idles.remove(clickEvent.getItem());
                grid.setItems(idles);
            });
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.addComponents(yesButton, noButton);
            popUpContent.addComponent(horizontalLayout);
            popUp.setContent(popUpContent);
            popUp.setWidth(400, Unit.PIXELS);
            popUp.setHeight(200, Unit.PIXELS);
            popUp.center();
            addWindow(popUp);
        }));

        grid.setWidth(100, Unit.PERCENTAGE);
        grid.setHeight(95, Unit.PERCENTAGE);

        Editor<Idle> editor = grid.getEditor();
        editor.setEnabled(true);
        editor.addSaveListener((editorSaveEvent) -> idleRestClient.saveIdle(editorSaveEvent.getBean()));
        editor.setSaveCaption("Сохранить");
        editor.setCancelCaption("Отмена");

        Button addButton = new Button("Добавить");
        addButton.addClickListener((event) -> {
            idles.add(new Idle());
            grid.setItems(idles);
        });

        content.addComponent(grid);
        content.addComponent(addButton);
        content.setExpandRatio(grid, 0.95f);
        content.setExpandRatio(addButton, 0.05f);
        setContent(content);
    }
}
