<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Static content -->
<link rel="stylesheet" href="/resources/stylesheets/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/stylesheets/vendor/bootstrap/css/dataTables.bootstrap.css">
<link rel="stylesheet" href="/resources/stylesheets/application.css">
<title>Spring Boot</title>
</head>
<body>
	<div class="container">
	  <div class="jumbotron">
	    <h1>Welcome to Jquery DataTable Project</h1> 
	  </div>
	  <p>This is a project where I working with jQuery DataTable and Handle ajax request.</p>  
	   <!-- Button trigger modal -->
		<div class="pull-right">
		 <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addEditAuthorModal">
		  Add Author
		 </button><br>
		</div>
		

	  <div class="col-md-8 col-md-offset-2 well">
	    <table class="table" id="bookAuthoreTable">
		  <thead class="thead-default">
		    <tr>
		      <th>#</th>
		      <th>Author Name</th>
		      <th>Gender</th>
		      <th>Country</th>
		      <th>Action</th>
		    </tr>
		  </thead>
		  <tbody>
		   	<c:forEach items="${authors}" var="author">
			  <tr>
				<td><c:out value="${author.id}"/></td>
				<td><c:out value="${author.name}"/></td>
				<td><c:out value="${author.gender}"/></td>
				<td><c:out value="${author.country}"/></td>
				<td>
				<a href=""  class="edit-reference" referenceId="${author.id }">Edit</a> | 
				<a href="" class="delete-reference" referenceId="${author.id }">Delete</a>
				</td>
			  </tr>
		    </c:forEach>
		  </tbody>
        </table>
	  </div>
	  
	    <div class="col-md-8 col-md-offset-2 well">
	    <table class="table" id="myTable">
		  <thead class="thead-default">
		    <tr>
		      <th>#</th>
		      <th>Author Name</th>
		      <th>Gender</th>
		      <th>Country</th>
		      <th>Action</th>
		    </tr>
		  </thead>
        </table>
	  </div>

	  
	  

<!-- Modal -->
<div class="modal fade" id="addEditAuthorModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
   <form id="create-author-form">
     <input type="hidden" name="id"  id="id">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="exampleModalLabel">Author Information</h4>
      </div>
      <div class="modal-body">
		  <div class="form-group row">
		    <label for="name" class="col-sm-3 col-form-label">Author Name</label>
		      <div class="col-sm-8">
	           <input type="text" name="name"  class="form-control" id="name" placeholder="name">
	          </div>
	       </div>
		   <div class="form-group row">
		    <label for="gender" class="col-sm-3 col-form-label">Gender</label>
		      <div class="col-sm-8">
	           <select name="gender" id="gender" class="form-control" >
				  <option selected value="MALE">Male</option>
				  <option value="FEMALE" ${gender == "FEMALE" ? 'selected="selected"' : ''}>Female</option>
				  <option value="OTHER">Other</option>
				</select>
	          </div>
	       </div>
	       <div class="form-group row">
		    <label for="country" class="col-sm-3 col-form-label">Country</label>
		      <div class="col-sm-8">
	           <input type="text" name="country" class="form-control" id="country" placeholder="Country">
	          </div>
	       </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" id="form-submit-btn" class="btn btn-primary">Save Author</button>
      </div>
    </div>
   </form>
  </div>
</div>
 </div>
<script type="text/javascript" src="/resources/javascripts/vendor/jquery/jquery-2.2.0.min.js"></script>
<script type="text/javascript" src="/resources/javascripts/vendor/jquery/jquery.dataTables.js"></script>
<script type="text/javascript" src="/resources/javascripts/vendor/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/javascripts/vendor/bootstrap/bootstrap-growl.min.js"></script>
<script type="text/javascript" src="/resources/javascripts/library/bookAuthor.js"></script>
<script type="text/javascript" src="/resources/javascripts/application.js"></script>



<script>
$(document).ready(function(){
	var authorData;
	
	  $('#myTable').on('click', 'a.editor_remove', function (e) {
		  
		     var selectRow = $(this).parents('tr');
		        var confirmDel = confirm("Are you sure?");
		        if (confirmDel == true) {
		            var control = this;
		         
		            var referenceId = $(control).attr('referenceId');
		            
		            var tr = $(this).closest('tr');
		            
		            alert(JSON.stringify(tr));
		            
		            var id = tr.children('td:eq(0)').text(); //get the text from first col of current row
		        }
	          e.preventDefault();
	    });
	

	
	
	
});
</script>
</body>
</html>