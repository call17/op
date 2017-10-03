package ru.sbertech.datahub.ui.view.IdleTestStandProblem;


import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

import jdk.nashorn.internal.objects.annotations.Function;
import ru.sbertech.datahub.webservice.model.sbrf.*;
import ru.sbertech.datahub.webservice.model.sbrf.idle.IdleTestProblemType;
import ru.sbertech.datahub.webservice.type.StatusEnum;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"serial", "unchecked"})
public class IdlesTestStandProblemEdit extends Window {

    private final IdlesTestStandProblemEditListener listener;
    private boolean addEdit;
    private String releaseCode;
    private BigInteger idRow;

    private ComboBox<Project> projectComboBox = new ComboBox("Проект:");
    private ComboBox<Release> releaseComboBox = new ComboBox("Релиз:");
    private ComboBox<AutomatedSystem> automatedSystemComboBox = new ComboBox("АС:");
    private ComboBox<FunctionalSubsystem> functionalSubsystemComboBox = new ComboBox("ФП:");
    private ComboBox<TestStageType> testStageTypeComboBox = new ComboBox("Стенд");
    private ComboBox<IdleTestProblemType> idleTestProblemTypeComboBox = new ComboBox("Тип проблемы");

    private List<Release> releaseList = new ArrayList<>();
    private List<AutomatedSystem> asList = new ArrayList<>();
    private List<Project> projectList = new ArrayList<>();
    private List<FunctionalSubsystem> fsList = new ArrayList<>();


    public IdlesTestStandProblemEdit(IdlesTestStandProblemEditListener listener, String releaseCode, BigInteger idRow) {
        this.listener = listener;
        this.addEdit = (idRow == null);
        this.releaseCode = releaseCode;
        this.idRow = idRow;

        setCaption(addEdit ? "Добавление простоя" : "Редактирование простоя");
        setModal(true);
        setClosable(true);
        setResizable(false);
        setWidth(600.0f, Unit.PIXELS);

        genData();

        setContent(buildContent());
    }

    private void genData() {
        releaseList.add(new Release(null, "pir26", "ПИР 26", "ПИР 26", null, null, null, null, null, null, null));
        releaseList.add(new Release(null, "pir27", "ПИР 27", "ПИР 27", null, null, null, null, null, null, null));
        releaseList.add(new Release(null, "pir28", "ПИР 28", "ПИР 28", null, null, null, null, null, null, null));

        asList.add(new AutomatedSystem(null, "efs", "ЕФС", "a", null, null, null, null));
        asList.add(new AutomatedSystem(null, "pprb", "ППРБ", "a", null, null, null, null));

        projectList.add(new Project(null, null, null, null, "123456", "Проект 1", "Проект 1", null, null, null, null, StatusEnum.ACTIVE, null, null, null, null));
        projectList.add(new Project(null, null, null, null, "456789", "Проект 2", "Проект 2", null, null, null, null, StatusEnum.ACTIVE, null, null, null, null));
        projectList.add(new Project(null, null, null, null, "789123", "Проект 3", "Проект 3", null, null, null, null, StatusEnum.ACTIVE, null, null, null, null));
    }

    private ComboBox prepareComboBox(ComboBox comboBox, String emptySelectionCaptioin, String placeholder) {
        comboBox.setWidth(90.0f, Unit.PERCENTAGE);
        comboBox.setEmptySelectionCaption(emptySelectionCaptioin);
        comboBox.setPlaceholder(placeholder);
        comboBox.setEmptySelectionAllowed(false);
        comboBox.setScrollToSelectedItem(true);

        return comboBox;
    }

    private Component buildContent() {
        VerticalLayout result = new VerticalLayout();

        releaseComboBox = prepareComboBox(releaseComboBox, "Выберите релиз", "Не выбран релиз");
        releaseComboBox.setItemCaptionGenerator(Release::getName);

        automatedSystemComboBox = prepareComboBox(automatedSystemComboBox, "Выберите АС", "АС не выбрана");
        automatedSystemComboBox.setItemCaptionGenerator(AutomatedSystem::getName);

        projectComboBox = prepareComboBox(projectComboBox, "Введите код проекта", "Проект не выбран");
        projectComboBox.setItemCaptionGenerator(Project::getCodeName);

        releaseComboBox.setItems(releaseList);
        automatedSystemComboBox.setItems(asList);
        projectComboBox.setItems(projectList);

        result.addComponent(releaseComboBox);
        result.addComponent(automatedSystemComboBox);
        result.addComponent(projectComboBox);
        result.addComponent(buildFooter(addEdit));
        return result;
    }

    private Component buildFooter(boolean addEdit) {
        HorizontalLayout footer = new HorizontalLayout();
        footer.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
        footer.setWidth(100.0f, Unit.PERCENTAGE);

        Button cancel = new Button("Отмена");
        cancel.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final Button.ClickEvent event) {
                close();
            }
        });
        cancel.setClickShortcut(KeyCode.ESCAPE, null);

        Button save = new Button("Сохранить");
        save.addStyleName(ValoTheme.BUTTON_PRIMARY);
        save.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                // return values
                listener.formSuccess();
                close();
            }
        });
        save.setClickShortcut(KeyCode.ENTER, null);

        footer.addComponents(save, cancel);
        footer.setExpandRatio(save, 1);
        footer.setComponentAlignment(save, Alignment.TOP_RIGHT);

        return footer;
    }

    public interface IdlesTestStandProblemEditListener {
        void formSuccess();

    }
}
