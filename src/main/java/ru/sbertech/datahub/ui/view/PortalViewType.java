package ru.sbertech.datahub.ui.view;


import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import ru.sbertech.datahub.ui.view.IdleTestStandProblem.IdlesTestStandProblemView;
import ru.sbertech.datahub.ui.view.classifier.ClassifierView;

public enum PortalViewType {
    IDLES("Простои", IdlesTestStandProblemView.class, FontAwesome.FILE_TEXT_O, true),
    CLASSIFIERS("Справочники", ClassifierView.class,FontAwesome.HOME,true);


    private final String viewName;
    private final Class<? extends View> viewClass;
    private final Resource icon;
    private final boolean stateful;

    private PortalViewType(final String viewName,
                           final Class<? extends View> viewClass, final Resource icon,
                           final boolean stateful) {
        this.viewName = viewName;
        this.viewClass = viewClass;
        this.icon = icon;
        this.stateful = stateful;
    }

    public boolean isStateful() {
        return stateful;
    }

    public String getViewName() {
        return viewName;
    }

    public Class<? extends View> getViewClass() {
        return viewClass;
    }

    public Resource getIcon() {
        return icon;
    }

    public static PortalViewType getByViewName(final String viewName) {
        PortalViewType result = null;
        for (PortalViewType viewType : values()) {
            if (viewType.getViewName().equals(viewName)) {
                result = viewType;
                break;
            }
        }
        return result;
    }

}
