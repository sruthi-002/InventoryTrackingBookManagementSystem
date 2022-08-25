package com.example.demo.ControllerTest;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.example.demo.Controller.BookController;
import com.example.demo.Service.BookService;
import com.example.demo.model.Book;
import com.example.demo.model.SellDto;
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerTest {
    private final int id = 1;
    private final String title = "title";
    private final String author = "author";
    private final float price = 50;
    private final String category = "DRAMA";
    private final int totalCount = 2;
    private final int sold = 2;
    private final int quantitytoadd = 1;
    private final String keyword = "author";
    private MockMvc mockMvc;
    @Autowired
    private BookController bookController;
    @MockBean
    private BookService bookservice;
    @Test
    public void testaddbook() throws Exception {
        String url = "/api/add-book/" + id + "/" + quantitytoadd;
        doNothing().when(bookservice).addbook(id, quantitytoadd);
        mockMvc.perform(MockMvcRequestBuilders
                .put(url))
                .andExpect(status().isOk());
    }
    @Test
    public void testgetnumberofbooks() throws Exception {
        String url = "/api/number-of-books/" + id;
        when(bookservice.getnumberofbooks(id)).thenReturn(totalCount);
        mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(totalCount)));
    }
    @Test
    public void testsellaBook() throws Exception {
        String url = "/api/sell-book/" + id;
        doNothing().when(bookservice).sellabook(id);
        mockMvc.perform(MockMvcRequestBuilders
                .put(url))
                .andExpect(status().isOk());
    }
    @Test
    public void testGetBookByCategoryKeyword() throws Exception {
        String url = "/api/books?"
                + "keyword=" + keyword
                + "&category=" + category;
        List<Book> bookDtoList = new ArrayList<Book>();
        when(bookservice.getBookByCategoryKeyWord(keyword, category)).thenReturn(bookDtoList);
        mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").value(title))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].author").value(author))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].category").value(category))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].totalCount").value(totalCount));
    }

    @Test
    public void testgetNumberOfBooksSoldByCategoryAndKeyword() throws Exception {
        String url = "/api/number-of-books?"
                + "keyword=" + keyword
                + "&category=" + category;
        when(bookservice.getNumberOfBooksSoldByCategoryAndKeyword(keyword, category)).thenReturn(sold);
        mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(sold)));
    }
}
