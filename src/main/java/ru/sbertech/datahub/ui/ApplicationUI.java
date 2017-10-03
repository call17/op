package ru.sbertech.datahub.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import ru.sbertech.datahub.ui.view.IdleTestStandProblem.IdlesTestStandProblemView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@Theme("valo")
@SpringUI
public class ApplicationUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(new IdlesTestStandProblemView());
    }

    @WebServlet(urlPatterns = {"/op_ufs_ui/*", "/VAADIN/*"}, name = "DatahubUI", displayName = "op_ufs_ui", asyncSupported = true)
    @VaadinServletConfiguration(ui = ApplicationUI.class, productionMode = false)
    public static class ApplicationServlet extends VaadinServlet {

        @Override
        protected final void servletInitialized() throws ServletException {
            super.servletInitialized();
            getService().addSessionInitListener(new ApplicationInitListener());
        }

    }
}
