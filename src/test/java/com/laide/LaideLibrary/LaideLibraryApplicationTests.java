package com.laide.LaideLibrary;

import com.laide.LaideLibrary.entities.Book;
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
}
