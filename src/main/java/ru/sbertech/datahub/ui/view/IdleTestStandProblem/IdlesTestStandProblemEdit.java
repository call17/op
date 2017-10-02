package ru.sbertech.datahub.ui.view.IdleTestStandProblem;


import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

import ru.sbertech.datahub.webservice.model.sbrf.Project;

import java.math.BigInteger;

@SuppressWarnings({"serial", "unchecked"})
public class IdlesTestStandProblemEdit extends Window {

    private final IdlesTestStandProblemEditListener listener;
    private boolean addEdit;

    private ComboBox<Project> project = null;

    public IdlesTestStandProblemEdit(IdlesTestStandProblemEditListener listener, final BigInteger idRow) {
        this.listener = listener;
        this.addEdit = (idRow == null);

        setCaption(addEdit ? "Добавление простоя" : "Редактирование простоя");
        setModal(true);
        setClosable(false);
        setResizable(false);
        setWidth(600.0f, Unit.PIXELS);

        buildContent(idRow);
    }

    private Component buildContent(BigInteger idRow) {
        VerticalLayout result = new VerticalLayout();

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
                //listener.dashboardNameEdited(nameField.getValue());
                close();
            }
        });
        save.setClickShortcut(KeyCode.ENTER, null);

        footer.addComponents(cancel, save);
        footer.setExpandRatio(cancel, 1);
        footer.setComponentAlignment(cancel, Alignment.TOP_RIGHT);
        return footer;
    }

    public interface IdlesTestStandProblemEditListener {
        void titleChanged(String newTitle);

    }
}
