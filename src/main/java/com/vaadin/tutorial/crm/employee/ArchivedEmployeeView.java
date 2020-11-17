package com.vaadin.tutorial.crm.employee;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.entity.Employee;
import com.vaadin.tutorial.crm.backend.service.EmployeeService;
import com.vaadin.tutorial.crm.ui.MainView;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "ArchivedEmployee", layout = MainView.class)
@PageTitle("Archived Employee Management")
@CssImport("./styles/shared-styles.css")
public class ArchivedEmployeeView extends Div {


    EmployeeService employeeService;

    private TextField filterText = new TextField();


    Grid<Employee> employeeview= new Grid<>(Employee.class);
    public ArchivedEmployeeView(  @Autowired EmployeeService employeeService) {
        this.employeeService= employeeService;
        setId("about-view");

        addClassName("list-view");
        setSizeFull();

        configureGrid();

        Div content = new Div(employeeview);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolbar(), content);
        updateList();
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Search Employee ...");
        Icon icon = VaadinIcon.SEARCH.create();
        filterText.setPrefixComponent(icon);
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());



        HorizontalLayout toolbar = new HorizontalLayout(filterText);
        toolbar.addClassName("toolbar");
        toolbar.getStyle().set("margin-left", "10px");
        toolbar.getStyle().set("margin-top", "10px");
        return toolbar;
    }


    private void configureGrid() {
        employeeview.addClassName("customer-grid");
        employeeview.setSizeFull();


        employeeview.getColumns().forEach(col -> col.setAutoWidth(true));

    }

    private void updateList() {

        employeeview.setItems(employeeService.getAllArchivedEmployee());

    }










}