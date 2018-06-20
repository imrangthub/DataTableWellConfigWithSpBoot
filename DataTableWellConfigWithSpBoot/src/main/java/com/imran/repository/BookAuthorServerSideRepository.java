package com.imran.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imran.model.BookAuthor;


@Repository("bookAuthorServerSideRepository")
public interface BookAuthorServerSideRepository extends JpaRepository<BookAuthor, Long>{
	
	List<BookAuthor> findAllByGender(String gender);

	
	
}
