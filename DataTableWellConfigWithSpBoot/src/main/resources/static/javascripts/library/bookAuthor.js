
$(document).ready(function(){

	
var	myTable = $('#myTable').DataTable({
	
					    ajax: {
					        url: 'list',
					        dataSrc: 'data'
					    },
					    columns: [
					        { "data": "id" },
				            { "data": "name" },
				            { "data": "gender" },
				            { "data": "country" },
				            {
				    			// add another column not persisted on the server-side
				    			data : null,
				    			// order is not available
				    			orderable : false,
				    			// yet filter should be available through the method
				    			// findAll(DataTablesInput input, Specification<T>
				    			// additionalSpecification)
				    			searchable : false,
				    			render : function(data, type, row) {
				    				return '<a href=""  class="edit-reference" referenceId='+row.id+'>Edit</a> | <a href=""  class="delete-reference" referenceId='+row.id+'>Delete</a>';
				    			}
				            }
					    ]
					});
	


    $("#create-author-form").submit(function(e) {
    	$.ajax({
			 url:'save',
			 type:'POST',
			 data:$("#create-author-form").serialize(),
			 success: function(data){
				 $('#myTable').DataTable().ajax.reload();
				 $('#addEditAuthorModal').modal('hide');
				  if (data.isError == false) {
					
                      showSuccessMsg(data.message);
                  } else {
                      showErrorMsg(data.message);
                  }
			 }
			 
		 });
		 e.preventDefault();
    });
    
    $('#myTable').on('click', 'a.edit-reference', function (e) {
    	   var control = this;
           var referenceId = $(control).attr('referenceId');
           jQuery.ajax({
               type: 'POST',
               dataType: 'JSON',
          	    url:'edit/'+referenceId,
               success: function (data) {          
                   if (data.isError == false) {
                       var authorObj =data.obj;
                       
                       $("#id").val(authorObj.id)
                       $("#name").val(authorObj.name);
                       $("#gender").val(authorObj.gender);
                       $('#country').val(authorObj.country);
                       
                       $("#form-submit-btn").html("Update Autor");
                       $('#addEditAuthorModal').modal('show');
                       $("#name").focus();
                       
                   } else {
                       showErrorMsg(data.message);
                   }
               }
           });
           e.preventDefault();
    });
    
    $('#myTable').on('click', 'a.delete-reference', function (e) {
        var selectRow = $(this).parents('tr');
        var confirmDel = confirm("Are you sure?");
        if (confirmDel == true) {
            var control = this;
            var referenceId = $(control).attr('referenceId');
         	$.ajax({
   			 url:'delete/'+referenceId,
   			 type:'POST',
   			 success: function(data){
   				 $('#myTable').DataTable().ajax.reload();
   				  if (data.isError == false) {
                         showSuccessMsg(data.message);
                     } else {
                         showErrorMsg(data.message);
                     }
   			 }
   			 
   		 });
        }
        e.preventDefault();
    });
    
    
    $('#bookAuthoreTable').on('click', 'a.delete-reference', function (e) {
        var selectRow = $(this).parents('tr');
        var confirmDel = confirm("Are you sure?");
        if (confirmDel == true) {
            var control = this;
            var referenceId = $(control).attr('referenceId');
         	$.ajax({
   			 url:'delete/'+referenceId,
   			 type:'POST',
   			 success: function(data){
   			
   				  if (data.isError == false) {
                         showSuccessMsg(data.message);
                     } else {
                         showErrorMsg(data.message);
                     }
   			 }
   			 
   		 });
        }
        e.preventDefault();
    });
    
    $('#bookAuthoreTable').on('click', 'a.edit-reference', function (e) {
        var control = this;
        var referenceId = $(control).attr('referenceId');
        jQuery.ajax({
            type: 'POST',
            dataType: 'JSON',
       	    url:'edit/'+referenceId,
            success: function (data) {          
                if (data.isError == false) {
                    var authorObj =data.obj;
                    
                    $("#id").val(authorObj.id)
                    $("#name").val(authorObj.name);
                    $("#gender").val(authorObj.gender);
                    $('#country').val(authorObj.country);
                    
                    $("#form-submit-btn").html("Update Autor");
                    $('#addEditAuthorModal').modal('show');
                    $("#name").focus();
                    
                } else {
                    showErrorMsg(data.message);
                }
            }
        });
        e.preventDefault();

    });
    
    
	
	
	
	
	
});