package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="Books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	@Autowired
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	@Column(name="author")
	String author;
	@Column(name="title")
	String title;
	@Column(name="category")
	String category;
	@Column(name="price")
	int price;
	@Column(name="Total")
	int total;
	@Column(name="sold")
	int sold;
	public  int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	public Book(int id, String author, String title, String category, int price, int total, int sold) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.category = category;
		this.price = price;
		this.total = total;
		this.sold = sold;
	}
}
