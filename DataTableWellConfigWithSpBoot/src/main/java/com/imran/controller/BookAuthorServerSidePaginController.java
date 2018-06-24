package com.imran.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;
import com.imran.model.BookAuthor;
import com.imran.service.BookAuthorServerSideService;
import com.imran.pagination.DataTableRequest;
import com.imran.pagination.DataTableResults;
import com.imran.pagination.PaginationCriteria;
import com.imran.statement.BookAuthorStatement;
import com.imran.util.AppUtil;


@Controller
@RequestMapping("library/author/serversidepagin")
public class BookAuthorServerSidePaginController {
	
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private BookAuthorServerSideService bookAuthorServerSideService;
	
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	@ResponseBody
	public String listPaginated(HttpServletRequest request, HttpServletResponse response, Model model) {
		
	    DataTableRequest<BookAuthor> dataTableInRQ = new DataTableRequest<BookAuthor>(request);
		PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();
		String paginatedQuery = AppUtil.buildPaginatedQuery(BookAuthorStatement.baseQuery(), pagination);
		
		Query query = entityManager.createNativeQuery(paginatedQuery, BookAuthor.class);
		
		@SuppressWarnings("unchecked")
		List<BookAuthor> comList = query.getResultList();
		DataTableResults<BookAuthor> dataTableResult = new DataTableResults<BookAuthor>();
		dataTableResult.setDraw(dataTableInRQ.getDraw());
		dataTableResult.setListOfDataObjects(comList);
		
		if (!AppUtil.isObjectEmpty(comList)) {
			dataTableResult.setRecordsTotal(comList.get(0).getTotalRecords().toString());
					
			if (dataTableInRQ.getPaginationRequest().isFilterByEmpty()) {
				dataTableResult.setRecordsFiltered(comList.get(0).getTotalRecords().toString());
						
			} else {
				dataTableResult.setRecordsFiltered(Integer.toString(comList.size()));
			}
		}
		
		Gson json = new Gson();
	    String companyJson = json.toJson(dataTableResult);
		return companyJson;
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView listAuthor() {
		return new ModelAndView("library/bookAuthor/serversidepagin");
	}
	
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addBook(@Valid BookAuthor bookAuthor, BindingResult bindingResult){
		Map<String, Object> result = new HashMap<String, Object>();
		
		if (bookAuthor.getName().isEmpty() || bookAuthor.getCountry().isEmpty()) {
			  result.put("isError", Boolean.TRUE);
			  result.put("message","Require field is empty.");
			  return result;
		}
		
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors().toString());
			  result.put("isError", Boolean.TRUE);
			  result.put("message",bindingResult.getAllErrors().toString());
			  return result;
		}
		bookAuthorServerSideService.saveAuthor(bookAuthor);
		  result.put("isError", Boolean.FALSE);
		  result.put("message","Successfully saved  Book author.");
		
		return result;
	}
	
	@RequestMapping(value="/delete/{id}",  method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> delete(@PathVariable("id") Long id){
		Map<String, Object> result = new HashMap<String, Object>();

		BookAuthor bookAuthor = bookAuthorServerSideService.authorById(id);
		if(bookAuthor == null) {
			  result.put("isError", Boolean.TRUE);
			  result.put("message","Delete failed, Author Dose not exit any more.");
			return result;
		}
		
		bookAuthorServerSideService.delete(id);
		  result.put("isError", Boolean.FALSE);
		result.put("message","Successfully Delete Author");
		return result;
		
	}


}
