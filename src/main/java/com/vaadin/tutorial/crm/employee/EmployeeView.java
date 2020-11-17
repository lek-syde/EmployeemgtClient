package com.vaadin.tutorial.crm.employee;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.entity.Employee;
import com.vaadin.tutorial.crm.backend.service.EmployeeService;
import com.vaadin.tutorial.crm.ui.MainView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "Employee", layout = MainView.class)
@PageTitle("Employee Management")
@CssImport("./styles/shared-styles.css")
public class EmployeeView extends Div {


    EmployeeService employeeService;

    EmployeeForm form;

    private TextField filterText = new TextField();


    Grid<Employee> employeeview= new Grid<>(Employee.class);
    public EmployeeView(  @Autowired EmployeeService employeeService) {
        this.employeeService= employeeService;
        setId("about-view");

        addClassName("list-view");
        setSizeFull();

        configureGrid();


        form = new EmployeeForm(employeeService);
        form.addListener(EmployeeForm.SaveEvent.class, this::saveEmployee);
        form.addListener(EmployeeForm.DeleteEvent.class, this::deleteEmployee);
        form.addListener(EmployeeForm.CloseEvent.class, e -> closeEditor());


        Div content = new Div(employeeview, form);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolbar(), content);
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Search Employee ...");
        Icon icon = VaadinIcon.SEARCH.create();
        filterText.setPrefixComponent(icon);
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addEmployeeButton = new Button("New Employee");


        addEmployeeButton.addClickListener(click -> addEmployee());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addEmployeeButton);
        toolbar.addClassName("toolbar");
        toolbar.getStyle().set("margin-left", "10px");
        toolbar.getStyle().set("margin-top", "10px");
        return toolbar;
    }


    private void configureGrid() {
        employeeview.addClassName("customer-grid");
        employeeview.setSizeFull();

        employeeview.addSelectionListener(e -> closeEditor());


        employeeview.getColumns().forEach(col -> col.setAutoWidth(true));
        employeeview.asSingleSelect().addValueChangeListener(event ->
                editEmployee(event.getValue()));

    }

    private void updateList() {
        List<Employee> mainList= employeeService.findAll(filterText.getValue());
        if(mainList!=null){
            employeeview.setItems(mainList);
            showSuccessNotification("Data loaded successfully");
        }
        else{

            showErrorNotification("Error: Data Cannot be Fetched. contact Administrator");

        }




    }

    private void saveEmployee(EmployeeForm.SaveEvent event) {
      employeeService.savemployee(event.getEmployee());
        System.out.println("save");
        updateList();
        closeEditor();
        showSuccessNotification("Data updated successfully");
    }

    private  void deleteEmployee(EmployeeForm.DeleteEvent event) {

        System.out.println("delete");
       Employee modifiedEmployee= event.getEmployee();
        modifiedEmployee.setIsarchived(true);
        employeeService.savemployee(modifiedEmployee);
        updateList();
        closeEditor();
        showSuccessNotification("Data deleted successfully");
    }

    private void closeEditor() {
        form.setEmployee(null);
        form.setVisible(false);
        removeClassName("editing");
    }


    private void addEmployee() {
        System.out.println("hi");
        employeeview.asSingleSelect().clear();
        editEmployee(new Employee());
    }

    private void editEmployee(Employee employee) {
        if (employee == null) {
            closeEditor();
        } else {
            form.setEmployee(employee);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    public void excludeColumns() {

    }

    public void showSuccessNotification(String message){
        Notification notification = Notification.show(message, 3000, Notification.Position.BOTTOM_END);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

    }

    public void showErrorNotification(String message){
        Notification notification = Notification.show(message, 3000, Notification.Position.BOTTOM_END);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

    }

}
