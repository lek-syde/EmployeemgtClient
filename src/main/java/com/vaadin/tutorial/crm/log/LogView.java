package com.vaadin.tutorial.crm.log;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.entity.Employee;
import com.vaadin.tutorial.crm.backend.entity.LogHistory;
import com.vaadin.tutorial.crm.backend.service.LogService;
import com.vaadin.tutorial.crm.employee.EmployeeForm;
import com.vaadin.tutorial.crm.ui.MainView;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "Log", layout = MainView.class)
@PageTitle("Logs")
@CssImport("./styles/shared-styles.css")
public class LogView  extends Div  {


    LogService logService;

    LogForm form;

    Grid<LogHistory> logView = new Grid<>(LogHistory.class);


    public LogView(@Autowired LogService logService) {
        this.logService= logService;
        setId("about-view");

        addClassName("list-view");
        setSizeFull();

        configureGrid();


        form = new LogForm();

        Div content = new Div(logView, form);
        content.addClassName("content");
        content.setSizeFull();

        add(content);
       updateList();
        closeEditor();
    }

    private void configureGrid() {
        logView.setSizeFull();
        logView.addClassName("customer-grid");
        logView.getColumns().forEach(col -> col.setAutoWidth(true));


        logView.addSelectionListener(e -> closeEditor());


       logView.asSingleSelect().addValueChangeListener(event ->
                showDetails(event.getValue()));


        excludeColumns();

    }

    private void showDetails(LogHistory value) {
        if (value == null) {
            closeEditor();
        } else {
            form.setLogHistory(value);
            form.setVisible(true);
            addClassName("editing");
        }
    }




    private void updateList() {

        logView.setItems(logService.getAllLogs());

    }

    public void excludeColumns() {
        logView.removeColumnByKey("employee");

    }

    private void closeEditor() {
        form.setLogHistory(null);
        form.setVisible(false);
        removeClassName("editing");
    }


}
