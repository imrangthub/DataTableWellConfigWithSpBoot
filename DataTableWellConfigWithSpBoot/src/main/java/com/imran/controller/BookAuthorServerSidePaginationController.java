package com.imran.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.imran.model.BookAuthor;
import com.imran.service.BookAuthorServerSidePaginationService;



@Controller
@RequestMapping("library/author/serverSidePagination")
public class BookAuthorServerSidePaginationController {
	
	@Autowired
	private BookAuthorServerSidePaginationService bookAuthorServerSidePaginationService;
	
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> list(@RequestParam("gender") String gender) {
		
		LinkedHashMap<String, Object> gridInfo = new LinkedHashMap<String, Object>();
		
		Map<String, Object> results = new HashMap<String, Object>();
		
	        LinkedHashMap gridData;
	        String result;

	        LinkedHashMap<String, Object> resultMap = bookAuthorServerSidePaginationService.paginateList(gridInfo);
	        
	        
	        System.out.println(resultMap);
	        System.out.println(resultMap.keySet());
	        System.out.println(resultMap.values());
	        
	        
	        results.put("iTotalRecords", resultMap.get("totalCount"));
	        results.put("iDisplayStart", resultMap.get("iDisplayStart"));
	        results.put("iDisplayLength", resultMap.get("iDisplayLength"));
	        results.put("aaData", resultMap.get("aData"));	        
	
	
           return results;
	}
	

	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView listAuthor() {

		return new ModelAndView("library/bookAuthor/serversidepagination");
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
		bookAuthorServerSidePaginationService.saveAuthor(bookAuthor);
		  result.put("isError", Boolean.FALSE);
		  result.put("message","Successfully saved  Book author.");
		
		return result;
	}
	
	
	@RequestMapping(value="/edit/{id}",  method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> edit(@PathVariable("id") Long id){
		Map<String, Object> result = new HashMap<String, Object>();
		BookAuthor bookAuthor = bookAuthorServerSidePaginationService.authorById(id);
		System.out.println(bookAuthor.getName());
		  result.put("isError", Boolean.FALSE);
		result.put("message","You Click On "+bookAuthor.getName());
		result.put("obj",bookAuthor);
		return result;
		
	}
	
	@RequestMapping(value="/delete/{id}",  method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> delete(@PathVariable("id") Long id){
		Map<String, Object> result = new HashMap<String, Object>();

		BookAuthor bookAuthor = bookAuthorServerSidePaginationService.authorById(id);
		if(bookAuthor == null) {
			  result.put("isError", Boolean.TRUE);
			  result.put("message","Delete failed, Author Dose not exit any more.");
			return result;
		}
		
		bookAuthorServerSidePaginationService.delete(id);
		  result.put("isError", Boolean.FALSE);
		result.put("message","Successfully Delete Author");
		return result;
		
	}




}
