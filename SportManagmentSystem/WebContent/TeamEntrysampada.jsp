<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Team Entry Form</title>
<title>Dervar Sport Event</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/material.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/font/material-icons.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>

<script type="text/javascript" src="js/jquery.1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script> 
<script type="text/javascript" src="js/jquery.currency.js"></script> 

<script type="text/javascript" src="<%=request.getContextPath()%>/js/material.min.js"></script> 
</head>
<body>


<div class="heading">
		<label>Dervnar Sport Event</label>
</div>


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
		    	   
		    	   var trHTML = '';	
		    	   $('#tablediv').show();
		            	trHTML += '<tr><td class=\"td\"id=\"fistnameText\" >' + $('#fistnameText').val()+'</td>'+ 
		            	'<td class=\"td\" id=\"lastnameText\">' + $('#lastnameText').val() +
		            	'</td><td class=\"td\" id=\"middlenameText\" >' + $('#middlenameText').val() + 
		            	'</td><td class=\"td\" id=\"dobText\">'+  $('#dobText').val() + 
		            	'</td><td class=\"td\" id=\"ageText\">' + $('#ageText').val() + 
		            	'</td><td class=\"td\" id=\"genderText\" >' + $('#partigender').val() + 
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
	            		
	            		tddobText.html("<input type='text' class= 'dateTimePicker1' id='txtdob' value='"+tddobText.html()+"'/>");
	            		tdageText.html("<input type='text' id='txtage' value='"+tdageText.html()+"'/>");
	            		tdgenderText.html("<input type='text' class='onClickGender' id='txtgender' value='"+tdgenderText.html()+"'/> <select id='tdgenderText' class='onChangeGender' style='visibility:hidden'><option value='select'>Select a gender</option><option  value='B'>B</option><option value='G'>G</option></select>" );

	            		//tdgenderText.html("<select id='tdgeerText' class='onChangeGender'><option value='select'>Select a gender</option><option  value='B'>B</option><option value='G'>G</option></select>");
	            		
	            		tdaddressText.html("<input type='text' id='txtaddress' value='"+tdaddressText.html()+"'/>");
	            		tdstateText.html("<input type='text' id='txtstate' value='"+tdstateText.html()+"'/>");
	            		tdcityText.html("<input type='text' id='txtcity' value='"+tdcityText.html()+"'/>");
	            		pincodeText.html("<input type='text' id='txtpincode' value='"+pincodeText.html()+"'/>");
	            		
	            		tdphoneText.html("<input type='text' id='txtphone' value='"+tdphoneText.html()+"'/>");
	            		tdemgphoneText.html("<input type='text' id='txtemgphone' value='"+tdemgphoneText.html()+"'/>");
	            		tdemailText.html("<input type='text' id='txtemail' value='"+tdemailText.html()+"'/>");
	            		
	            		tdButtons.html("<img src='css/images/save.png' class='btnSave'/>");
	            		
	            		$(".btnSave").bind("click",onSave);
	            		$(".onClickGender").bind("click",changeGender);
	            		$(".onChangeGender").bind("click",selectGender);
	            		
	            		
	            		function changeGender(){
			        		//alert(document.getElementById('tdgenderText'));
			        		document.getElementById('tdgenderText').style.visibility='visible';
			        		document.getElementById('txtgender').style.visibility='hidden';
			        	}
	            		
	            		function selectGender(){
	            			
	            			if($('#tdgenderText').val()=='select'){
	            				alert("select gender");
	            			}else{
	            				document.getElementById('txtgender').value= $('#tdgenderText').val();
				        		document.getElementById('tdgenderText').style.visibility='hidden';
				        		document.getElementById('txtgender').style.visibility='visible';
	            			}
			        		
	            		}
	            		
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
	            		
	            		$("#txtdob").change(function(){
	            			if($(this).val()!=undefined ||$(this).val()!=null){
	            			var start = $('#txtdob').val();
	            	     	
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
	            		 	
	            		 	$('#txtage').val(age);
	            	     }
	            		 
	            		});
	            		
		        	}
		        	
		        	
		        	
		        	function onSave(){
		        		
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
		    data: "dataSend=createTeamBtn&&TableData=" + TableData + "&&TeamName=" + $("#teamnameText").val() + "&&TeamSchoolName=" + $("#teamschoolnameText").val(),
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
		            , "age" :$(tr).find('td:eq(4)').text()
		            , "gender" : $(tr).find('td:eq(5)').text()
		            , "address" : $(tr).find('td:eq(6)').text()
		            , "state" :$(tr).find('td:eq(7)').text()
		            , "city" : $(tr).find('td:eq(8)').text()
		            , "pincode" : $(tr).find('td:eq(9)').text()
		            , "phone" :$(tr).find('td:eq(10)').text()
		            , "emgphone" : $(tr).find('td:eq(11)').text()
		            , "email" : $(tr).find('td:eq(12)').text()
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
<!--  
<div class=container></div>
-->
	<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
        <select class="mdl-textfield__input" id="category">
            <option value=""></option>
        </select>
        <label class="mdl-textfield__label" for="category">Select Category</label>
    </div>
	
	 <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
        <select class="mdl-textfield__input" id="age">
            <option value=""></option>
        </select>
        <label class="mdl-textfield__label" for="age">Select Age</label>
    </div>
    
    <!-- <div class="labels_age"><span>Please select age:</span></div><select id="age"></select> -->
	
	<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
        <select class="mdl-textfield__input" id="gender">
            <option value=""></option>
            <option value="B">B</option>
            <option value="G">G</option>
        </select>
        <label class="mdl-textfield__label" for="gender">Gender</label>
    </div>
    
    <!-- 
	<div class="labels_gender"><span>Please select gender:</span></div><select id="gender">
	<option value="select">Select a gender</option> 
	<option  value="B">B</option> 
	<option value="G">G</option> 
	</select>
	 -->
	 
	 <button class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-js-ripple-effect  mdl-button--accent searching" 
	name="searchBtn"  id="searchBtn">
        <i class="material-icons">search</i>
    </button>
    <div class="mdl-tooltip" data-mdl-for="tt1">
        Search Captian
    </div>
    
    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
        <select class="mdl-textfield__input" id="captainlist">
            <option value=""></option>
        </select>
        <label class="mdl-textfield__label" for="captainlist">Select Captain</label>
    </div>
    
    
    
    <!-- 
<div class="buttondiv">
		<input type="button" name="searchBtn" id="searchBtn" value="Search Captain">
</div>
 -->
 
 <div class="clear-fix"></div>
    <div id="p2" class="mdl-progress mdl-js-progress mdl-progress__indeterminate"></div>
    <div class="clear-fix"></div>
    <div class="data">
    
     <div class="mdl-dialog__content">
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="teamnameText" value="enter team name">
                <label class="mdl-textfield__label" for="teamnameText">Enter Team Name:</label>
            </div>
        </div>
        

    <div class="mdl-dialog__content">
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="teamschoolnameText" value="enter team name">
                <label class="mdl-textfield__label" for="teamschoolnameText">Enter Team Name:</label>
            </div>
        </div>

       
       <!--  
        <div class="labels_category"><span>Enter Team School Name:</span></div>
<input type="text" class="middlename"  id="teamschoolnameText" value="enter team school name">	
 -->

        
        
 <!--   
<div class="labels_category"><span>Enter Team Name:</span></div>
<input type="text" class="middlename"  id="teamnameText" value="enter team name">	
-->


    <!-- <select id="captainlist"></select> -->

    <button name="createTeamBtn" id="createTeamBtn" type="button" class="mdl-button">Create Team Name</button>
    <div class="clear-fix"></div>
    <div class="inputteamname" id="tt2"></div>
    <div class="mdl-tooltip" data-model-for="tt2">
        Team Name
    </div>
     <div class="clear-fix"></div>
     
<!-- 	
<div class="buttondiv">
		<input type="button" name="createTeamBtn" id="createTeamBtn" value="Create Team">
</div>
 -->


	
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
<div class="labels_category"><span>Gender:</span></div><select id="partigender">
	<option value="select">Select a gender</option> 
	<option  value="B">B</option> 
	<option value="G">G</option> 
	</select>
<div class="labels_category"><span>Address:</span></div><input type="text" class="addressline"  id="addressText" value="sampada">
<div class="labels_category"><span>State:</span></div><input type="text" class="state"  id="stateText" value="sampada">
<div class="labels_category"><span>City:</span></div><input type="text" class="city"  id="cityText" value="sampada">
<div class="labels_category"><span>Pincode:</span></div><input type="text" class="pincode"  id="pincodeText" value="sampada">
<div class="labels_category"><span>Phone:</span></div><input type="text" class="phone"  id="phoneText" value="sampada">
<div class="labels_category"><span>Emergency Phone:</span></div><input type="text" class="emergencyphone"  id="emergencyPhoneText" value="sampada">
<div class="labels_category"><span>Email:</span></div><input type="text" class="email"  id="emailText" value="sampada">


<!-- 
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
 -->




    <dialog class="mdl-dialog" id="tablediv">

        <div class="mdl-dialog__content" id="partitable">
            <div class="mdl-grid">
                <div class="mdl-cell mdl-cell--4-col">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="text" id="fistnameText">
                        <label class="mdl-textfield__label" for="fistnameText">Name</label>
                    </div>
                </div>
                <div class="mdl-cell mdl-cell--4-col">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="text" id="middlenameText">
                        <label class="mdl-textfield__label" for="middlenameText">Father Name</label>
                    </div>
                </div>
                <div class="mdl-cell mdl-cell--4-col">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="text" id="lastnameText">
                        <label class="mdl-textfield__label" for="lastnameText">Surname</label>
                    </div>
                </div>
            </div>
        	<label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="checkbox-2">
  				<input type="checkbox" id="checkbox-2" class="mdl-checkbox__input">
  				<span class="mdl-checkbox__label">Same as Captain</span>
			</label>
        	<div class="mdl-grid">
                <div class="mdl-cell mdl-cell--12-col">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <textarea class="mdl-textfield__input"  rows= "3" id="addressText" ></textarea>
                        <label class="mdl-textfield__label" for="addressText">Address</label>
                    </div>
                </div>
            </div>
        	<div class="mdl-grid">
                <div class="mdl-cell mdl-cell--4-col">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="text" id="stateText">
                        <label class="mdl-textfield__label" for="stateText">State</label>
                    </div>
                    
                </div>
                <div class="mdl-cell mdl-cell--4-col">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="text" id="cityText">
                        <label class="mdl-textfield__label" for="cityText">City</label>
                    </div>
                </div>
                <div class="mdl-cell mdl-cell--4-col">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="text" id="pincodeText">
                        <label class="mdl-textfield__label" for="pincodeText">Pin Code</label>
                    </div>
                </div>
            </div>
        	<div class="mdl-grid dobvals" >
                <div class="mdl-cell mdl-cell--6-col">
                   <label>Date of Birth</label>
                   <select id="day"><option value="0">DD<option></select>
                   <select id="month"><option value="0">MM<option></select>
                   <select id="year"><option value="0">YYYY<option></select>
                </div>
                <div class="mdl-cell mdl-cell--6-col">
                        Age:
                        <span id="ageText"></span>
                </div>
               
            </div>
        	
        	<div class="mdl-grid">
                <div class="mdl-cell mdl-cell--4-col">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="text" id="phoneText">
                        <label class="mdl-textfield__label" for="phoneText">Phone</label>
                    </div>
                </div>
                <div class="mdl-cell mdl-cell--4-col">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="text" id="emergencyphone">
                        <label class="mdl-textfield__label" for="emergencyphone">Emergency Phone</label>
                    </div>
                </div>
                <div class="mdl-cell mdl-cell--4-col">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="text" id="emailText">
                        <label class="mdl-textfield__label" for="emailText">Email</label>
                    </div>
                </div>
            </div>
        	
        </div>
        <div class="mdl-dialog__actions">
            <button type="button" class="mdl-button ok">Add</button>
            <button type="button" class="mdl-button close">Cancel</button>
        </div>
    </dialog>
    
    



</div>
</form>


</body>
</html>  