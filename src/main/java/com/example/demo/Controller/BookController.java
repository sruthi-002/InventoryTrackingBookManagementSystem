package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.BookService;
import com.example.demo.model.Book;
import com.example.demo.model.SellDto;

@RestController
@RequestMapping("/api")
public class BookController {
	@Autowired
	private BookService bookservice;
	@PostMapping("/addnewbook")
	public Book addnewbook(@RequestBody Book book)
	{
		return bookservice.addnewbook(book);
	}
	@PostMapping("/addbook/{id}")
	public Book addbook(@PathVariable ("id") int id,@RequestParam("total") int total)
	{
		return bookservice.addbook(id,total);
	}
	@GetMapping("/book/{id}")
	public Optional<Book> getbookbyid(@PathVariable ("id") int id)
	{
		return bookservice.getbookbyid(id);
	}
	@GetMapping("/booklist")
	public List<Book> allbook()
	{
		return bookservice.allbook();
	}
	@GetMapping("/number-of-books/{id}")
	public int getnumberofbooks(@PathVariable ("id") int id)
	{
		return bookservice.getnumberofbooks(id);
	}
	@GetMapping("/sell-book/{id}")
	public void sellabook(@PathVariable ("id") int id)
	{
		bookservice.sellabook(id);
	}
	@PutMapping("/books/{id}")
	public Book updateBook(@PathVariable ("id")int id, @RequestBody Book bookDto) {
	 return bookservice.updateBook(id, bookDto);
}
	@PutMapping("/Sell-books/{id}")
	public void Sellbooklist(@RequestBody List<SellDto> SellDto)
	{
	         bookservice.Sellbooklist(SellDto);
	}
	@GetMapping("/books")
    public List<Book> getBookByCategoryKeyWord(@RequestParam String keyword,
                                                  @RequestParam String category) {
        return bookservice.getBookByCategoryKeyWord(keyword, category);
    }
	@GetMapping("/number-of-books")
    public int getNumberOfBooksSoldByCategoryAndKeyword(@RequestParam String keyword,
                                                        @RequestParam String category) {
        return bookservice.getNumberOfBooksSoldByCategoryAndKeyword(keyword, category);
    }

}
