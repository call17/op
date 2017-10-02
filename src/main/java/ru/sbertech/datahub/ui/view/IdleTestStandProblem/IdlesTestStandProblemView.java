package ru.sbertech.datahub.ui.view.IdleTestStandProblem;


import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.PieChartConfig;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.PieDataset;
import com.byteowls.vaadin.chartjs.utils.ColorUtils;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.coyote.http2.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sbertech.datahub.ui.rest.IdleRestClient;
import ru.sbertech.datahub.webservice.model.metric.idle.IdleTestStandProblem;
import ru.sbertech.datahub.webservice.type.RestErrorMessage;

import java.util.ArrayList;
import java.util.List;

@Theme("valo")
@SpringUI(path = "/idles")
public final class IdlesTestStandProblemView extends Panel implements View, IdlesTestStandProblemEdit.IdlesTestStandProblemEditListener {

    public static final String EDIT_ID = "IdleTestStandProblem-edit";
    public static final String TITLE_ID = "IdleTestStandProblem-title";

    private final VerticalLayout root;

    @Autowired
    IdleRestClient idleRestClient;

    public IdlesTestStandProblemView() {
        addStyleName(ValoTheme.PANEL_BORDERLESS);
        setSizeFull();

        root = new VerticalLayout();
        root.setSizeFull();
//        root.setSpacing(false);
        setContent(root);

        root.addComponents(buildCharts(), buildGrid());
    }

    private Component buildCharts() {
        HorizontalLayout hl = new HorizontalLayout();
        hl.setMargin(true);

        hl.setWidth(30.0f, Unit.PERCENTAGE);
        hl.setHeight(20.0f, Unit.PERCENTAGE);

        PieChartConfig config = new PieChartConfig();
        config
                .data()
                .labels("Red", "Green", "Yellow", "Grey", "Dark Grey")
                .addDataset(new PieDataset().label("Dataset 1"))
                .and();

        config.
                options()
                .responsive(true)
                .title()
                .display(true)
                .text("Example 1")
                .and()
                .animation()
                .animateScale(true)
                .animateRotate(true)
                .and()
                .done();

        List<String> labels = config.data().getLabels();
        for (Dataset<?, ?> ds : config.data().getDatasets()) {
            PieDataset lds = (PieDataset) ds;
            List<Double> data = new ArrayList<>();
            List<String> colors = new ArrayList<>();
            for (int i = 0; i < labels.size(); i++) {
                data.add((double) (Math.round(Math.random() * 100)));
                colors.add(ColorUtils.randomColor(0.7));
            }
            lds.backgroundColor(colors.toArray(new String[colors.size()]));
            lds.dataAsList(data);
        }

        ChartJs chart = new ChartJs(config);
        chart.setJsLoggingEnabled(true);
//        chart.addClickListener((a,b) ->
//                DemoUtils.notification(a, b, config.data().getDatasets().get(a)));

        hl.addComponent(chart);

        PieChartConfig config2 = new PieChartConfig();
        config2
                .data()
                .labels("Red", "Green", "Yellow", "Grey", "Dark Grey")
                .addDataset(new PieDataset().label("Dataset 1"))
                .and();

        config2.
                options()
                .responsive(true)
                .title()
                .display(true)
                .text("Example 2")
                .and()
                .animation()
                .animateScale(true)
                .animateRotate(true)
                .and()
                .done();

        List<String> labels2 = config2.data().getLabels();
        for (Dataset<?, ?> ds : config2.data().getDatasets()) {
            PieDataset lds = (PieDataset) ds;
            List<Double> data = new ArrayList<>();
            List<String> colors = new ArrayList<>();
            for (int i = 0; i < labels.size(); i++) {
                data.add((double) (Math.round(Math.random() * 100)));
                colors.add(ColorUtils.randomColor(0.7));
            }
            lds.backgroundColor(colors.toArray(new String[colors.size()]));
            lds.dataAsList(data);
        }

        ChartJs chart2 = new ChartJs(config2);
        chart2.setJsLoggingEnabled(true);
//        chart.addClickListener((a,b) ->
//                DemoUtils.notification(a, b, config.data().getDatasets().get(a)));

        hl.addComponent(chart2);

        return hl;
    }

    private Component buildGrid() {
        VerticalLayout vl = new VerticalLayout();
        vl.setSizeFull();
        Grid<IdleTestStandProblem> grid = new Grid<>();
        grid.setSizeFull();

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addColumn(idle -> "Редактировать", new ButtonRenderer<>(clickEvent -> {
            IdlesTestStandProblemEdit edit = new IdlesTestStandProblemEdit(this::titleChanged, clickEvent.getItem().getId());
        }));

        grid.addColumn(IdleTestStandProblem::getIdIdleTestProblemType).setId("idIdleTestProblemType").setHidden(true);
        grid.addColumn(IdleTestStandProblem::getIdleTestProblemTypeName).setId("idleTestProblemTypeName").setCaption("Тип проблемы");

        grid.addColumn(IdleTestStandProblem::getIdTestStageType).setId("idTestStageType").setHidden(true);
        grid.addColumn(IdleTestStandProblem::getTestStageTypeName).setId("testStageTypeName").setCaption("Этап тестирования");

        grid.addColumn(IdleTestStandProblem::getDateStart).setId("dateStart").setCaption("Дата начала простоя");
        grid.addColumn(IdleTestStandProblem::getDateEnd).setId("dateEnd").setCaption("Дата окончания простоя");
        grid.addColumn(IdleTestStandProblem::getResponsible).setId("responsible").setCaption("Ответственный");

//        grid.addColumn(IdleTestStandProblem::get).setId("").setHidden(true);
//        grid.addColumn(IdleTestStandProblem::get).setId("").setCaption("");

        grid.addColumn(idle -> "Удалить", new ButtonRenderer<>(clickEvent -> {
            Window popUp = new Window("Удаление");

            VerticalLayout popUpContent = new VerticalLayout();
            popUpContent.addComponent(new Label("Вы уверены что хотите удалить выбранную запись?"));
            Button btnYes = new Button("Да");
            Button btnNo = new Button("Нет");
            btnNo.addClickListener(event -> popUp.setVisible(false));
            btnYes.addClickListener((Button.ClickEvent event) -> {
                popUp.setVisible(false);
                RestErrorMessage res = idleRestClient.deleteIdle(clickEvent.getItem().getId());

                if (res.isSuccess()) {
                    grid.setItems(idleRestClient.getIdles());
                } else {
                    //show error message
                    final Window win = new Window("Ошибка");
                    win.setModal(true);
                    win.setClosable(true);
                    win.setHeight(20.0f, Unit.PERCENTAGE);
                    win.setWidth(20.0f, Unit.PERCENTAGE);
                    final FormLayout content = new FormLayout();
                    content.setMargin(true);
                    TextField text = new TextField();
                    text.setSizeFull();
                    text.setReadOnly(true);
                    text.setValue(res.getMessage());
                    content.addComponent(text);
                    win.setContent(content);
                    this.getUI().addWindow(win);
                }

            });
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.addComponents(btnYes, btnNo);
            popUpContent.addComponent(horizontalLayout);
            popUp.setContent(popUpContent);
            popUp.setWidth(400, Unit.PIXELS);
            popUp.setHeight(200, Unit.PIXELS);
            popUp.center();
            this.getUI().addWindow(popUp);
        }));

        vl.addComponent(grid);

        return vl;
    }

    @Override
    public void titleChanged(String newTitle) {
//        this.getUI().getUI().get;
//        grid.setItems();
    }
}
