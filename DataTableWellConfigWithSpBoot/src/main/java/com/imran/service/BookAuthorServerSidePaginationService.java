package com.imran.service;

import static org.assertj.core.api.Assertions.filter;
import static org.mockito.Matchers.eq;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imran.model.BookAuthor;
import com.imran.repository.BookAuthorRepository;
import com.imran.repository.BookAuthorServerSidePaginationRepository;
import com.imran.repository.BookAuthorServerSideRepository;


@Service
public class BookAuthorServerSidePaginationService {
	
	
	@Autowired
	private BookAuthorServerSidePaginationRepository bookAuthorServerSidePaginationRepository;
	
	
   public LinkedHashMap<String, Object>  paginateList(LinkedHashMap<String, Object> params){
//	   
//       int iDisplayStart = params.iDisplayStart ? params.getInt('iDisplayStart') : CommonUtils.DEFAULT_PAGINATION_START
//       int iDisplayLength = params.iDisplayLength ? params.getInt('iDisplayLength') : CommonUtils.DEFAULT_PAGINATION_LENGTH
//       String sSortDir = params.sSortDir_0 ? params.sSortDir_0 : CommonUtils.DEFAULT_PAGINATION_SORT_ORDER
//       int iSortingCol = params.iSortCol_0 ? params.getInt('iSortCol_0') : CommonUtils.DEFAULT_PAGINATION_SORT_IDX
//       String sSearch = params.sSearch ? params.sSearch : null;
	   
	   int iDisplayStart =  0;
	   int iDisplayLength = 4;
	

       
       
	   
	   LinkedHashMap<String, Object> paginationData = new  LinkedHashMap<String, Object>();
	   
	   LinkedHashMap<String, Object>   dataReturns = new  LinkedHashMap<String, Object>();
       List<BookAuthor> authorData = new ArrayList();
	   
	   int totalCount = bookAuthorServerSidePaginationRepository.findAll().size();
	   authorData = bookAuthorServerSidePaginationRepository.findAll(); 
	   
	   for(BookAuthor Obj : authorData) {
		   dataReturns.put("DT_RowId", Obj.getId());
		   dataReturns.put("0", Obj.getName());
		   dataReturns.put("1", Obj.getGender());
		   dataReturns.put("2", Obj.getCountry());
		   dataReturns.put("3", null);
		   dataReturns.put("DT_RowId", Obj.getId());
	   }
	  
	   
	   paginationData.put("totalCount", totalCount);
	   paginationData.put("iDisplayStart", iDisplayStart);
	   paginationData.put("iDisplayLength", iDisplayLength);
	   
	   paginationData.put("aData", dataReturns);
	  
		return paginationData;
		
	}
	
	public List<BookAuthor> list() {
		
		return bookAuthorServerSidePaginationRepository.findAll();
		
	}
	
	public List<BookAuthor> listByGender(String gender) {
		
		return bookAuthorServerSidePaginationRepository.findAllByGender(gender);
		
	}
	
	
	public void saveAuthor(BookAuthor bookAuthor) {
		bookAuthorServerSidePaginationRepository.save(bookAuthor);
		
	}
	
	public BookAuthor authorById(Long bookAuthorId) {
		return bookAuthorServerSidePaginationRepository.findOne(bookAuthorId);
		
	}
	
	public void delete(Long authorId) {
		bookAuthorServerSidePaginationRepository.delete(authorId);
		
	}

}
