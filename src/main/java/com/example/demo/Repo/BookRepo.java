package com.example.demo.Repo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;

@Repository
public interface BookRepo extends JpaRepository<Book,Integer>{
	@Query(value="SELECT * FROM InventoryTrackingSystem as b WHERE (b.title like '%?:keyword%' OR b.author like'%:keyword%' OR b.id like '%:keyword%') AND b.category like '%:category%")
	List<Book> findBookByKeywordAndCategory(String keyword , String category);
	@Query(value = "Select IF(SUM(b.sold) IS NULL,0,SUM(b.sold)) from book b where " +
            "(b.title like %?1% OR CAST(b.id as CHAR) like %?1% OR LOWER(b.author) like %?1%) " +
            "AND b.category=?2 AND b.sold>0")
    int countNumberOfBooksSold(String keyword, String category);
}
