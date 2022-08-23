package com.example.demo.Repo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;

@Repository
public interface BookRepo extends JpaRepository<Book,Integer>{

	@Query(value="SELECT B FROM InventoryTrackingSystem B WHERE (title like '%?1%' OR author like'%?1%' OR id like '%?1%') AND category like '%?2%")
	Book findBookByKeywordAndCategory(String keyword , String category);
}
