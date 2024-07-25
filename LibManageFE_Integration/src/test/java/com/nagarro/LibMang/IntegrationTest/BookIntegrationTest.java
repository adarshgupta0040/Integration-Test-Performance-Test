package com.nagarro.LibMang.IntegrationTest;

import com.nagarro.LibMang.config.MvcAppContext;
import com.nagarro.LibMang.entities.Author;
import com.nagarro.LibMang.entities.Book;
import com.nagarro.LibMang.service.AuthorService;
import com.nagarro.LibMang.service.BookService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MvcAppContext.class})
@WebAppConfiguration
public class BookIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        cleanUpDatabase();
    }
    
    @AfterEach
    public void teardown() {
        // Clean up books with specific codes after each test
    	cleanUpDatabase();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = webApplicationContext.getServletContext();
        assertNotNull(servletContext);
        assertTrue(servletContext instanceof org.springframework.mock.web.MockServletContext);
    }

    @Test
    public void getAuthorsTest() throws Exception {
        mockMvc.perform(post("/Add"))
                .andExpect(status().isOk())
                .andExpect(view().name("AddBook"))
                .andExpect(model().attributeExists("Author"))
                .andExpect(model().attributeExists("date"));
    }

    @Test
    public void addBookTest() throws Exception {
        Book book = new Book();
        book.setBookCode(104);
        book.setBookName("Test Book");
        book.setAuthor("Test Author");
        book.setAddedOn("27 JUNE 2024");

        mockMvc.perform(post("/Addbook")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("bookCode", String.valueOf(book.getBookCode()))
                .param("bookName", book.getBookName())
                .param("author", book.getAuthor())
                .param("addedOn", book.getAddedOn()))
                .andExpect(status().isOk())
                .andExpect(view().name("BookListing"));

        Book savedBook = bookService.retrieveBooks().stream()
                .filter(b -> b.getBookCode() == book.getBookCode())
                .findFirst()
                .orElse(null);

        assertNotNull(savedBook);
        assertTrue(savedBook.getBookName().equals(book.getBookName()));
        assertTrue(savedBook.getAuthor().equals(book.getAuthor()));
    }
    
    @Test
    public void editBookTest() throws Exception {
        Book book = new Book();
        book.setBookCode(105);
        book.setBookName("Test Book");
        book.setAuthor("Test Author");
        book.setAddedOn("27 JUNE 2024");

        bookService.saveBook(book, "POST");

        mockMvc.perform(post("/Edit")
                .param("bookcode", String.valueOf(book.getBookCode()))
                .param("bookname", book.getBookName())
                .param("author", book.getAuthor())
                .param("addedOn", book.getAddedOn()))
                .andExpect(status().isOk())
                .andExpect(view().name("EditBook"))
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attributeExists("Author"));
    }
    
    @Test
    public void updateBookTest() throws Exception {
        Book book = new Book();
        book.setBookCode(105);
        book.setBookName("Updated Book");
        book.setAuthor("Updated Author");
        book.setAddedOn("28 JUNE 2024");

        mockMvc.perform(post("/Editbook")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("bookCode", String.valueOf(book.getBookCode()))
                .param("bookName", book.getBookName())
                .param("author", book.getAuthor())
                .param("addedOn", book.getAddedOn()))
                .andExpect(status().isOk())
                .andExpect(view().name("BookListing"));

        Book savedBook = bookService.retrieveBooks().stream()
                .filter(b -> b.getBookCode() == book.getBookCode())
                .findFirst()
                .orElse(null);

        assertNotNull(savedBook);
        assertTrue(savedBook.getBookName().equals(book.getBookName()));
        assertTrue(savedBook.getAuthor().equals(book.getAuthor()));
    }

    @Test
    public void deleteBookTest() throws Exception {
        Book book = new Book();
        book.setBookCode(106);
        book.setBookName("Book to Delete");
        book.setAuthor("Author to Delete");
        book.setAddedOn("29 JUNE 2024");

        bookService.saveBook(book, "POST");

        mockMvc.perform(post("/Delete")
                .param("bookCode", String.valueOf(book.getBookCode())))
                .andExpect(status().isOk())
                .andExpect(view().name("BookListing"));
        
        Book deletedBook = bookService.retrieveBooks().stream()
                .filter(b -> b.getBookCode() == book.getBookCode())
                .findFirst()
                .orElse(null);

        assertTrue(deletedBook == null);
    }

    @Test
    public void bookListingTest() throws Exception {
        mockMvc.perform(post("/BookListing"))
                .andExpect(view().name("redirect:/BookListing"));
    }
    
    
    private void cleanUpDatabase() {
        // Clean up books with specific codes
        bookService.deleteBook(104);
        bookService.deleteBook(105);
        bookService.deleteBook(106);
    }
    
    
    
}
