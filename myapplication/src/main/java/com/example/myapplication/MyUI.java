package com.example.myapplication;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

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
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
    	final VerticalLayout layout = new VerticalLayout();
        layout.addComponent(grid);
        grid.setColumns("firstName","lastName","email","birthDate");
        
        updateCustomers();
        
        
        setContent(layout);
    }

	private void updateCustomers() {
		List<Customer> customers = customerService.findAll();	
        grid.setItems(customers);
	}

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
