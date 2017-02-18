<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Slab</title>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.structure.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.theme.css">

<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="js/jquery.1.7.2.min.js"></script>
<script type="text/javascript" src="js/validationFile.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script> 
<script type="text/javascript" src="js/jquery.currency.js"></script> 

<script>
var global_data;
$('document').ready(function(){
	$("#tablediv").hide();
	$.ajax({
	       type: "post",
	       url:"getSlabDataServlet",
	       data:{dataSend:"legalEntity"},   
	       datatype:"json",
	       success:function(data, textStatus, jqXHR)
         {
	    	   console.log("Legal"+data);
	    	   var e=jQuery.parseJSON(data);
        
    console.log("Legal"+e);    
        var options = '';
        options += '<option value="NotSelected">Select the Legal Entity</option>';
		if(e!=undefined && e!=null && e!="" ){
			$.each(e, function(key, value){
				options += '<option value="' + key + '">' + value + '</option>';
			});
        	/* for (var i = 0; i < e.length; i++) {
          	options += '<option value="' + e[i].optionValue + '">' + e[i].optionDisplay + '</option>';
        	} */
		}
        $("#legalEntity").html(options);
    },
    error:function(xhr,status){
           console.log(status);
    }
    });
	
	
	 $("#legalEntity").change(function(){
		 if($(this).val()!="NotSelected"){
			$.ajax({
			       type: "post",
			       url:"getSlabDataServlet",
			       data:"dataSend=businessUnit&&legalEntity="+$(this).val(),   
			       
			       success:function(data, textStatus, jqXHR)
		      {
			    	   console.log("Legal"+data);
			    	   var e=jQuery.parseJSON(data);
		     
		 console.log("Legal"+e);    
		     var options = '';
		     options += '<option value="NotSelected">Select the Business Unit</option>';
				if(e!=undefined && e!=null && e!="" ){
					$.each(e, function(key, value){
						options += '<option value="' + key + '">' + value + '</option>';
					});
		     	
				}
		     $("#businessUnit").html(options);
		 },
		 error:function(xhr,status){
		        console.log(status);
		 }
		 });
		}
	
	 });
	
	 $("#businessUnit").change(function(){
		 if($(this).val()!="NotSelected"){
			$.ajax({
			       type: "post",
			       url:"getSlabDataServlet",
			       data:"dataSend=clientName&&businessUnit="+$(this).val()+"&&legalEntity="+$("#legalEntity").val(),   
			       
			       success:function(data, textStatus, jqXHR)
		      {
			    	   console.log("Legal"+data);
			    	   var e=jQuery.parseJSON(data);
		     
		 console.log("Legal"+e);    
		     var options = '';
		     options += '<option value="NotSelected">Select the Client Name</option>';
				if(e!=undefined && e!=null && e!="" ){
					$.each(e, function(key, value){
						options += '<option value="' + key + '">' + value + '</option>';
					});
		     	
				}
		     $("#clientName").html(options);
		 },
		 error:function(xhr,status){
		        console.log(status);
		 }
		 });
		}
	
	 });
	 
	 $("#clientName").change(function(){
		 if($(this).val()!="NotSelected"){
			$.ajax({
			       type: "post",
			       url:"getSlabDataServlet",
			       data:"dataSend=clientId&&businessUnit="+$("#businessUnit").val()+"&&clientName="+$(this).val()+"&&legalEntity="+$("#legalEntity").val(),   
			       success:function(data, textStatus, jqXHR)
				   {
			    	   console.log("Legal"+data);
			    	   var e=jQuery.parseJSON(data);
		     
		 				console.log("Legal"+e);    
		     			var options = '';
		     			options += '<option value="NotSelected">Select the Client ID</option>';
						if(e!=undefined && e!=null && e!="" ){
							$.each(e, function(key, value){
							options += '<option value="' + key + '">' + value + '</option>';
						});
					}
		    	 $("#clientId").html(options);
		 	},
		 		error:function(xhr,status){
		        	console.log(status);
		 		}
		 	});
		}
	 });
	 
	 $("#clientId").change(function(){
		 if($(this).val()!="NotSelected"){
			$.ajax({
			       type: "post",
			       url:"getSlabDataServlet",
			       data:"dataSend=clientlocationNo&&clientId="+$(this).val()+"&&businessUnit="+$("#businessUnit").val()+"&&clientName="+$("#clientName").val()+"&&legalEntity="+$("#legalEntity").val(),   
			       success:function(data, textStatus, jqXHR)
				   {
			    	   console.log("Legal"+data);
			    	   var e=jQuery.parseJSON(data);
		     
		 console.log("Legal"+e);    
		     var options = '';
		     options += '<option value="NotSelected">Select the Client Location number</option>';
				if(e!=undefined && e!=null && e!="" ){
					$.each(e, function(key, value){
						options += '<option value="' + key + '">' + value + '</option>';
					});
		     	
				}
		     $("#clientlocationNo").html(options);
		 },
		 error:function(xhr,status){
		        console.log(status);
		 }
		 });
		}
	
	 });
	 
	 $("#clientlocationNo").change(function(){
		 if($(this).val()!="NotSelected"){
			$.ajax({
			       type: "post",
			       url:"getSlabDataServlet",
			       data:"dataSend=productID&&clientlocationNo="+$(this).val()+"&&clientName="+$("#clientName").val()+"&&businessUnit="+$("#businessUnit").val()+"&&clientId="+$("#clientId").val()+"&&legalEntity="+$("#legalEntity").val(),   
			       success:function(data, textStatus, jqXHR)
				   {
			    	   console.log("Legal"+data);
			    	   var e=jQuery.parseJSON(data);
		     
		 				console.log("Legal"+e);    
		     			var options = '';
		     			options += '<option value="NotSelected">Select the Product Id</option>';
						if(e!=undefined && e!=null && e!="" ){
							$.each(e, function(key, value){
							options += '<option value="' + key + '">' + value + '</option>';
						});
					}
		    	 $("#productID").html(options);
		 	},
		 		error:function(xhr,status){
		        	console.log(status);
		 		}
		 	});
		}
	 });
	 
	 
	$("#submitBtn").click(function(){
		var jsonData={
				"LEGAL_ENTITY":$('#legalEntity').val(),
				"BUSINESS_UNIT":$('#businessUnit').val(),
				"CLIENT_ID":$('#clientId').val(),
				"CLIENT_NAME":$('#clientName').val(),
				"CLIENT_LOCATION_NO":$('#clientlocationNo').val(),
				"PRODUCT_ID":$('#productID').val()
		};
		$.ajax({
		       type: "post",
		       url:"getSlabDataServlet",
		       data:{dataSubmit:JSON.stringify(jsonData)},   
		       datatype:"json",
		       success:function(data, textStatus, jqXHR)
	            {
		    	   var optionCat ='';
		    	   $("#slabtable").find("tr:gt(0)").remove();
		    	   var trHTML = '';	
		    	   $('#tablediv').show();
		    	   console.log("submit:"+data);
		    	   console.log("length :"+data.length);
 
		            $.each(data, function(index, slab) {  
		            	trHTML += '<tr><td class=\"td\">'
				        +"<img src='css/images/edit.png' class='btnEdit'/></td>"
		            	+'<td class=\"td\"id=\"LEGAL_ENTITY\" >' + slab.LEGAL_ENTITY + '</td><td class=\"td\" id=\"BUSINESS_UNIT\">' 
		            	+ slab.BUSINESS_UNIT + '</td><td class=\"td\" id=\"CLIENT_ID\" >' 
		            	+ slab.CLIENT_ID + '</td><td class=\"td\" id=\"CLIENT_LOCATION_NO\">'
		            	+ slab.CLIENT_LOCATION_NO + '</td><td class=\"td\" id=\"PRODUCT_ID\">' 
		            	+ slab.PRODUCT_ID + '</td><td class=\"td\" id=\"CLIENT_NAME\" >'
		            	+ slab.CLIENT_NAME + '</td><td class=\"td\" id=\"SLAB_NO\" >'
		            	+ slab.SLAB_NO + '</td><td class=\"td\" id=\"SLAB_CATEGORY\">'
		            	+ slab.SLAB_CATEGORY + '</td><td class=\"td\" id=\"SLAB_TYPE\">'
		            	+ slab.SLAB_TYPE + '</td><td class=\"td\" id=\"MIN_SLAB_VOLUME\">'
		            	+ slab.MIN_SLAB_VOLUME + '</td><td class=\"td\" id=\"MAX_SLAB_VOLUME\">'
		            	+ slab.MAX_SLAB_VOLUME + '</td><td class=\"td\"  id=\"CHARGES\">'
		            	+ slab.CHARGES + '</td><td class=\"td\" id=\"ACTIVE\">'
		            	+ slab.ACTIVE + '</td><td class=\"td\" id=\"SLAB_END_DATE\">'
		            	+ slab.SLAB_END_DATE + '</td></tr>';
		            	console.log(slab);
		            	console.log("index "+index);
		            	$(".btnEdit").live("click",Edit); 
		           
		            	function Edit(){ 
		            		var par = $(this).parent().parent(); //tr 
		            		var tdButtons = par.children("td:nth-child(1)"); 
		            		var tdLegalEntity = par.children("td:nth-child(2)"); 
		            		var tdBusinessUnit = par.children("td:nth-child(3)"); 
		            		var tdClientId = par.children("td:nth-child(4)"); 
		            		var tdClientLocation = par.children("td:nth-child(5)");
		            		var tdProductId = par.children("td:nth-child(6)");
		            		var tdClientName = par.children("td:nth-child(7)");
		            		var tdSlabNo = par.children("td:nth-child(8)");
		            		var tdSlabCategory = par.children("td:nth-child(9)");
		            		var tdSlabType = par.children("td:nth-child(10)");
		            		var tdMinSlab = par.children("td:nth-child(11)");
		            		var tdMaxSlab = par.children("td:nth-child(12)");
		            		var tdCharges = par.children("td:nth-child(13)");
		            		var tdActive = par.children("td:nth-child(14)");
		            		var tdSlabEndDate = par.children("td:nth-child(15)");
		            		
		            		
		            		           		
		            		var optionType = '<option value=\"FIXED\">FIXED</option>';
		            		optionType += '<option value=\"CUMULATIVE\">CUMULATIVE</option>';
		            		optionType += '<option value=\"INCREMENTAL\">INCREMENTAL</option>';

		            		var optionCat='<option value=\"NORMAL\">NORMAL</option>';
		            		optionCat += '<option value=\"WARM_UP\">WARM UP</option>';
		            		
		            		
		            		tdSlabType.html("<select>"+optionType+"</Select>"); 
		            		tdSlabCategory.html("<select>"+optionCat+"</Select>");
		            		tdMinSlab.html("<input type='text' id='MIN_SLAB_VOLUME' value='"+tdMinSlab.html()+"'/>"); 
		            		tdMaxSlab.html("<input type='text' id='MAX_SLAB_VOLUME' value='"+tdMaxSlab.html()+"'/>"); 
		            		tdCharges.html("<input type='text' id='CHARGES' value='"+tdCharges.html()+"'/>");
		            		
		            		tdSlabEndDate.html("<input type='text' class ='dateTimePicker1' id='SLAB_END_DATE' value='"+tdSlabEndDate.html()+"'/>");
		            		tdButtons.html("<img src='css/images/save.png' class='btnSave'/>"); 
		            		 
		            		$(".dateTimePicker1").datepicker({
		            		      showOn: "button",
		            		      buttonImage: "css/images/calendar.png",
		            		      buttonImageOnly: true,
		            		      buttonText: "",
		            		      dateFormat:"M dd, yy"
		            		    });
		            		
		            		$(".btnSave").bind("click", Save);
		            		};
		            		function Save(){ 
		            			var dataToSend =[];
		            			var par = $(this).parent().parent(); //tr 
		            			var tdButtons = par.children("td:nth-child(1)"); 
			            		var tdLegalEntity = par.children("td:nth-child(2)"); 
			            		var tdBusinessUnit = par.children("td:nth-child(3)"); 
			            		var tdClientId = par.children("td:nth-child(4)"); 
			            		var tdClientLocation = par.children("td:nth-child(5)");
			            		var tdProductId = par.children("td:nth-child(6)");
			            		var tdClientName = par.children("td:nth-child(7)");
			            		var tdSlabNo = par.children("td:nth-child(8)");
			            		var tdSlabCategory = par.children("td:nth-child(9)");
			            		var tdSlabType = par.children("td:nth-child(10)");
			            		var tdMinSlab = par.children("td:nth-child(11)");
			            		var tdMaxSlab = par.children("td:nth-child(12)");
			            		var tdCharges = par.children("td:nth-child(13)");
			            		var tdActive = par.children("td:nth-child(14)");
			            		var tdSlabEndDate = par.children("td:nth-child(15)");
			            		
			            		var data = {};
			            					            		
			            		
			            		data["LEGAL_ENTITY"] = tdLegalEntity.html();
			            		dataToSend.push(data);
			            		
			            		data["BUSINESS_UNIT"] = tdBusinessUnit.html();
			            		dataToSend.push(data);
			            		
			            		data["CLIENT_ID"] = tdClientId.html();
			            		dataToSend.push(data);
			            		
			            		data["CLIENT_LOCATION_NO"] = tdClientLocation.html();
			            		dataToSend.push(data);
			            		
			            		data["PRODUCT_ID"] = tdProductId.html();
			            		dataToSend.push(data);
			            		
			            		data["SLAB_NO"] =tdSlabNo.html();
			            		dataToSend.push(data);
			            		
			            		data["CLIENT_NAME"] = tdClientName.html();
			            		dataToSend.push(data);
			            						            	
			            		$('input:text').each(function(index){
			            					
			            	        data[$(this).attr('id')] =$(this).val();
			            	        dataToSend.push(data);
	            				});
	            							            		
			            		tdSlabCategory.html(tdSlabCategory.children($('optionCat')).val());
			            		tdSlabType.html(tdSlabType.children($('optionType')).val());
			            		tdMinSlab.html(tdMinSlab.children("input[type=text]").val());
			            		tdMaxSlab.html(tdMaxSlab.children("input[type=text]").val());
			            		tdCharges.html(tdCharges.children("input[type=text]").val());
			            		tdSlabEndDate.html(tdSlabEndDate.children("input[type=text]").val());
		            			tdButtons.html("<img src='css/images/edit.png' class='btnEdit'/>"); 
		            			$(".btnEdit").live("click", Edit); 
		            			
		            			
		            			data["SLAB_CATEGORY"] = tdSlabCategory.html();
			            		dataToSend.push(data);
			            	
			            		data["SLAB_TYPE"] = tdSlabType.html();
			            		dataToSend.push(data);
			            		
			            		data["ACTIVE"] = tdActive.html();
			            		dataToSend.push(data);
			            		
			            		
		            			console.log("dataToSend length: "+dataToSend.length);
		            			
		            			$.ajax({
		            			       type: "post",
		            			       url:"getSlabDataServlet",
		            			       data:{dataEdit:JSON.stringify(dataToSend)}, 
		            			     		            			       
		            			       datatype:"json",
		            			       success:function(data, textStatus, jqXHR)
		            		            {
		            			    	   var e=jQuery.parseJSON(data);
		            			              if(e.status==="success"){
		            				    		   alert("Update Done");
		            				    	   }
		            				    	   else if(e.status==="error"){
		            				    		   alert("ERROR");   
		            				    	   }
		            		            }
		            			});
		            			
		            		}; 
						
		            	
		            });
		    	   
		            $('#slabtable').append(trHTML);
		    	  // }                  
	            },
	            error:function(xhr,status)
	            {
	                 console.log(status);
	             }
	            });					  
		 });  
	
});
</script>
</head>
<body>
<jsp:useBean id="getSlabDataServlet" class="com.experian.finance.GetSlabDataServlet"/>

<form action="/Finance/getSlabDataServlet" method="post">
<img src="/Finance/images/Experian_logo.png"/>
<div class="container" id="displayContainer">
<h3 class="heading">Search Slab</h3>
		<div >
			<div class="labels"><span>Legal Entity:</span></div><select id="legalEntity" name="legalEntity"></select>
		</div>
		<div>
			<div class="labels"><span>Business Unit:</span></div><select id="businessUnit" name="businessUnit"></select>
		</div>
		<div>
			<div class="labels"><span>Client Name:</span></div><select id="clientName" name="clientName"></select>
		</div>
		<div>
			<div class="labels"><span>Client Id:</span></div><select id="clientId" name="clientId"></select>
		</div>
		<div>
			<div class="labels"><span>Client Location No:</span></div><select id="clientlocationNo" name="clientlocationNo"></select>
		</div>
		<div>
			<div class="labels"><span>Product ID:</span></div><select id="productID" name="productID"></select>
		</div>		
		<div>
		<table align="center">
		<tr>
			<td><div class="buttondiv"><input type="button" name="submitBtn" id="submitBtn" value="Submit" style="float: right;"/></div></td>
			<td><div class="buttondiv"><input type="button" name="addBtn" id="addBtn" value="Add" onclick="javascript:window.location='SlabInput.jsp'" style="float: right;"/></div></td>
			<td><div class="buttondiv"><input type="button" value="Back" onclick="history.go(-1);" style="float: right;"/></div></td>
		</tr>
		</table>
		</div>	
</div>
<br/><br/>
<div id="tablediv">
<table cellspacing="1px" id="slabtable" class="table"> 
    <tr class="tr"> 
    	<th class="th" scope="col">EDIT/ SAVE</th>
        <th class="th" scope="col">LEGAL ENTITY</th> 
        <th class="th" scope="col">BUSINESS UNIT</th> 
        <th class="th" scope="col">CLIENT ID</th> 
        <th class="th" scope="col">CLIENT LOCATION NO</th> 
        <th class="th" scope="col">PRODUCT ID</th> 
        <th class="th" scope="col">PRODUCT NAME</th>  
        <th class="th" scope="col">SLAB NO</th>  
        <th class="th" scope="col">SLAB CATEGORY</th>  
        <th class="th" scope="col">SLAB TYPE</th>  
        <th class="th" scope="col">MIN SLAB VOLUME</th>
        <th class="th" scope="col">MAX SLAB VOLUME</th>
        <th class="th" scope="col">CHARGES</th>
        <th class="th" scope="col">ACTIVE</th>
        <th class="th" scope="col">SLAB END DATE</th>
                  
    </tr> 
</table>
</div>
</form>
<jsp:include page="footer.jsp"/>
</body>
</html>