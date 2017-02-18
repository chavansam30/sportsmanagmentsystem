<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html SYSTEM "about:legacy-compat" PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
 

 
<script type="text/javascript" src="js/material.min.js"></script> 

</head>
<body>
<div class="heading">
		<label>Dervan Sport Event</label>
	</div>

<script  type="text/javascript">
var memberlist=[];
$(document).ready(function(){
		
		var noofmember=0;
	 	var dialog = document.querySelector('#teamnameDialog');
	    var showDialogButton = document.querySelector('#show-dialog');
	    if (!dialog.showModal) {
	      dialogPolyfill.registerDialog(dialog);
	    }
	    showDialogButton.addEventListener('click', function() {
	      dialog.showModal();
	    });
	    dialog.querySelector('.ok').addEventListener('click', function() {
	    	document.querySelector('.inputteamname').innerHTML=document.querySelector('#teamName').value+ " OF " +document.querySelector('#schoolName').value;
	    	dialog.close();
		});
	    dialog.querySelector('.close').addEventListener('click', function() {
	      dialog.close();
	    });
	    var isedited=false;
	    var iseditedid=0;
	    var newMemberdialog = document.querySelector('#newmemeber');
	    var newmemberButton = document.querySelector('.addmember');
	    if (!newMemberdialog.showModal) {
	      dialogPolyfill.registerDialog(newMemberdialog);
	    }
	    newmemberButton.addEventListener('click', function() {
	    	editorinsert();
	    	newMemberdialog.showModal();
	    });
	    newMemberdialog.querySelector('.ok').addEventListener('click', function() {
	    	var numbers = /^[0-9]+$/;
	        if(document.getElementById('day').selectedIndex != '0' && document.getElementById('month').selectedIndex != '0' && document.getElementById('year').selectedIndex != '0' && document.getElementById("phoneText").value.match(numbers) && document.getElementById("pincodeText").value.match(numbers) && document.getElementById("emergencyphone").value.match(numbers))
	        {	        
		        addMember();
		    	newMemberdialog.close();	    	
	        	return true;
	        }
	        else
	        {
	        
	         //alert(document.getElementById('day').selectedIndex);
        	 if(document.getElementById('day').selectedIndex == '0' || document.getElementById('month').selectedIndex == '0' || document.getElementById('year').selectedIndex == '0'){
        		 alert('Please select valid date of birth.');
 	        	document.getElementById("day").focus();
        	 }
	        	 
	        if(!document.getElementById("emergencyphone").value.match(numbers)){
	        	alert('Please input numeric values only in Emergency Phone');
	        	document.getElementById("emergencyphone").focus();
	        }
	        
	        if(!document.getElementById("phoneText").value.match(numbers)){
	        	alert('Please input numeric values only in Phone.');
	        	document.getElementById("phoneText").focus();
	        }
	        

       	 	if(!document.getElementById("pincodeText").value.match(numbers)){
	        	alert('Please input numeric values only in Pincode');
	        	document.getElementById("pincodeText").focus();
	        }
       	 
	        return false;
	        }
	        
	    	
		});
	    newMemberdialog.querySelector('.close').addEventListener('click', function() {

	    	newMemberdialog.close();
	    });
	    editorinsert=function(obj){
	    	var id;
	    	if(obj!=undefined){
	    		id=obj['id'];
	    	}
	    	$('#fistnameText').val((obj!=undefined)?obj["fistnameText"]:'');
	    	$('#middlenameText').val((obj!=undefined)?obj["middlenameText"]:'');
	    	$('#lastnameText').val((obj!=undefined)?obj["lastnameText"]:''); 
	    	//$('#day').val((obj!=undefined)?obj["day"]:'');
	    	//$('#month').val((obj!=undefined)?obj["month"]:'');
	    	//$('#year').val((obj!=undefined)?obj["year"]:'');
	    	$('#ageText').text((obj!=undefined)?obj["ageText"]:'');
	    	$('#genderParti').val((obj!=undefined)?obj["genderParti"]:'') ;
	    	$('#addressText').val((obj!=undefined)?obj["addressText"]:'');
	    	$('#stateText').val((obj!=undefined)?obj["stateText"]:'');
	    	$('#cityText').val((obj!=undefined)?obj["cityText"]:'');
	    	$('#pincodeText').val((obj!=undefined)?obj["pincodeText"]:'') 
	    	$('#phoneText').val((obj!=undefined)?obj["phoneText"]:'');
	    	$('#emergencyphone').val((obj!=undefined)?obj["emergencyphone"]:'');
	    	$('#emailText').val((obj!=undefined)?obj["emailText"]:'');
	    }
	    
	    addMember=function(){
	    	noofmember=noofmember+1
	    	var arr={};
	    	if(isedited){
	    		$.each(memberlist,function(e,i){
	    			if(memberlist[e]['id']==iseditedid){
	    				arr=i;
	    			}
	    		});
	    		
	    	}else{
	    		arr["id"]=noofmember;
	    	}
	    	arr["fistnameText"]=$('#fistnameText').val();
	    	arr["middlenameText"]=$('#middlenameText').val();
	    	arr["lastnameText"]=$('#lastnameText').val(); 
	    	arr["day"]=$('#day').val();
	    	arr["month"]=$('#month').val();
	    	arr["year"]=$('#year').val();
	    	arr["ageText"]=$('#ageText').text();
	    	arr["genderParti"]=$('#genderParti').val() ;
	    	arr["addressText"]=$('#addressText').val();
	    	arr["stateText"]=$('#stateText').val();
	    	arr["cityText"]=$('#cityText').val();
	    	arr["pincodeText"]=$('#pincodeText').val() 
	    	arr["phoneText"]=$('#phoneText').val();
	    	arr["emergencyphone"]=$('#emergencyphone').val();
	    	arr["emailText"]=$('#emailText').val();
	    	if(isedited){
	    		$('.tablerow[memberrow="'+iseditedid+'"]').html('<div class="mdl-cell mdl-cell--3-col"></div><div class="mdl-cell mdl-cell--2-col"><button class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-js-ripple-effect  mdl-button--accent delete"><i class="material-icons">delete</i></button><button class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-js-ripple-effect  mdl-button--accent edit"><i class="material-icons">edit</i></button></div><div class="mdl-cell mdl-cell--3-col">'+arr["fistnameText"]+' '+arr["middlenameText"]+' '+arr["lastnameText"]+'</div><div class="mdl-cell mdl-cell--2-col">'+arr["ageText"]+'</div><div class="mdl-cell mdl-cell--2-col"></div>')
	    	}else{
	    	memberlist.push(arr);
	    	var trHTML='<div class="mdl-grid tablerow" memberrow="'+noofmember+'"><div class="mdl-cell mdl-cell--3-col"></div><div class="mdl-cell mdl-cell--2-col"><button class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-js-ripple-effect  mdl-button--accent delete"><i class="material-icons">delete</i></button><button class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-js-ripple-effect  mdl-button--accent edit"><i class="material-icons">edit</i></button></div><div class="mdl-cell mdl-cell--3-col">'+arr["fistnameText"]+' '+arr["middlenameText"]+' '+arr["lastnameText"]+'</div><div class="mdl-cell mdl-cell--2-col">'+arr["ageText"]+'</div><div class="mdl-cell mdl-cell--2-col"></div></div>';
	    	$(".memberlist").append(trHTML);
	    	}
	    	$('.edit').off('click').on('click',function(){
	    		var id=$(this).parents('.tablerow').attr('memberrow');
	    		iseditedid=id;
	    		var temp;
	    		$.each(memberlist,function(e,i){
	    			if(memberlist[e]['id']==id){
	    				temp=i;
	    			}
	    		});
	    		isedited=true;
	    		editorinsert(temp);
	    		newMemberdialog.showModal();
	    		return false;
	    	});
	    	$('.delete').off('click').on('click',function(){
	    		var id=$(this).parents('.tablerow').attr('memberrow');
	    		console.log(id);
	    		var temp=[];
	    		$.each(memberlist,function(e,i){
	    			if(memberlist[e]['id']!=id){
	    				temp.push(i);
	    			}
	    		});
	    		memberlist=temp;
	    		$(this).parents('.tablerow').remove();
	    		return false;
	    	});
	    	isedited=false;
	    }
	    
	    
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
		     	options += '<option value="NotSelected"></option>';
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
	
	
	
	
	
	
	$("#captainlist").change(function(){
		 if($(this).val()!="NotSelected"){			 
			 //alert("Text" + $('#captainlist').text());
	    	    //alert("Value"  + $('#captainlist').val());	    	    
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
	
	/*
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
	var picker = new MaterialDatetimePicker({})
	.on('submit', function(d) {
	  output.innerText = d;
	});
	    */	
	   $('#p2').hide();
	    $('.data').hide();
	    $('.searching').off('click').on('click',function(){
	    	$('#p2').show();
	    	//setTimeout(function(){},5000);
	    	
	    	if(document.getElementById('category').selectedIndex == '0' || document.getElementById('age').selectedIndex == '0' || document.getElementById('gender').selectedIndex == '0'){
	    		alert("Please select Category,Gender and Age.");
	    	}else{

				$.ajax({
			       type: "post",
			       url:"/SportManagmentSystem/TeamEntryServlet",
			       data:"dataSend=searchBtn&&category="+$("#category").val()+"&&age="+$("#age").val()+"&&gender="+$("#gender").val(),      
			       success:function(data, textStatus, jqXHR)
				      {
			    		var e=jQuery.parseJSON(data);
					    var options = '';
				     	options += '<option value="NotSelected"></option>';
						if(e!=undefined && e!=null && e!="" ){
							$.each(e, function(key, value){
								options += '<option value="' + key + '">' + value + '</option>';
							});		     	
						}
				     $("#captainlist").html(options);
				     $('#p2').fadeOut('slow',function(){
			    			$('.data').fadeIn('slow');	
				     });
				      },		      
				      error:function(xhr,status){
					        console.log(status);
					 }
			});	
	    	}
		    	
	    	return false;
	    });
	    var day;
	    var month;
	    var year;
	    $("#day").change(function(){
	    	day=$(this).val();
	    	calAge();
	    });
	    $("#month").change(function(){
	    	month=$(this).val();
	    	calAge();
	    });
	    $("#year").change(function(){
	    	year=$(this).val();
	    	calAge();
	    });


	    calAge =function(e){
	    		let d=31;
	    		 var valideDate=false;
	    		if(!isNaN(day) && !isNaN(month)&&!isNaN(year)){
	    			if(month=='February'){
	    			 if((this.isLeapyear() && day<30)||day<29){
	    				 d=28;
	    				 if(this.isLeapyear()){
	    					d=28;	 
	    				 }
	    				 valideDate=true;
	    			 }
	    			}else if(month=='April' || month=='June' ||month=='September' || month=='November' ){
	    				if(day<31){
	    				 valideDate=true;
	    				 d=31;	 
	    			 }
	    			}else{
	    				valideDate=true;
	    			}
	    			if(valideDate){
	    				console.log(month+'/'+ day+'/'+year);
	    				this.dob=new Date(month+'/'+ day+'/'+year);
	    				console.log(this.dob);
	    				let ageDifMs = new Date('3/15/2017') - new Date(month+'/'+ day+'/'+year).getTime();
	    				let ageDate = new Date(ageDifMs);
	    				dayofbirth=d-ageDate.getDate();
	    				months=12-(ageDate.getMonth()+1);
	    				var yrs=Math.abs(ageDate.getUTCFullYear() - 1970);
	    				$('#ageText').text(yrs);
	    				valideDate=false;
	    			}
	    			
	    		}
	    		
	    	}
	    for(var i=1;i<32;i++){$('#day').append('<option value="'+i+'">'+i+'</option>')}
	    for(var i=1;i<13;i++){$('#month').append('<option value="'+i+'">'+i+'</option>')}
	    for(var i=1970;i<2017;i++){$('#year').append('<option value="'+i+'">'+i+'</option>')}
	    $('.saveTeam').off('submit').on('click',function(){
	    	
	    	if(document.getElementById("captainlist").selectedIndex == '0' || document.getElementById('teamName').value.length == '0' || document.getElementById('schoolName').value.length == '0' || memberlist.length == '0'){
	    		alert("something wrong");
	    		if(memberlist.length == '0'){
	    			alert("Please add team members.");
	    			document.getElementById('addmemberspan').focus();
	    		}
	    		
	    		if(document.getElementById("teamName").value.length == '0' || document.getElementById("schoolName").value.length == '0'){
	    			alert("Please add Team Name and School Name.")
		    		document.getElementById('show-dialog').focus();	
	    		}
	    		
	    		if(document.getElementById("captainlist").selectedIndex == '0'){
	    			alert("Please select captain name.")
		    		document.getElementById('captainlist').focus();	
	    		}
	    		
	    		return false;
	    	}else{
	    		
	    		$.ajax({
				    type: "POST",
				    url:"/SportManagmentSystem/TeamEntryServlet",
				    //data: "dataSend=createTeamBtn&&TableData=" + TableData + "&&TeamName=" + $("#teamnameText").val() + "&&TeamSchoolName=" + $("#teamschoolnameText").val(),
				   
				   data: {dataSend:'createTeamBtn',TableData: JSON.stringify(memberlist),TeamName: $("#teamName").val(),TeamSchoolName:$("#schoolName").val(),CaptainId:$('#captainlist').val()} ,
				    success: function(msg){
				        // return value stored in msg variable
				    }		
				});
		    	
		    	return true;
		    	
	    	}
	    	
	    	
	    	
	    });
	    
	    
	    
	    
});

</script>
<form>
    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
        <select class="mdl-textfield__input" id="category">
            <option value=""></option>
        </select>
        <label class="mdl-textfield__label" for="category"></label>
    </div>
    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
        <select class="mdl-textfield__input" id="age">
            <option value=""></option>
        </select>
        <label class="mdl-textfield__label" for="age">Select Age</label>
    </div>
    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
        <select class="mdl-textfield__input" id="gender">
            <option value=""></option>
            <option value="B">B</option>
            <option value="G">G</option>
        </select>
        <label class="mdl-textfield__label" for="gender">Gender</label>
    </div>
    <button class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-js-ripple-effect  
    mdl-button--accent searching" id="tt1">
        <i class="material-icons">search</i>
    </button>
    <div class="mdl-tooltip" data-mdl-for="tt1">
        Search Captian
    </div>

    <div class="clear-fix"></div>
    <div id="p2" class="mdl-progress mdl-js-progress mdl-progress__indeterminate"></div>
    <div class="clear-fix"></div>
    <div class="data">
    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
        <select class="mdl-textfield__input" id="captainlist">
            <option value=""></option>
        </select>	
        <label class="mdl-textfield__label" for="captainlist">Select Captain</label>
    </div>
    <button id="show-dialog" type="button" class="mdl-button">Add Team/School Name</button>
    <div class="clear-fix"></div>
    <div class="inputteamname" id="tt2"></div>
    <div class="mdl-tooltip" data-mdl-for="tt2">
        Team Name
    </div>
     <div class="clear-fix"></div>
    <dialog class="mdl-dialog" id="teamnameDialog">

        <div class="mdl-dialog__content">
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="teamName" maxlength="99">
                <label class="mdl-textfield__label" for="teamName">Team Name</label>
                </div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="schoolName" maxlength="99">
                <label class="mdl-textfield__label" for="schoolName">School Name</label>
            </div>
        </div>
        <div class="mdl-dialog__actions">
            <button type="button" class="mdl-button ok">Ok</button>
            <button type="button" class="mdl-button close">Cancel</button>
        </div>
    </dialog>
    <div class="memberlist">
     <div class="mdl-grid tableHeader">
    <div class="mdl-cell mdl-cell--3-col">
    <span id= "addmemberspan" class="mdl-chip mdl-chip--contact addmember">
   	<img class="mdl-chip__contact" src="<%=request.getContextPath()%>/images/add-user.png"></img>
	 <span class="mdl-chip__text">Add Member</span>
    </span>
    </div>
     <div class="mdl-cell mdl-cell--2-col">
	    Action
    </div>
     <div class="mdl-cell mdl-cell--3-col">
	    Name
    </div>
     <div class="mdl-cell mdl-cell--2-col">
	    Age
    </div>
     <div class="mdl-cell mdl-cell--2-col">
	    
    </div>
    </div>

    </div>

  <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent saveTeam">
 Save Team
</button>
    <dialog class="mdl-dialog" id="newmemeber">

        <div class="mdl-dialog__content">
            <div class="mdl-grid">
                <div class="mdl-cell mdl-cell--4-col">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="text" id="fistnameText" maxlength="49">
                        <label class="mdl-textfield__label" for="fistnameText">Name</label>
                    </div>
                </div>
                <div class="mdl-cell mdl-cell--4-col">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="text" id="middlenameText" maxlength="49">
                        <label class="mdl-textfield__label" for="middlenameText">Father Name</label>
                    </div>
                </div>
                <div class="mdl-cell mdl-cell--4-col">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="text" id="lastnameText" maxlength="49">
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
                        <textarea class="mdl-textfield__input"  rows= "3" id="addressText" maxlength="249"></textarea>
                        <label class="mdl-textfield__label" for="addressText">Address</label>
                    </div>
                </div>
            </div>
        	<div class="mdl-grid">
                <div class="mdl-cell mdl-cell--4-col">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="text" id="stateText" maxlength="2">
                        <label class="mdl-textfield__label" for="stateText">State</label>
                    </div>
                    
                </div>
                <div class="mdl-cell mdl-cell--4-col">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="text" id="cityText" maxlength="19">
                        <label class="mdl-textfield__label" for="cityText">City</label>
                    </div>
                </div>
                <div class="mdl-cell mdl-cell--4-col">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="text" id="pincodeText" maxlength="6">
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
        	<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
	        <select class="mdl-textfield__input" id="genderParti">
	            <option value=""></option>
	            <option value="B">B</option>
	            <option value="G">G</option>
	        </select>
	        <label class="mdl-textfield__label" for="gender">Gender</label>
	   		 </div>
        	<div class="mdl-grid">
                <div class="mdl-cell mdl-cell--4-col">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="text" id="phoneText" maxlength="10">
                        <label class="mdl-textfield__label" for="phoneText">Phone</label>
                    </div>
                </div>
                <div class="mdl-cell mdl-cell--4-col">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="text" id="emergencyphone" maxlength="10">
                        <label class="mdl-textfield__label" for="emergencyphone">Emergency Phone</label>
                    </div>
                </div>
                <div class="mdl-cell mdl-cell--4-col">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="text" id="emailText" maxlength="39">
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


<!-- form>
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

</form-->


</body>
</html>  