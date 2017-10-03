package ru.sbertech.datahub.ui.view.IdleTestStandProblem;


import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.PieChartConfig;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.PieDataset;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.utils.ColorUtils;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sbertech.datahub.ui.rest.IdleRestClient;
import ru.sbertech.datahub.webservice.model.metric.idle.IdleTestStandProblem;
import ru.sbertech.datahub.webservice.model.metric.idle.IdleTestStandProblemChartData;
import ru.sbertech.datahub.webservice.type.IdleTestStandProblemChartTypeEnum;
import ru.sbertech.datahub.webservice.type.RestErrorMessage;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Theme("valo")
@SpringUI(path = "/idles")
public final class IdlesTestStandProblemView extends VerticalLayout implements View, IdlesTestStandProblemEdit.IdlesTestStandProblemEditListener {
    public static final String VIEW_NAME = "IdlesTestStandProblemView";

    public static final String EDIT_ID = "IdleTestStandProblem-edit";
    public static final String TITLE_ID = "IdleTestStandProblem-title";

    private final Grid<IdleTestStandProblem> grid;
    private final String releaseCode;

    List<IdleTestStandProblem> list = new ArrayList<>();

    @Autowired
    IdleRestClient idleRestClient = new IdleRestClient();

    public IdlesTestStandProblemView() {
        this("pir27");
    }

    private void genData() {
      list.add(new IdleTestStandProblem(null, null, "РБ", "РБ", null, "ufs", "ЕФС", null, "Card", null, "123456", "Карты-1", "Карты-1", null, "pir27", "Пир 27", null, null, "ufs", "ЕФС", null, "identifiction", "МИ", null, "ift", "ИФТ", "Да черт его знает почему упало", null, null, "1 час 10 мин", null, "Упало что то", "Конь в пальто", "a"));
        list.add(new IdleTestStandProblem(null, null, "РБ", "РБ", null, "ufs", "ЕФС", null, "Card", null, "123456", "ПИП.ВИП", "ПИП.ВИП", null, "pir27", "Пир 27", null, null, "ufs", "ЕФС", null, "pipvip", "ПИП.ВИП", null, "ift", "ИФТ", "Да черт его знает почему упало", null, null, "1 час 10 мин", null, "Упало что то", "Конь в пальто", "a"));
    }

    public IdlesTestStandProblemView(String releaseCode) {
        this.releaseCode = releaseCode;

        setSizeFull();
        setMargin(false);
        setSpacing(true);

        addComponent(buildTitle());
        addComponent(buildCharts());
        addComponent(buildAddButton());
        grid = buildGrid();
        addComponent(grid);
        setExpandRatio(grid, 1);

        genData();
        grid.setItems(list);
    }

    private Component buildTitle() {
        HorizontalLayout header = new HorizontalLayout();

        Label title = new Label("<h2>Простои стендов в <b>ПИР 27</b></h2>");
        title.setContentMode(ContentMode.HTML);
        title.setSizeUndefined();
        title.addStyleName(ValoTheme.LABEL_H1);
        title.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        header.addComponent(title);

        return header;
    }

    private ChartJs buildChart(List<IdleTestStandProblemChartData> list, String DatasetName, String chartName) {
        PieChartConfig config = new PieChartConfig();

        List<String> names = new ArrayList<>();
        list.stream().forEach(idleTestStandProblemChartData -> names.add(idleTestStandProblemChartData.getName()));

        config.data().labelsAsList(names).addDataset(new PieDataset().label(DatasetName)).and();
        config.options().responsive(true).title().display(true).text(chartName).and().animation().animateScale(true).animateRotate(true).and().legend().position(Position.RIGHT).and().done();

        List<String> labels = config.data().getLabels();
        for (Dataset<?, ?> ds : config.data().getDatasets()) {
            PieDataset lds = (PieDataset) ds;
            List<Double> data = new ArrayList<>();
            List<String> colors = new ArrayList<>();
            for (IdleTestStandProblemChartData item : list) {
                data.add(item.getCountItem().doubleValue());
                colors.add(ColorUtils.randomColor(0.7));
            }
            lds.backgroundColor(colors.toArray(new String[colors.size()]));
            lds.dataAsList(data);
        }

        ChartJs chart = new ChartJs(config);
        //chart.setHeight(200.0f, Unit.PIXELS);
        chart.setJsLoggingEnabled(true);
        chart.setStyleName("outlined");

        return chart;
    }

    private Component buildCharts() {
        HorizontalLayout layout = new HorizontalLayout();
        //layout.setHeight(20.0f, Unit.PERCENTAGE);
        layout.setStyleName("outlined");
//        layout.setSizeFull();

//        layout.setMargin(true);

        List<IdleTestStandProblemChartData> week = idleRestClient.getIdleChartData(IdleTestStandProblemChartTypeEnum.WEEK.getChartTypeName(), "pir27");
        List<IdleTestStandProblemChartData> month = idleRestClient.getIdleChartData(IdleTestStandProblemChartTypeEnum.MONTH.getChartTypeName(), "pir27");
        List<IdleTestStandProblemChartData> release = idleRestClient.getIdleChartData(IdleTestStandProblemChartTypeEnum.RELEASE.getChartTypeName(), "pir27");

        layout.addComponent(buildChart(week, "weekDataset", "за неделю"));
        layout.addComponent(buildChart(month, "monthDataset", "за месяц"));
        layout.addComponent(buildChart(release, "releaseDataset", "за релиз"));

        return layout;
    }

    private Component buildAddButton() {
        Button btn = new Button("Добавить", VaadinIcons.INSERT);
        btn.setWidth(150.0f, Unit.PIXELS);
        btn.setHeight(30.0f, Unit.PIXELS);
        btn.setDescription("Добавление нового элемента");
        btn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getUI().addWindow(new IdlesTestStandProblemEdit(IdlesTestStandProblemView.this, "pit27", null));
            }
        });

        return btn;
    }

    private Grid<IdleTestStandProblem> buildGrid() {
        Grid<IdleTestStandProblem> grid = new Grid<>();
        grid.setSizeFull();
//        grid.setWidth(100.0f, Unit.PERCENTAGE);
//        grid.setHeight(70.0f, Unit.PERCENTAGE);
        grid.setStyleName("outlined");

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addColumn(idle -> "Редактировать", new ButtonRenderer<>(clickEvent -> getUI().addWindow(new IdlesTestStandProblemEdit(this::formSuccess, "pir27", clickEvent.getItem().getId())))).setSortable(false).setWidth(130.0f).setResizable(false);
        grid.addColumn(IdleTestStandProblem::getId).setId("Id").setHidden(true).setWidth(0);
        grid.addColumn(IdleTestStandProblem::getIdRelease).setId("IdRelease").setHidden(true).setWidth(0);
        grid.addColumn(IdleTestStandProblem::getReleaseCode).setId("ReleaseCode").setHidden(true).setWidth(0);
        grid.addColumn(IdleTestStandProblem::getReleaseName).setId("ReleaseName").setCaption("Релиз");

        grid.addColumn(IdleTestStandProblem::getBlocShortName).setId("BlocShortName").setCaption("Блок");

        grid.addColumn(IdleTestStandProblem::getIdProject).setId("IdProject").setHidden(true).setWidth(0);
        grid.addColumn(IdleTestStandProblem::getProjectCode).setId("ProjectCode").setHidden(true).setWidth(0);
        grid.addColumn(IdleTestStandProblem::getProjectName).setId("ProjectName").setCaption("Релиз");

        grid.addColumn(IdleTestStandProblem::getIdAsFs).setId("idAsFs").setHidden(true).setWidth(0);

        grid.addColumn(IdleTestStandProblem::getIdAutomatedSystem).setId("IdAutomatedSystem").setHidden(true).setWidth(0);
        grid.addColumn(IdleTestStandProblem::getAutomatedSystemCode).setId("AutomatedSystemCode").setHidden(true).setWidth(0);
        grid.addColumn(IdleTestStandProblem::getAutomatedSystemName).setId("AutomatedSystemName").setCaption("АС");

        grid.addColumn(IdleTestStandProblem::getIdFunctionalSubsystem).setId("IdFunctionalSubsystem").setHidden(true).setWidth(0);
        grid.addColumn(IdleTestStandProblem::getFunctionalSubsystemCode).setId("FunctionalSubsystemCode").setHidden(true).setWidth(0);
        grid.addColumn(IdleTestStandProblem::getFunctionalSubsystemName).setId("FunctionalSubsystemName").setCaption("ФП");

        grid.addColumn(IdleTestStandProblem::getIdIdleTestProblemType).setId("idIdleTestProblemType").setHidden(true).setWidth(0);
        grid.addColumn(IdleTestStandProblem::getIdleTestProblemTypeName).setId("idleTestProblemTypeName").setCaption("Тип проблемы");

        grid.addColumn(IdleTestStandProblem::getIdTestStageType).setId("idTestStageType").setHidden(true).setWidth(0);
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
                RestErrorMessage res = idleRestClient.delIdle(clickEvent.getItem().getId());

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
        })).setSortable(false).setWidth(90.0f).setResizable(false);
        return grid;
    }

    @Override
    public void formSuccess() {
//
//        grid.setItems();
    }
}
