package com.example.myapplication;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class CustomerForm extends FormLayout {
	
	private TextField firstName = new TextField("First name");
	private TextField lastName = new TextField("Last name");
	private TextField email = new TextField("Email");
	private NativeSelect<CustomerStatus> status = new NativeSelect<>("Status");
	private DateField birthDate = new DateField("Birthdate");
	private Button saveCustomerBt = new Button("Save");
	private Button deleteCustomerBt = new Button("Delete");
	
	private CustomerService customerService = CustomerService.getInstance();
	private Customer customer;
	private MyUI myUI;
	private Binder<Customer> binder = new Binder<>(Customer.class);
	
	public CustomerForm(MyUI _myUI) {
		myUI = _myUI;
		
		status.setItems(CustomerStatus.values());
		saveCustomerBt.setStyleName(ValoTheme.BUTTON_PRIMARY);
		saveCustomerBt.setClickShortcut(KeyCode.ENTER);
		
		setSizeUndefined();
		HorizontalLayout buttonsLayout = new HorizontalLayout(saveCustomerBt,deleteCustomerBt);
		addComponents(firstName,lastName,email,birthDate,status,buttonsLayout);
		
		binder.bindInstanceFields(this);
		
		saveCustomerBt.addClickListener(e->saveCustomer());
		deleteCustomerBt.addClickListener(e->deleteCustomer());
		
		
	}
	
	public void setCustomer(Customer c)
	{
		customer = c;
		binder.setBean(c);
		deleteCustomerBt.setVisible(customer.isPersisted());
		this.setVisible(true);
		firstName.selectAll();
	}
	
	private void deleteCustomer() {
		customerService.delete(customer);
		myUI.updateCustomers();
		this.setVisible(false);
	}
	
	private void saveCustomer() {
		customerService.save(customer);
		myUI.updateCustomers();
		this.setVisible(false);
	}
	
	
}
