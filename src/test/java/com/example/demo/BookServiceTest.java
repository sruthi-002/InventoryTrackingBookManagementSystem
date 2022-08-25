package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.Repo.BookRepo;
import com.example.demo.Service.BookService;
import com.example.demo.Service.Exception.BookNotFoundException;
import com.example.demo.Service.Exception.UnwantedException;
import com.example.demo.model.Book;
import com.example.demo.model.SellDto;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    private final Integer id = 1;
    private final String category = "ACTION";
    private final int totalCount = 2;
    private final int sold = 2;
    private final String keyword = "author";
    @Mock
    private BookRepo bookrepo;
    @InjectMocks
    private BookService bookservice;
    @Test
    public void testaddnewbook() {
        Book books = mock(Book.class);
        Book book = mock(Book.class);
        when(books.getId()).thenReturn(id);
        when(bookrepo.findById(id)).thenReturn(Optional.empty());
        bookservice.addnewbook(book);
        verify(bookrepo).save(book);
    }
    @Test
    public void testaddbook() {
        Book book = mock(Book.class);
        when(bookrepo.findById(id)).thenReturn(Optional.ofNullable(book));
        when(book.getTotal()).thenReturn(totalCount);
        bookservice.addbook(id, 1);
        verify(bookrepo).save(book);
    }
    @Test
    public void testgetbookbyid() {
        Book book = mock(Book.class);
        Book books = mock(Book.class);
        when(bookrepo.findById(id)).thenReturn(Optional.ofNullable(book));
        Optional<Book> booknew = Optional.ofNullable(bookservice.getbookbyid(id));
        assertEquals(book, booknew);
    }
    @Test
    public void testallbook() {
        Book book = mock(Book.class);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        Book books = mock(Book.class);
        List<Book> bookList1 = new ArrayList<Book>();
        bookList1.add(books);
        when(bookrepo.findAll()).thenReturn(bookList1);
        List<Book> list2= bookservice.allbook();
        assertEquals(bookList1, list2);
    }
    @Test
    public void testgetnumberofbooks() {
        Book book = mock(Book.class);
        when(bookrepo.findById(id)).thenReturn(Optional.ofNullable(book));
        when(book.getTotal()).thenReturn(totalCount);
        int num = bookservice.getnumberofbooks(id);
        assertEquals(totalCount, num);
    }
    @Test
    public void testupdateBook() {
        Book bookDto = mock(Book.class);
        Book book = mock(Book.class);
        when(bookDto.getId()).thenReturn(id);
        Book bookFromRepo = mock(Book.class);
        when(bookrepo.getOne(id)).thenReturn(bookFromRepo);
        when(bookFromRepo.getSold()).thenReturn(sold);
        bookservice.updateBook(id, bookDto);
        verify(bookrepo).save(book);
    }
    @Test
    public void testsellabook() {
        Book book = mock(Book.class);
        when(bookrepo.findById(id)).thenReturn(Optional.ofNullable(book));
        when(book.getTotal()).thenReturn(totalCount);
        when(book.getSold()).thenReturn(sold);
        bookservice.sellabook(id);
        verify(bookrepo).save(book);
    }
    @Test
    public void testgetBookByCategoryKeyWord() {
        Book book = mock(Book.class);
        List<Book> books = new ArrayList<>();
        books.add(book);
        Book bookDto = mock(Book.class);
        when(bookrepo.findBookByKeywordAndCategory(keyword.toLowerCase(), category)).thenReturn(books);
        List<Book> booklist = bookservice.getBookByCategoryKeyWord(keyword, category);
        assertEquals(book, booklist);
    }
    @Test
    public void testgetNumberOfBooksSoldByCategoryAndKeyword() {
        Integer count = Integer.valueOf(totalCount);
        when(bookrepo.countNumberOfBooksSold(keyword.toLowerCase(), category)).thenReturn(count);
        int num = bookservice.getNumberOfBooksSoldByCategoryAndKeyword(keyword, category);
        assertEquals(totalCount, num);
    }
    }
