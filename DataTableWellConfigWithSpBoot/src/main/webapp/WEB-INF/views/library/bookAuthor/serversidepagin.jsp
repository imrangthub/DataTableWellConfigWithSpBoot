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
	    <h1>Welcome to Server side Pagination</h1> 
	  </div>
	  
	  <div class="row">
	  	<div class="pull-right">
		 <a type="button" class="btn btn-primary" onclick="serverSidePagin.addBookAuthor()">
		  Add Author
		 </a>
		 <button type="button" class="btn btn-warning" onclick="serverSidePagin.editBookAuthor()">
		  Edit Author
		 </button>
		 <button type="button" class="btn btn-danger" onclick="serverSidePagin.deleteBookAuthor()">
		  Delete Author
		 </button>
		</div>
	  </div>

		
            <div class="table-responsive">
				<table id="serverSidePaginTable"
					class="table table-striped table-bordered table_data_all"
					style="width: 100%">
				</table>
			</div>

	  
	<!-- 	  <div class="col-md-8 col-md-offset-2 well">
		    <table class="table" id="serverSidePaginTable">
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
		  </div> -->

	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  

<!-- Modal -->
<div class="modal fade" id="addEditAuthorServerSidePageinModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
   <form id="create-author-server-side-pagin-form">
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
        <button type="button" class="btn btn-secondary" onclick="serverSidePagin.celcelBtn()">Close</button>
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
<script type="text/javascript" src="/resources/javascripts/library/bookAuthorServersidePagin.js"></script>
<script type="text/javascript" src="/resources/javascripts/application.js"></script>



<script>
$(document).ready(function(){
	
});
</script>
</body>
</html>