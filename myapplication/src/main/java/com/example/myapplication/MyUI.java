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
import com.vaadin.ui.HorizontalLayout;
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
	private CustomerForm customerForm = new CustomerForm(this);
	
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
    	updateCustomers();
    	
        grid.setColumns("firstName","lastName","email","birthDate");
        
        filterCustomerListTextField.setPlaceholder("filter by name...");
        filterCustomerListTextField.addValueChangeListener(e->updateCustomers());
        filterCustomerListTextField.setValueChangeMode(ValueChangeMode.LAZY);
        
        Button clearFilterCustomerListButton = new Button(VaadinIcons.CLOSE);
        clearFilterCustomerListButton.setDescription("Clear the filter text");
        clearFilterCustomerListButton.addClickListener(e->filterCustomerListTextField.clear());
        
        Button addCustomerButton = new Button("Adde New Customer");
        addCustomerButton.setDescription("Add a new customer in the list of customers below");
        addCustomerButton.addClickListener(event->{
        	grid.asSingleSelect().clear();
        	customerForm.setCustomer(new Customer());
        });
        
        
        
        
        CssLayout filteringLayout = new CssLayout();
        filteringLayout.addComponents(filterCustomerListTextField,clearFilterCustomerListButton);
        filteringLayout.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        
        HorizontalLayout toolbarLayout = new HorizontalLayout();
        toolbarLayout.addComponents(filteringLayout,addCustomerButton);
        toolbarLayout.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        
        
        HorizontalLayout mainLayout = new HorizontalLayout(grid,customerForm);
    	mainLayout.setSizeFull();
    	grid.setSizeFull();
    	mainLayout.setExpandRatio(grid, 2);
    	mainLayout.setExpandRatio(customerForm, 1);
    	
        final VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponents(toolbarLayout,mainLayout);
        
        setContent(verticalLayout);
        
        customerForm.setVisible(false);
        grid.asSingleSelect().addValueChangeListener(event->
        {
        	Customer c = event.getValue();
        	if (c==null) 
        	{
        		customerForm.setVisible(false);
        	}
        	else {
        		customerForm.setCustomer(c);
        		customerForm.setVisible(true);
        	}
        	
        });
        
        
    }

	public void updateCustomers() {
		List<Customer> customers = customerService.findAll(filterCustomerListTextField.getValue());	
        grid.setItems(customers);
	}

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
