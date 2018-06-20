package com.imran.service;

import static org.assertj.core.api.Assertions.filter;
import static org.mockito.Matchers.eq;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imran.model.BookAuthor;
import com.imran.repository.BookAuthorRepository;
import com.imran.repository.BookAuthorServerSideRepository;


@Service
public class BookAuthorServerSideService {
	
	
	@Autowired
	private BookAuthorServerSideRepository bookAuthorServerSideRepository;

	
	public List<BookAuthor> list() {
		
		return bookAuthorServerSideRepository.findAll();
		
	}
	
	public List<BookAuthor> listByGender(String gender) {
		
		return bookAuthorServerSideRepository.findAllByGender(gender);
		
	}
	
	
	public void saveAuthor(BookAuthor bookAuthor) {
		bookAuthorServerSideRepository.save(bookAuthor);
		
	}
	
	public BookAuthor authorById(Long bookAuthorId) {
		return bookAuthorServerSideRepository.findOne(bookAuthorId);
		
	}
	
	public void delete(Long authorId) {
		bookAuthorServerSideRepository.delete(authorId);
		
	}

}
