package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Repo.BookRepo;
import com.example.demo.Service.Exception.BookNotFoundException;
import com.example.demo.Service.Exception.UnwantedException;
import com.example.demo.model.Book;
import  com.example.demo.model.SellDto;
@Service
public class BookService {
	@Autowired
	private BookRepo bookrepo;
	public Book addnewbook(Book book) {
			return bookrepo.save(book);
	}
	public Book addbook(int id , int total) {
		Book book = bookrepo.findById(id).orElse(null);
		int totalCountAfterAdd = book.getTotal() +total;
        book.setTotal(totalCountAfterAdd);
        return bookrepo.save(book);
	}
	public Optional<Book> getbookbyid(int id) {
		return bookrepo.findById(id);
	}
	public List<Book> allbook() {
		ArrayList<Book> books = new ArrayList<Book>();
		bookrepo.findAll().forEach(book -> books.add(book));
		return books;
	}
	public int getnumberofbooks(int id) {
	        Optional<Book> book = bookrepo.findById((int) id);
	        return book.isPresent() ? book.get().getTotal() : 0;
	}
	public void sellabook(int id) {
		Book book = bookrepo.findById(id).orElseThrow(() -> new BookNotFoundException("Book with id: " + id + " is not found."));
		int totalCount = book.getTotal()- 1;
		int sold = book.getSold() + 1;
		book.setTotal(totalCount);
        book.setSold(sold);
        bookrepo.save(book);
	}
	public Book updateBook(int id,  Book bookDto) {
		Book b = bookrepo.findById(bookDto.getId()).orElseThrow(() -> new BookNotFoundException("Book with id: " + id + " is not found."));
		List<Integer> list = new ArrayList<Integer>();
		if(b==null)
		{
		     return bookrepo.save(bookDto);
		}
		else
		{
			bookrepo.deleteById(b.getId());
			return bookrepo.save(bookDto);
		}
        }
	public List<Integer> Sellbooklist(int id ,SellDto sellDto) {
		Book b = bookrepo.findById(sellDto.getTotal()).orElseThrow(() -> new BookNotFoundException("Book with id: " + id + " is not found."));
		List<Integer> list = new ArrayList<Integer>();
		int idvalue = sellDto.getId();
		list.add(id);
		int TotalCount = sellDto.getTotal();
		list.add(TotalCount);
		return list;	
	}
	
	}
	
