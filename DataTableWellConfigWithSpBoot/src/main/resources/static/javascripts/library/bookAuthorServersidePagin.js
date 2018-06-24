var serverSidePagin = {
		
	 dataTableObj: null,
	 modalityFilterArray: null,
	 selectPatient: null,
	 selectPatientArr: null,
     
    initView:function(){
    	serverSidePagin.initGrid();   	
     },
    
    initGrid:function(){
       serverSidePagin.dataTableObj = $('#serverSidePaginTable').DataTable({
		        "processing": true,
		        "serverSide": true,
		        /* "pageLength": 5, */
		        "ajax": {
		            "url": "list",
		            "data": function ( data ) {
					 //process data before sent to server.
		         }},
		        "columns": [
		                    { "data": "id", "name": "id",  "title" : "ID"  },
		                    { "data": "name","name": "name",  "title" : "Name"},
		                    { "data": "gender","name": "gender", "title" : "Gender"},
		                    { "data": "country", "name": "country",  "title" : "Country"}
		                ]    
			});
			
    		 
      },
      
      initForm: function (formId) {      	  
    	$('#serverSidePaginTable tbody tr').removeClass("activetable");   
    	serverSidePagin.submitAuthorForm();
      },
      
      addBookAuthor:function(){  	  
          $('#addEditAuthorServerSidePageinModal').modal('show');  	
          serverSidePagin.initForm("create-author-server-side-pagin-form");
      },
      editBookAuthor:function(){  
    	  	var bookAuthorObj = serverSidePagin.getDataTableObj();
        	var dtLength = app.getTableLength(bookAuthorObj);
          	if(dtLength==0){
          		 showErrorMsg("Must select a author for this action");
          	}else{   		 
                $("#id").val(app.getTableSelectRow(bookAuthorObj,'id'));
                $("#name").val(app.getTableSelectRow(bookAuthorObj,'name'));
                $("#gender").val(app.getTableSelectRow(bookAuthorObj,'gender'));
                $('#country').val(app.getTableSelectRow(bookAuthorObj,'country'));
                
                $("#form-submit-btn").html("Update Autor");
                $('#addEditAuthorServerSidePageinModal').modal('show');
                $("#name").focus();              
          	}	
            serverSidePagin.initForm("create-author-server-side-pagin-form");
      },
      submitAuthorForm:function(){
    	  serverSidePagin.createBookAuthor();
      },
      createBookAuthor: function () {
    	    $('#create-author-server-side-pagin-form').submit(function(e) {
    	    	$.ajax({
    				 url:'save',
    				 type:'POST',
    				 data:$("#create-author-server-side-pagin-form").serialize(),
    				 success: function(data){
    					 serverSidePagin.resetForm();
    					 serverSidePagin.getDataTableObj().draw();
    					 $('#addEditAuthorServerSidePageinModal').modal('hide');
    					  if (data.isError == false) {
    	                      showSuccessMsg(data.message);
    	                  } else {
    	                      showErrorMsg(data.message);
    	                  }
    				 }
    				 
    			 });
    			 e.preventDefault();
    	    });
      },
      
      deleteBookAuthor:function(){
      	var bookAuthorObj = serverSidePagin.getDataTableObj();
    	var dtLength = app.getTableLength(bookAuthorObj);
      	if(dtLength==0){
      		 showErrorMsg("Must select a author for this action");
      	}else{   		 
      		var authorId = app.getTableSelectRow(bookAuthorObj,'id');
      		serverSidePagin.removeAuthor(authorId);
      	}
    	 
      },
      removeAuthor:function(authorId){
	        var confirmDel = confirm("Are you sure?");
	        if (confirmDel == true) {
	         	$.ajax({
	   			 url:'delete/'+authorId,
	   			 type:'POST',
	   			 success: function(data){
	   				serverSidePagin.getDataTableObj().draw();
	   				  if (data.isError == false) {
	                         showSuccessMsg(data.message);
	                     } else {
	                         showErrorMsg(data.message);
	                   }
	   			 }
	   			 
	   		 });
	        } 
      },
      
      getDataTableObj:function(){
    	  return serverSidePagin.dataTableObj;
      },
      resetForm:function(){
          $("#id").val("");
          $("#name").val("");
          $("#gender").val("");
          $('#country').val("");
          $("#form-submit-btn").html("Save Autor");
      },
      celcelBtn:function(){
    	  serverSidePagin.resetForm();  
 		 $('#addEditAuthorServerSidePageinModal').modal('hide');
      }
    	
}

$( document ).ready(function() {
	serverSidePagin.initView();
		
});
