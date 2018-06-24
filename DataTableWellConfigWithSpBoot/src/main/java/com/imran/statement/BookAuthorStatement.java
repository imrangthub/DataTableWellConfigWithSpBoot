package com.imran.statement;

public class BookAuthorStatement {
	
 public static String baseQuery() {
		String baseQuery = "SELECT id, name, country, gender, (SELECT COUNT(1) FROM book_author) AS total_records  FROM book_author";
		return baseQuery; 
	}

  public static String AuthorIsExistence(String authorName) {
	  return   "select name , id from book_author where name ='"+authorName+"'";
  }

}
