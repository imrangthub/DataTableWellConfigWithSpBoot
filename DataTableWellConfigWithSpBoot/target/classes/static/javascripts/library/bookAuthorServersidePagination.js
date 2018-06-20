
$(document).ready(function(){

	
	var	serverSidePaginationTable = $('#serverSidePaginationTable').DataTable({
//								        "sDom": "<'row'<'col-md-3 category-filter-holder'><'col-md-3 status-filter-holder'><'col-md-6'f>r>t<'row'<'col-md-3'l><'col-md-3'i><'col-md-6'p>>",
								        "bAutoWidth": true,
								        "scrollX": false,
								        "bServerSide": true,
								        "iDisplayLength": 25,
								        "aaSorting": [0, 'desc'],
								        "sAjaxSource": "list",
//								        "fnServerParams": function (aoData) {
//								            aoData.push({"name": "gender", "value": $('#genderFilter').val()});
//								        },
								        "fnRowCallback": function (nRow, aData, iDisplayIndex) {
								            if (aData.DT_RowId == undefined) {
								                return true;
								            }
								            $('td:eq(5)', nRow).html(getGridActionBtns(nRow, aData, 'editBook,delete'));
								            return nRow;
								        },
								
								        "aoColumns": [
								            null,
								            null,
								            null,
								            null,
								            {"bSortable": false}
								        ]
                                  });
	
	
	
	
//    $('#genderFilter').on('change', function (e) {
//		 $('#serverSidePaginationTable').DataTable().ajax.reload();    
//		 
//    });
		
	    $("#create-author-form").submit(function(e) {
	    	$.ajax({
				 url:'save',
				 type:'POST',
				 data:$("#create-author-form").serialize(),
				 success: function(data){
					 $('#serverSidePaginationTable').DataTable().ajax.reload();
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
	    
	    
	    $('#serverSidePaginationTable').on('click', 'a.edit-reference', function (e) {
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
	    
	    $('#serverSidePaginationTable').on('click', 'a.delete-reference', function (e) {
	        var selectRow = $(this).parents('tr');
	        var confirmDel = confirm("Are you sure?");
	        if (confirmDel == true) {
	            var control = this;
	            var referenceId = $(control).attr('referenceId');
	         	$.ajax({
	   			 url:'delete/'+referenceId,
	   			 type:'POST',
	   			 success: function(data){
	   				 $('#serverSidePaginationTable').DataTable().ajax.reload();
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
	    
	

});