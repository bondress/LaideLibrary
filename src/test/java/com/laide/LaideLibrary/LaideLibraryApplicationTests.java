package com.laide.LaideLibrary;

import com.laide.LaideLibrary.entities.Book;
import com.laide.LaideLibrary.entities.Rental;
import com.laide.LaideLibrary.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = LaideLibraryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LaideLibraryApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetAllBooks() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/allBooks",
				HttpMethod.GET, entity, String.class);
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetBook() {
		Book book = restTemplate.getForObject(getRootUrl() + "/getBookById/1", Book.class);
		System.out.println(book.getTitle());
		assertNotNull(book);
	}

	@Test
	public void testCreateBook() {
		Book book = new Book();
		book.setBookId(9);
		book.setAuthor("Enid Blyton");
		book.setTitle("The Bed that ran away");
		book.setRentalFee(new BigDecimal(20.5));
		ResponseEntity<Book> postResponse = restTemplate.postForEntity(getRootUrl() + "/addBook", book, Book.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateBook() {
		int id = 1;
		Book book = restTemplate.getForObject(getRootUrl() + "/books/" + id, Book.class);
		book.setTitle("Purple Hibiscus");
		book.setAuthor("Chimamanda Adichie");
		book.setRentalFee(new BigDecimal(35.44));
		restTemplate.put(getRootUrl() + "/updateBookById/" + id, book);
		Book updatedBook = restTemplate.getForObject(getRootUrl() + "/updateBookById/" + id, Book.class);
		assertNotNull(updatedBook);
	}

	@Test
	public void testDeleteBook() {
		int id = 2;
		Book book = restTemplate.getForObject(getRootUrl() + "/deleteBookById/" + id, Book.class);
		assertNotNull(book);
		restTemplate.delete(getRootUrl() + "/deleteBookById/" + id);
		try {
			book = restTemplate.getForObject(getRootUrl() + "/deleteBookById/" + id, Book.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

	@Test
	public void testGetAllUsers() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/allUsers",
				HttpMethod.GET, entity, String.class);
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetUser() {
		User user = restTemplate.getForObject(getRootUrl() + "/getUserById/1", User.class);
		System.out.println(user.getEmail());
		assertNotNull(user);
	}

	@Test
	public void testCreateUser() {
		User user = new User();
		user.setUserId(4);
		user.setEmail("test@gmail.com");
		user.setPassword("password");
		ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/addUser", user, User.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateUser() {
		int id = 1;
		User user = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);
		user.setEmail("test@gmail.com");
		user.setPassword("password");
		restTemplate.put(getRootUrl() + "/updateUserById/" + id, user);
		User updatedUser = restTemplate.getForObject(getRootUrl() + "/updateUserById/" + id, User.class);
		assertNotNull(updatedUser);
	}

	@Test
	public void testDeleteUser() {
		int id = 2;
		User user = restTemplate.getForObject(getRootUrl() + "/deleteUserById/" + id, User.class);
		assertNotNull(user);
		restTemplate.delete(getRootUrl() + "/deleteUserById/" + id);
		try {
			user = restTemplate.getForObject(getRootUrl() + "/deleteUserById/" + id, User.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

	@Test
	public void testGetAllRentals() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/allRentals",
				HttpMethod.GET, entity, String.class);
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetRental() {
		Rental rental = restTemplate.getForObject(getRootUrl() + "/getRentalById/1", Rental.class);
		System.out.println(rental.getUserId());
		assertNotNull(rental);
	}

	@Test
	public void testCreateRental() {
		Rental rental = new Rental();
		rental.setRentalId(4);
		rental.setUserId(2);
		rental.setBookId(3);
		rental.setReturned(0);
		ResponseEntity<Rental> postResponse = restTemplate.postForEntity(getRootUrl() + "/addRental", rental, Rental.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateRental() {
		int id = 1;
		Rental rental = restTemplate.getForObject(getRootUrl() + "/rentals/" + id, Rental.class);
		rental.setUserId(1);
		rental.setBookId(1);
		rental.setReturned(1);
		restTemplate.put(getRootUrl() + "/updateRentalById/" + id, rental);
		Rental updatedRental = restTemplate.getForObject(getRootUrl() + "/updateRentalById/" + id, Rental.class);
		assertNotNull(updatedRental);
	}

	@Test
	public void testDeleteRental() {
		int id = 2;
		Rental rental = restTemplate.getForObject(getRootUrl() + "/deleteRentalById/" + id, Rental.class);
		assertNotNull(rental);
		restTemplate.delete(getRootUrl() + "/deleteRentalById/" + id);
		try {
			rental = restTemplate.getForObject(getRootUrl() + "/deleteRentalById/" + id, Rental.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
