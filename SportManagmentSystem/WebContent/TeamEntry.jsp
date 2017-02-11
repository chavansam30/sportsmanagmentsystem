<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Team Entry Form</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>

<script type="text/javascript" src="js/jquery.1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script> 
<script type="text/javascript" src="js/jquery.currency.js"></script> 

</head>
<body>


<script  type="text/javascript">
$(document).ready(function(){
	
	
	$.ajax({
		type:"post",
		url:"/SportManagmentSystem/TeamEntryServlet",
		data:{dataSend:"category"},  
		datatype:"json",
		 success:function(data, textStatus, jqXHR)
         {
	    	var e=jQuery.parseJSON(data);	          
	    	console.log("category"+e);    
	        var options = '';
	        options += '<option value="NotSelected">Select the Game Category</option>';
			 if(e!=undefined && e!=null && e!="" ){ 
				$.each(e, function(key, value){
					options += '<option value="' + key + '">' + value + '</option>';
				});
			}
	        $("#category").html(options);	        
	        
				     
         },
         error:function(xhr,status){
             console.log(status);
      }
	});
	
	
	$("#category").change(function(){
		 if($(this).val()!="NotSelected"){
			$.ajax({
			       type: "post",
			       url:"/SportManagmentSystem/TeamEntryServlet",
			       data:"dataSend=age&&category="+$(this).val(),   
			       
			       success:function(data, textStatus, jqXHR)
		      {
			   	var e=jQuery.parseJSON(data);
			    var options = '';
		     	options += '<option value="NotSelected">Select Age</option>';
				if(e!=undefined && e!=null && e!="" ){
					$.each(e, function(key, value){
						options += '<option value="' + key + '">' + value + '</option>';
					});		     	
				}
		     $("#age").html(options);
		 },
		 error:function(xhr,status){
		        console.log(status);
		 }
		 });
		}	
	 });
	
	
	$("#searchBtn").click(function(){
		$.ajax({
		       type: "post",
		       url:"/SportManagmentSystem/TeamEntryServlet",
		       data:"dataSend=searchBtn&&category="+$("#category").val()+"&&age="+$("#age").val()+"&&gender="+$("#gender").val(),      
		       success:function(data, textStatus, jqXHR)
			      {
		    		var e=jQuery.parseJSON(data);
				    var options = '';
			     	options += '<option value="NotSelected">Select Captain</option>';
					if(e!=undefined && e!=null && e!="" ){
						$.each(e, function(key, value){
							options += '<option value="' + key + '">' + value + '</option>';
						});		     	
					}
			     $("#captainlist").html(options);
			      },		      
			      error:function(xhr,status){
				        console.log(status);
				 }
		});
	});
	
	$("#captainlist").change(function(){
		 if($(this).val()!="NotSelected"){			 
			 //alert($('#captainlist').text());
	    	    //alert($('#captainlist').val());	    	    
			$.ajax({
			       type: "post",
			       url:"/SportManagmentSystem/TeamEntryServlet",
			       data:"dataSend=age&&category="+$(this).val(),   			       
			       success:function(data, textStatus, jqXHR)
		      {			    	  
		 },
		 error:function(xhr,status){
		        console.log(status);
		 }
		 });
		}	
	 });
	
	
	$("#addBtn").click(function(){
		    	   var optionCat ='';
		    	   var trHTML = '';	
		    	   $('#tablediv').show();
		            	trHTML += '<tr><td class=\"td\"id=\"fistnameText\" >' + $('#fistnameText').val()+'</td>'+ 
		            	'<td class=\"td\" id=\"lastnameText\">' + $('#lastnameText').val() +
		            	'</td><td class=\"td\" id=\"middlenameText\" >' + $('#middlenameText').val() + 
		            	'</td><td class=\"td\" id=\"dobText\">'+  $('#dobText').val() + 
		            	'</td><td class=\"td\" id=\"ageText\">' + $('#ageText').val() + 
		            	'</td><td class=\"td\" id=\"genderText\" >' + $('#genderText').val() + 
		            	'</td><td class=\"td\" id=\"addressText\" >' + $('#addressText').val() + 
		            	'</td><td class=\"td\" id=\"stateText\">' + $('#stateText').val() + 
		            	'</td><td class=\"td\" id=\"cityText\">' + $('#cityText').val() + 
		            	'</td><td class=\"td\" id=\"pincodeText\">' + $('#pincodeText').val() + 
		            	'</td><td class=\"td\" id=\"phoneText\">' + $('#phoneText').val() + 
		            	'</td><td class=\"td\"  id=\"emergencyPhoneText\">' + $('#emergencyPhoneText').val() + 
		            	'</td><td class=\"td\" id=\"emailText\">' + $('#emailText').val() + 
		            	'</td><td class=\"td\">'+"<img src='css/images/edit.png' class='btnEdit'/>"+
		            	"<img src='css/images/delete.png' class='btnDelete'/>"+
		            	'</td></tr>';
		            $("#partitable").append(trHTML);
		            
		            
		            $(".btnEdit").bind("click",onEdit);
		        	$(".btnDelete").bind("click",onDelete);
		        	
		        	
		        	function onEdit(){
		        			        		
		        		var par = $(this).parent().parent();
            			
	            		var tdfistnameText= par.children("td:nth-child(1)"); 
	            		var tdmiddlenameText = par.children("td:nth-child(2)"); 
	            		var tdlastnameText = par.children("td:nth-child(3)"); 
	            		
	            		var tddobText = par.children("td:nth-child(4)");
	            		var tdageText = par.children("td:nth-child(5)");
	            		var tdgenderText = par.children("td:nth-child(6)");
	            		
	            		var tdaddressText = par.children("td:nth-child(7)");
	            		var tdstateText = par.children("td:nth-child(8)");
	            		var tdcityText = par.children("td:nth-child(9)");
	            		var pincodeText = par.children("td:nth-child(10)");
	            		
	            		var tdphoneText = par.children("td:nth-child(11)");
	            		var tdemgphoneText = par.children("td:nth-child(12)");
	            		var tdemailText = par.children("td:nth-child(13)");
	            		
	            		var tdButtons = par.children("td:nth-child(14)"); 
	            		
	            		tdfistnameText.html("<input type='text' id='txtFName' value='"+tdfistnameText.html()+"'/>");
	            		tdmiddlenameText.html("<input type='text' id='txtMName' value='"+tdmiddlenameText.html()+"'/>");
	            		tdlastnameText.html("<input type='text' id='txtLName' value='"+tdlastnameText.html()+"'/>");
	            		
	            		tddobText.html("<input type='text' id='txtdob' value='"+tddobText.html()+"'/>");
	            		tdageText.html("<input type='text' id='txtage' value='"+tdageText.html()+"'/>");
	            		tdgenderText.html("<input type='text' id='txtgender' value='"+tdgenderText.html()+"'/>");

	            		tdaddressText.html("<input type='text' id='txtaddress' value='"+tdaddressText.html()+"'/>");
	            		tdstateText.html("<input type='text' id='txtstate' value='"+tdstateText.html()+"'/>");
	            		tdcityText.html("<input type='text' id='txtcity' value='"+tdcityText.html()+"'/>");
	            		pincodeText.html("<input type='text' id='txtpincode' value='"+pincodeText.html()+"'/>");
	            		
	            		tdphoneText.html("<input type='text' id='txtphone' value='"+tdphoneText.html()+"'/>");
	            		tdemgphoneText.html("<input type='text' id='txtemgphone' value='"+tdemgphoneText.html()+"'/>");
	            		tdemailText.html("<input type='text' id='txtemail' value='"+tdemailText.html()+"'/>");
	            	
	            		
	            		tdButtons.html("<img src='css/images/save.png' class='btnSave'/>");
	            		
	            		$(".btnSave").bind("click",onSave);
	            			            		
		        		
		        	}
		        	
		        	
		        	function onSave(){
		        		alert("Save me");
		        		
	        			var par = $(this).parent().parent();
            			
	            		var tdfistnameText= par.children("td:nth-child(1)"); 
	            		var tdmiddlenameText = par.children("td:nth-child(2)"); 
	            		var tdlastnameText = par.children("td:nth-child(3)"); 
	            		
	            		var tddobText = par.children("td:nth-child(4)");
	            		var tdageText = par.children("td:nth-child(5)");
	            		var tdgenderText = par.children("td:nth-child(6)");
	            		
	            		var tdaddressText = par.children("td:nth-child(7)");
	            		var tdstateText = par.children("td:nth-child(8)");
	            		var tdcityText = par.children("td:nth-child(9)");
	            		var pincodeText = par.children("td:nth-child(10)");
	            		
	            		var tdphoneText = par.children("td:nth-child(11)");
	            		var tdemgphoneText = par.children("td:nth-child(12)");
	            		var tdemailText = par.children("td:nth-child(13)");
	            		
	            		var tdButtons = par.children("td:nth-child(14)"); 
	            		
	            		tdName.html(tdName.children("input[type=text]").val());
	            		
	            		tdfistnameText.html(tdfistnameText.children("input[type=text]").val());
	            		tdmiddlenameText.html(tdmiddlenameText.children("input[type=text]").val());
	            		tdlastnameText.html(tdlastnameText.children("input[type=text]").val());
	            		
	            		tddobText.html(tddobText.children("input[type=text]").val());
	            		tdageText.html(tdageText.children("input[type=text]").val());
	            		tdgenderText.html(tdgenderText.children("input[type=text]").val());

	            		tdaddressText.html(tdaddressText.children("input[type=text]").val());
	            		tdstateText.html(tdstateText.children("input[type=text]").val());
	            		tdcityText.html(tdcityText.children("input[type=text]").val());
	            		pincodeText.html(pincodeText.children("input[type=text]").val());
	            		
	            		tdphoneText.html(tdphoneText.children("input[type=text]").val());
	            		tdemgphoneText.html(tdemgphoneText.children("input[type=text]").val());
	            		tdemailText.html(tdemailText.children("input[type=text]").val());
	            	
	            		
	            		tdButtons.html("<img src='css/images/edit.png' class='btnEdit'/><img src='css/images/delete.png' class='btnDelete'/>");
	            		
	    	            $(".btnEdit").bind("click",onEdit);
			        	$(".btnDelete").bind("click",onDelete);

	            		
	            		
		        	}
		        	
		        	function onDelete(){
		        		//alert("Delete me");
		        		var par = $(this).parent().parent(); //tr
		        		par.remove();
		        	}
	});
	
	
	
	
	$("#createTeamBtn").click(function(){
		
		var TableData;
		TableData = storeTblValues();
		
		//TableData = $.toJSON(TableData);
		TableData = JSON.stringify(TableData);
		alert(TableData);
		
		$.ajax({
		    type: "POST",
		    url:"/SportManagmentSystem/TeamEntryServlet",
		    // data :"dataSend=age&&category="+$(this).val(),   
		    data: "dataSend=createTeamBtn&&TableData=" + TableData,
		   	//data:{json :TableData}, 
		   success: function(msg){
		        // return value stored in msg variable
		    }
		
		});
		
		function storeTblValues()
		{
		    var TableData = new Array();

		    $('#partitable tr').each(function(row, tr){
		        TableData[row]={
		            "firstname" : $(tr).find('td:eq(0)').text()
		            , "middlename" :$(tr).find('td:eq(1)').text()
		            , "lastname" : $(tr).find('td:eq(2)').text()
		            , "dob" : $(tr).find('td:eq(3)').text()
		        } ;   
		    }); 
		    TableData.shift();  // first row will be empty - so remove
		    return TableData;
		}
	});
	
	
	
	
	$("#dobText").change(function(){
		if($(this).val()!=undefined ||$(this).val()!=null){
		var start = $('#dobText').val();
     	
	 	var date = new Date(start);
     	var d = date.getDate();
	 	var m = date.getMonth();
     	var y = date.getFullYear();
	 	
     	var now = new Date();
	 	
		var age = now.getFullYear() - y;
		var mdif = now.getMonth() - m + 1; //0=jan	
		
		if(mdif < 0)
		{
			--age;
		}
		else if(mdif == 0)
		{
			var ddif = now.getDate() - d;
			
			if(ddif < 0)
			{
				--age;
			}
		}
	 	
	 	$('#ageText').val(age);
     }
	 
	});
	
	$(".dateTimePicker1").datepicker({
	    showOn: "button",
	    buttonImage: "css/images/calendar.png",
	    buttonImageOnly: true,
	    buttonText: "",
	    dateFormat:"dd-M-yy", 
	    changeMonth : true,
           changeYear : true,
           yearRange: '-100y:c+nn',
           maxDate: '-1d'
	  });
		
});
</script>
<form>
<div class=container></div>

	<div class="labels_category"><span>Please select category:</span></div><select id="category"></select>
	<div class="labels_age"><span>Please select age:</span></div><select id="age"></select>
	<div class="labels_gender"><span>Please select gender:</span></div><select id="gender">
	<option value="select">Select a gender</option> 
	<option  value="B">B</option> 
	<option value="G">G</option> 
	</select>
	
<div class="buttondiv">
		<input type="button" name="searchBtn" id="searchBtn" value="Search Captain">
</div>
	
<select id="captainlist"></select>	
	
<div class="buttondiv">
		<input type="button" name="addBtn" id="addBtn" value="Add Record">
</div>

<div class="labels_category">
<span>First Name:</span></div>
<input type="text" class="firstname"  id="fistnameText" value="sampada">
<div class="labels_category"><span>Last Name:</span></div><input type="text" class="lastname"  id="lastnameText" value="sampada">
<div class="labels_category"><span>Middle Name:</span></div><input type="text" class="middlename"  id="middlenameText" value="sampada">
<div class="labels_category"><span>Date of Birth:</span></div>
<input type="text" class ='dateTimePicker1' id="dobText" value="sampada">
<div class="labels_category"><span>Age:</span></div><input type="text" class="age"  id="ageText" value="sampada">
<div class="labels_category"><span>Gender:</span></div><input type="text" class="gender"  id="genderText" value="sampada">
<div class="labels_category"><span>Address:</span></div><input type="text" class="addressline"  id="addressText" value="sampada">
<div class="labels_category"><span>State:</span></div><input type="text" class="state"  id="stateText" value="sampada">
<div class="labels_category"><span>City:</span></div><input type="text" class="city"  id="cityText" value="sampada">
<div class="labels_category"><span>Pincode:</span></div><input type="text" class="pincode"  id="pincodeText" value="sampada">
<div class="labels_category"><span>Phone:</span></div><input type="text" class="phone"  id="phoneText" value="sampada">
<div class="labels_category"><span>Emergency Phone:</span></div><input type="text" class="emergencyphone"  id="emergencyPhoneText" value="sampada">
<div class="labels_category"><span>Email:</span></div><input type="text" class="email"  id="emailText" value="sampada">


<div id="tablediv">
<table cellspacing="10px" id="partitable" class="table"> 
    <tr class="tr"> 
    	<th class="th" scope="col">First Name</th>
        <th class="th" scope="col">Middle Name</th> 
        <th class="th" scope="col">Last Name</th> 
        <th class="th" scope="col">Date Of Birth</th> 
        <th class="th" scope="col">Age</th> 
        <th class="th" scope="col">Gender</th> 
        <th class="th" scope="col">Address</th>  
        <th class="th" scope="col">State</th>  
        <th class="th" scope="col">City</th>  
        <th class="th" scope="col">Pincode</th>  
        <th class="th" scope="col">Phone</th>
        <th class="th" scope="col">Emergency Phone</th>
        <th class="th" scope="col">Email</th>       
        <th class="th" scope="col">Action</th>          
    </tr> 
</table>
</div>


<div class="buttondiv">
		<input type="button" name="createTeamBtn" id="createTeamBtn" value="Create Team">
</div>

</form>


</body>
</html>  