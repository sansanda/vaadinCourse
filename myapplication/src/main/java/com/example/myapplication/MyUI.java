package com.example.myapplication;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	private CustomerService customerService = CustomerService.getInstance();
	private Grid<Customer> grid = new Grid<Customer>(Customer.class);
	private TextField filterCustomerListTextField = new TextField();
	
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
    	updateCustomers();
    	
    	final VerticalLayout verticalLayout = new VerticalLayout();
        grid.setColumns("firstName","lastName","email","birthDate");
        
        filterCustomerListTextField.setPlaceholder("filter by name...");
        filterCustomerListTextField.addValueChangeListener(e->updateCustomers());
        filterCustomerListTextField.setValueChangeMode(ValueChangeMode.LAZY);
        
        Button clearFilterCustomerListTextField = new Button(VaadinIcons.CLOSE);
        clearFilterCustomerListTextField.setDescription("Clear the filter text");
        clearFilterCustomerListTextField.addClickListener(e->filterCustomerListTextField.clear());
        
        CssLayout filteringLayout = new CssLayout();
        filteringLayout.addComponents(filterCustomerListTextField,clearFilterCustomerListTextField);
        filteringLayout.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        
        
        
        verticalLayout.addComponents(filteringLayout,grid);
        
        setContent(verticalLayout);
    }

	private void updateCustomers() {
		List<Customer> customers = customerService.findAll(filterCustomerListTextField.getValue());	
        grid.setItems(customers);
	}

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
