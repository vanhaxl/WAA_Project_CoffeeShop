package edu.mum.coffee.restclient;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestClientException;

import edu.mum.coffee.domain.Address;
import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;

@SpringBootApplication
public class CoffeeShopRestClientApplication implements CommandLineRunner {

	@Autowired
	private Environment m_env;
	
	Product m_product1;
	Product m_product2;
	Person m_person1;
	Person m_person2;
	
	public static void main(String[] args) {
		SpringApplication.run(CoffeeShopRestClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String baseUrl = m_env.getProperty("myapp.rest.url");
		try {
			testProduct(baseUrl);
			testPerson(baseUrl);
			testOrder(baseUrl);
		} catch (RestClientException ex) {	
			ex.printStackTrace();
			System.out.println(ex);
		}
		
	}
	private void testPerson(String baseUrl) {
		RestClient<Person> personClient = new RestClient<Person>(baseUrl, Person.class);
		Address address1 = new Address("Fairfield", "Iowa", "US", "52557");
		m_person1 = new Person("Van Ha", "Nguyen", "vanhaxl@gmail.com", address1, "123453789", false);
		m_person2 = new Person("Van Nam", "Nguyen", "namguyen@gmail.com", address1, "98765432", true);
		Person person3 = new Person("XXX", "YYYY", "abc@com", address1, "12321312", true);
		// Add
		m_person1 = personClient.add(m_person1);
		m_person2 = personClient.add(m_person2);
		person3 = personClient.add(person3);
		// Update
		m_person1.setEnable(true);
		personClient.update(m_person1);
		// Read
		m_person1 = personClient.getById(m_person1.getId());
		// Delete
		personClient.delete(person3);
		// ReadAll
		System.out.println("ALL PERSON: " + Arrays.asList(personClient.getAll()));		
	}
	
	private void testProduct(String baseUrl) {
		RestClient<Product> productClient = new RestClient<Product>(baseUrl, Product.class);
		m_product1 = new Product("Black Coffee", "Hot coffee without milk or suggar", 1, ProductType.BREAKFAST);
		m_product2 = new Product("Caramel", "Hot coffee with milk and suggar", 3, ProductType.BREAKFAST);
		Product product3 = new Product("Caramel", "Hot coffee with milk and suggar", 3, ProductType.BREAKFAST);
		// Add
		m_product1 = productClient.add(m_product1);
		m_product2 = productClient.add(m_product2);
		product3 = productClient.add(product3);
		// Update
		m_product1.setPrice(2);
		productClient.update(m_product1);
		// Read
		Product product4 = productClient.getById(product3.getId());
		System.out.println("READ Product" + product4);
		// Delete
		productClient.delete(product4);
		// Read All
		Product[] products = productClient.getAll();
		System.out.println("ALL PRODUCTS:" + Arrays.asList(products));
	}
	
	private void testOrder(String baseUrl) {
		RestClient<Order> orderClient = new RestClient<Order>(baseUrl, Order.class);
		Order order1 = new Order(new Date(), m_person1);
		order1.addOrderLine(m_product1, 10);
		order1.addOrderLine(m_product2, 20);
		Order order2 = new Order(new Date(), m_person2);
		order2.addOrderLine(m_product1, 30);
		Order order3 = new Order(new Date(), m_person2);
		// Add
		order1 = orderClient.add(order1);
		order2 = orderClient.add(order2);
		order3 = orderClient.add(order3);
		// Update
		order2.addOrderLine(m_product2, 40);
		order2.addOrderLine(m_product2, 50);
		orderClient.update(order2);
		// Delete
		orderClient.delete(order2);
		// Read
		System.out.println("ALL ORDER:" + Arrays.asList(orderClient.getAll()));		
	}
}
