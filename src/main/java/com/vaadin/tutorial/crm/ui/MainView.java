package com.vaadin.tutorial.crm.ui;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import com.vaadin.tutorial.crm.employee.ArchivedEmployeeView;
import com.vaadin.tutorial.crm.employee.EmployeeView;
import com.vaadin.tutorial.crm.log.LogView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
@Route("")
@Theme(value = Lumo.class, variant=Lumo.LIGHT)
@PageTitle("Employee Management")
@CssImport("./styles/views/main/main-view.css")
public class MainView extends AppLayout {

    private final Tabs menu;
    private H1 viewTitle;
    Image avatar;

    Image logo;



    public MainView( ) {
        avatar=  new Image();
        logo= new Image();
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        menu = createMenu();




        addToDrawer(createDrawerContent(menu));

    }

    private Component createHeaderContent() {
        H1 userTitle;
        HorizontalLayout layout = new HorizontalLayout();
        layout.setId("header");
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(new DrawerToggle());
        viewTitle = new H1();
        userTitle= new H1(" ");
        layout.add(viewTitle);
        userTitle.getStyle().set("margin-right", "10px");

        avatar.setSrc("https://biosec.com.ng/img/BIOSEC%20Solutions%20Logo-02.png");



        layout.add(avatar, userTitle);

        ContextMenu contextMenu = new ContextMenu(userTitle);
        contextMenu.setOpenOnClick(true);
        contextMenu.addItem("Settings",
                e -> getUI().ifPresent(ui -> ui.navigate("setting/profile")));
        Anchor li= new Anchor("/logout", "Logout");
        li.setClassName("centered-content");
        contextMenu.add(li);

        return layout;
    }

    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();

        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);
        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setId("logo");

        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        logo.setSrc("https://biosec.com.ng/img/BIOSEC_Solutions_namedlogo.png");


        logoLayout.add(logo);


        layout.add(logoLayout, menu);
        return layout;
    }

    private Tabs createMenu() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.setId("tabs");
        tabs.add(createMenuItems());

        return tabs;
    }

    private Component[] createMenuItems() {




        RouterLink employeeLink = new RouterLink("Employee", EmployeeView.class);
        RouterLink archemployeeLink = new RouterLink("Archived Employee", ArchivedEmployeeView.class);

        RouterLink logLink = new RouterLink("Logs", LogView.class);






        RouterLink[] links = new RouterLink[] {

                employeeLink,
                archemployeeLink,
                logLink




        };




        return Arrays.stream(links).map(MainView::createTab).toArray(Tab[]::new);

    }

    private static Tab createTab(Component content) {
        final Tab tab = new Tab();
        tab.add(content);
        return tab;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        updateChrome();
    }

    private void updateChrome() {
        getTabWithCurrentRoute().ifPresent(menu::setSelectedTab);
        viewTitle.setText(getCurrentPageTitle());
    }

    private Optional<Tab> getTabWithCurrentRoute() {
        String currentRoute = RouteConfiguration.forSessionScope()
                .getUrl(getContent().getClass());
        return menu.getChildren().filter(tab -> hasLink(tab, currentRoute))
                .findFirst().map(Tab.class::cast);
    }

    private boolean hasLink(Component tab, String currentRoute) {
        return tab.getChildren().filter(RouterLink.class::isInstance)
                .map(RouterLink.class::cast).map(RouterLink::getHref)
                .anyMatch(currentRoute::equals);
    }

    private String getCurrentPageTitle() {
        return getContent().getClass().getAnnotation(PageTitle.class).value();
    }





}
