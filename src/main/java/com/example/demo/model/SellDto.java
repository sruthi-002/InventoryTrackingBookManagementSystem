package com.example.demo.model;

public class SellDto {
	int id;
	int Total;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTotal() {
		return Total;
	}
	public void setTotal(int total) {
		Total = total;
	}
	public SellDto(int id, int total) {
		super();
		this.id = id;
		Total = total;
	}
	

}
