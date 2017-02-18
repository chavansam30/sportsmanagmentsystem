<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Contract</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui.structure.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui.theme.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">

<%--<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.currency.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.selectbox-0.2.js"></script> --%>

<script type="text/javascript" src="js/jquery.1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script> 
<script type="text/javascript" src="js/jquery.currency.js"></script> 

<script type="text/javascript" src="<%=request.getContextPath()%>/js/validationFile.js"></script>

</head>
<body>

<script type="text/javascript">
$(document).ready(function(){
	 $("#tablediv").hide();
	$.ajax({
	       type: "post",
	       url:"/Finance/getDataServlet",
	       data:{dataSend:"legalEntity"},   
	       datatype:"json",
	       success:function(data, textStatus, jqXHR)
         {
	    	   //console.log("legalEntity"+data);
	    	  var e=jQuery.parseJSON(data);
        
    		//console.log("legalEntity"+e);    
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
			       url:"/Finance/getDataServlet",
			       data:"dataSend=businessUnit&&legalEntity="+$(this).val(),   
			       
			       success:function(data, textStatus, jqXHR)
		      {
			    	  // console.log("businessUnit"+data);
			    	   var e=jQuery.parseJSON(data);
		     
		// console.log("businessUnit"+e);    
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
			       url:"/Finance/getDataServlet",
			       data:"dataSend=clientName&&businessUnit="+$(this).val()+"&&legalEntity="+$("#legalEntity").val(),   
			       
			       success:function(data, textStatus, jqXHR)
		      {
			    	   //console.log("clientId"+data);
			    	   var e=jQuery.parseJSON(data);
		     
		// console.log("clientId"+e);    
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
			       url:"/Finance/getDataServlet",
			       data:"dataSend=clientId&&businessUnit="+$("#businessUnit").val()+"&&clientName="+$(this).val()+"&&legalEntity="+$("#legalEntity").val(),   
			       success:function(data, textStatus, jqXHR)
				   {
			    	 //  console.log("clientlocationNo"+data);
			    	   var e=jQuery.parseJSON(data);
		     
		// console.log("clientlocationNo"+e);    
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
			       url:"/Finance/getDataServlet",
			       data:"dataSend=clientlocationNo&&clientId="+$(this).val()+"&&businessUnit="+$("#businessUnit").val()+"&&clientName="+$("#clientName").val()+"&&legalEntity="+$("#legalEntity").val(),   
			       success:function(data, textStatus, jqXHR)
				   {
			    	 //  console.log("clientName"+data);
			    	   var e=jQuery.parseJSON(data);
		     
		// console.log("clientName"+e);    
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
			       url:"/Finance/getDataServlet",
			       data:"dataSend=productID&&clientlocationNo="+$(this).val()+"&&clientName="+$("#clientName").val()+"&&businessUnit="+$("#businessUnit").val()+"&&clientId="+$("#clientId").val()+"&&legalEntity="+$("#legalEntity").val(),   
			       success:function(data, textStatus, jqXHR)
				   {
			    	 //  console.log("productID"+data);
			    	   var e=jQuery.parseJSON(data);
		     
		// console.log("productID"+e);    
		     var options = '';
		     options += '<option value="NotSelected">Select the product id</option>';
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
					"PRODUCT_ID":$('#productID').val(),
			};	
			$.ajax({
			       type: "post",
			       url:"/Finance/getDataServlet",
			       data:{dataSubmit:JSON.stringify(jsonData)},   
			       datatype:"json",
			       success:function(data, textStatus, jqXHR)
		            {
			    	   $("#slabtable").find("tr:gt(0)").remove();
			    	   var trHTML = '';	
			    	   $('#tablediv').show();
			    	   console.log("submit:"+data);
			    	   console.log("length :"+data.length);
			    	  
			            $.each(data, function(index, contract) {  
			            	trHTML += '<tr><td class=\"td\">'
				            +"<img src='css/images/edit.png' class='btnEdit'/>"+'</td><td class=\"td\">' 
				            + contract.LEGAL_ENTITY + '</td><td class=\"td\">' 
			            	+ contract.BUSINESS_UNIT + '</td><td class=\"td\" >' 
			            	+ contract.CLIENT_ID + '</td><td class=\"td\">'
			            	+ contract.CLIENT_LOCATION_NO + '</td><td class=\"td\">' 
			            	+ contract.PRODUCT_ID + '</td><td class=\"td\">'
			            	+ contract.CLIENT_NAME + '</td><td class=\"td\">'
			            	+ contract.PRODUCT_NAME + '</td><td class=\"td\">'
			            	+ contract.CATEGORY + '</td><td class=\"td\">'
			            	+ contract.CONTRACT_START_DATE + '</td><td class=\"td\">'
			            	+ contract.CONTRACT_END_DATE + '</td><td class=\"td\">'
			            	+ contract.AR_PROCESS_DATE + '</td><td class=\"td\">'
			            	+ contract.ONE_TIME_SETUP_FLAG + '</td><td class=\"td\">'
			            	+ contract.ONE_TIME_SETUP_CHARGE_DATE + '</td><td class=\"td\">'
			            	+ contract.ONE_TIME_SETUP_FEE + '</td><td class=\"td\">'
			            	+ contract.ADDITIONAL_SERVICE_CHARGES + '</td><td class=\"td\">'
			            	+ contract.EARLY_TERMINATION_FLAG + '</td><td class=\"td\">'
			            	+ contract.EARLY_TERMINATION_DATE + '</td><td class=\"td\">'
			            	+ contract.EARLY_TERMINATION_MINIMUM_VOLUME + '</td><td class=\"td\">'
			            	+ contract.PRO_RATA_CHARGES_FOR_TERMINATION + '</td><td class=\"td\">'
			            	+ contract.NO_MONTHS_OF_COMMITMENT + '</td><td class=\"td\">'
			            	+ contract.COMMITMENT_QTY + '</td><td class=\"td\">'
			            	+ contract.DISCOUNT_RATE_FLAG + '</td><td class=\"td\">'
			            	+ contract.DISCOUNT_START_DATE + '</td><td class=\"td\">'
			            	+ contract.DISCOUNT_END_DATE + '</td><td class=\"td\">'
			            	+ contract.DISCOUNT_RATE + '</td><td class=\"td\">'
			            	+ contract.DISCOUNT_EXTENSION_FLAG + '</td><td class=\"td\">'
			            	+ contract.DISCOUNT_EXTENSION_END_DATE + '</td><td class=\"td\">'
			            	+ contract.SPECIAL_RATE_FLAG + '</td><td class=\"td\">'
			            	+ contract.SPECIAL_START_DATE + '</td><td class=\"td\">'
			            	+ contract.SPECIAL_END_DATE + '</td><td class=\"td\">'
			            	+ contract.SPECIAL_RATE + '</td><td class=\"td\">'
			            	+ contract.WARM_UP_FLAG + '</td><td class=\"td\">'
			            	+ contract.WARM_UP_START_DATE + '</td><td class=\"td\">'
			            	+ contract.WARM_UP_END_DATE + '</td><td class=\"td\">'
			            	+ contract.WARM_UP_EXTENSION_FLAG + '</td><td class=\"td\">'
			            	+ contract.WARM_UP_EXTENSION_END_DATE + '</td><td class=\"td\">'
			            	+ contract.MONTHLY_SLAB_FLAG + '</td><td class=\"td\">'
			            	+ contract.MIN_MONTHLY_SLAB_VOLUME + '</td><td class=\"td\">'
			            	+ contract.MAX_MONTHLY_SLAB_VOLUME + '</td><td class=\"td\">'
			            	+ contract.MONTHLY_RATE + '</td><td class=\"td\">'
			            	+ contract.QUARTERLY_SLAB_FLAG + '</td><td class=\"td\">'
			            	+ contract.MIN_QUARTERLY_SLAB_VOLUME + '</td><td class=\"td\">'
			            	+ contract.MAX_QUARTERLY_SLAB_VOLUME + '</td><td class=\"td\">'
			            	+ contract.QUARTERLY_RATE + '</td><td class=\"td\">'
			            	+ contract.SEMI_ANNUAL_SLAB_FLAG + '</td><td class=\"td\">'
			            	+ contract.MIN_SEMI_ANNUAL_SLAB_VOLUME + '</td><td class=\"td\">'
			            	+ contract.MAX_SEMI_ANNUAL_SLAB_VOLUME + '</td><td class=\"td\">'
			            	+ contract.SEMI_ANNUAL_SLAB_RATE + '</td><td class=\"td\">'
			            	+ contract.ANNUAL_SLAB_FLAG + '</td><td class=\"td\">'
			            	+ contract.MIN_ANNUAL_SLAB_VOLUME + '</td><td class=\"td\">'
			            	+ contract.MAX_ANNUAL_SLAB_VOLUME + '</td><td class=\"td\">'
			            	+ contract.ANNUAL_SLAB_RATE + '</td><td class=\"td\">'
			            	+ contract.PROCESSED_VOLUME_TILL_DATE + '</td><td class=\"td\">'
			            	+ contract.PROCESSED_VOLUME_QUARTER + '</td><td class=\"td\">'
			            	+ contract.PROCESSED_VOLUME_SEMI_ANNUAL + '</td><td class=\"td\">'
			            	+ contract.PROCESSED_VOLUME_ANNUAL + '</td><td class=\"td\">'
			            	+ contract.PROCESSED_VOLUME_UPDATE_DATE + '</td></tr>';
			            	console.log(contract);
			            	
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
			            		var tdProductName = par.children("td:nth-child(8)");
			            		var tdCategory = par.children("td:nth-child(9)");
			            		var tdContractStartDate = par.children("td:nth-child(10)");
			            		var tdContractEndDate = par.children("td:nth-child(11)");
			            		var tdARProcessDate = par.children("td:nth-child(12)");
			            		var tdOneTimeSetupFlag = par.children("td:nth-child(13)");
			            		var tdOneTimeSetupChargeDate = par.children("td:nth-child(14)");
			            		var tdOneTimeSetupFee = par.children("td:nth-child(15)");
			            		var tdAdditionalServiceCharges = par.children("td:nth-child(16)");
			            		var tdEarlyTerminaltionFlag = par.children("td:nth-child(17)");
			            		var tdEarlyTerminationDate = par.children("td:nth-child(18)");
			            		var tdEarlyTerminationMinVol = par.children("td:nth-child(19)");
			            		var tdProRataCharges = par.children("td:nth-child(20)");
			            		var tdNoMonthsOfCommitment = par.children("td:nth-child(21)");
			            		var tdCommitmentQty = par.children("td:nth-child(22)");
			            		var tdDiscountRateFlag = par.children("td:nth-child(23)");
			            		var tdDiscountStartDate = par.children("td:nth-child(24)");
			            		var tdDiscountEndDate = par.children("td:nth-child(25)");
			            		var tdDiscountRate = par.children("td:nth-child(26)");
			            		var tdDiscountExtentionFlag = par.children("td:nth-child(27)");
			            		var tdDiscountExtentionEndDate = par.children("td:nth-child(28)");
			            		var tdSpecialRateFlag = par.children("td:nth-child(29)");
			            		var tdSpecialStartDate = par.children("td:nth-child(30)");
			            		var tdSpecialEndDate = par.children("td:nth-child(31)");
			            		var tdSpecialRate = par.children("td:nth-child(32)");
			            		var tdWarmUpFlag = par.children("td:nth-child(33)");
			            		var tdWarmUpStartDate = par.children("td:nth-child(34)");
			            		var tdWarmUpEndDate = par.children("td:nth-child(35)");
			            		var tdWarmUpExtentionFlag = par.children("td:nth-child(36)");
			            		var tdWarmUpExtentionEndDate = par.children("td:nth-child(37)");
			            		var tdMonthlySlabFlag = par.children("td:nth-child(38)");
			            		var tdMinMonthlyslabVol = par.children("td:nth-child(39)");
			            		var tdMaxMonthlyslabVol = par.children("td:nth-child(40)");
			            		var tdMonthlyRate = par.children("td:nth-child(41)");
			            		var tdQuaterlySlabFlag = par.children("td:nth-child(42)");
			            		var tdMinQuaterlySlabVol = par.children("td:nth-child(43)");
			            		var tdMaxQuaterlySlabVol = par.children("td:nth-child(44)");
			            		var tdQuaterlyRate = par.children("td:nth-child(45)");
			            		var tdSemiAnnualSlabFlag = par.children("td:nth-child(46)");
			            		var tdMinSemiAnnualSlabVol = par.children("td:nth-child(47)");
			            		var tdMaxSemiAnnualSlabVol = par.children("td:nth-child(48)");
			            		var tdSemiAnnualSlabRate = par.children("td:nth-child(49)");
			            		var tdAnnualSlabFlag = par.children("td:nth-child(50)");
			            		var tdMinAnnualSlabVol = par.children("td:nth-child(51)");
			            		var tdMaxAnnualSlabVol = par.children("td:nth-child(52)");
			            		var tdAnnualSlabRate = par.children("td:nth-child(53)");
			            		var tdProcessedVolTillDate = par.children("td:nth-child(54)");
			            		var tdProcessedVolTillQtr = par.children("td:nth-child(55)");
			            		var tdProcessedVolTillSemiAnnual = par.children("td:nth-child(56)");
			            		var tdProcessedVolTillAnnual = par.children("td:nth-child(57)");
			            		var tdProcessedVolUpdateDate = par.children("td:nth-child(58)");
			         
			            		//tdLegalEntity.html("<input type='text' id='txtLegalEntity' value='"+tdLegalEntity.html()+"'/>"); 
			            		//tdBusinessUnit.html("<input type='text' id='txtBusinessUnit' value='"+tdBusinessUnit.html()+"'/>"); 
			            		//tdClientId.html("<input type='text' id='txtClientId' value='"+tdClientId.html()+"'/>"); 
			            		//tdClientLocation.html("<input type='text' id='txtClientLocation' value='"+tdClientLocation.html()+"'/>"); 
			            		//tdClientName.html("<input type='text' id='CLIENT_NAME' value='"+tdClientName.html()+"'/>"); 
			            		//tdProductId.html("<input type='text' id='txtProductId' value='"+tdProductId.html()+"'/>"); 
			            		tdProductName.html("<input type='text' id='PRODUCT_NAME' value='"+tdProductName.html()+"'/>"); 
			            		tdCategory.html("<input type='text' id='CATEGORY' value='"+tdCategory.html()+"'/>"); 
			            		tdContractStartDate.html("<input type='text' width='auto' class ='dateTimePicker1' id='CONTRACT_START_DATE' value='"+tdContractStartDate.html()+"'/>"); 
			            		tdContractEndDate.html("<input type='text' width='auto' class ='dateTimePicker1' id='CONTRACT_END_DATE' value='"+tdContractEndDate.html()+"'/>"); 
			            		tdARProcessDate.html("<input type='text' width='auto' class ='dateTimePicker1' id='AR_PROCESS_DATE' value='"+tdARProcessDate.html()+"'/>"); 
			            		tdOneTimeSetupFlag.html("<input type='text' id='ONE_TIME_SETUP_FLAG' value='"+tdOneTimeSetupFlag.html()+"'/>"); 
			            		tdOneTimeSetupChargeDate.html("<input type='text' width='auto' class ='dateTimePicker1' id='ONE_TIME_SETUP_CHARGE_DATE' value='"+tdOneTimeSetupChargeDate.html()+"'/>");
			            		tdOneTimeSetupFee.html("<input type='text' id='ONE_TIME_SETUP_FEE' value='"+tdOneTimeSetupFee.html()+"'/>");
			            		tdAdditionalServiceCharges.html("<input type='text' id='ADDITIONAL_SERVICE_CHARGES' value='"+tdAdditionalServiceCharges.html()+"'/>");
			            		tdEarlyTerminaltionFlag.html("<input type='text' id='EARLY_TERMINATION_FLAG' value='"+tdEarlyTerminaltionFlag.html()+"'/>"); 
			            		tdEarlyTerminationDate.html("<input type='text' width='auto' class ='dateTimePicker1' id='EARLY_TERMINATION_DATE' value='"+tdEarlyTerminationDate.html()+"'/>"); 
			            		tdEarlyTerminationMinVol.html("<input type='text' id='EARLY_TERMINATION_MINIMUM_VOLUME' value='"+tdEarlyTerminationMinVol.html()+"'/>"); 
			            		tdProRataCharges.html("<input type='text' id='PRO_RATA_CHARGES_FOR_TERMINATION' value='"+tdProRataCharges.html()+"'/>"); 
			            		tdNoMonthsOfCommitment.html("<input type='text' id='NO_MONTHS_OF_COMMITMENT' value='"+tdNoMonthsOfCommitment.html()+"'/>"); 
			            		tdCommitmentQty.html("<input type='text' id='COMMITMENT_QTY' value='"+tdCommitmentQty.html()+"'/>"); 
			            		tdDiscountRateFlag.html("<input type='text' id='DISCOUNT_RATE_FLAG' value='"+tdDiscountRateFlag.html()+"'/>"); 
			            		tdDiscountStartDate.html("<input type='text' width='auto' class ='dateTimePicker1' id='DISCOUNT_START_DATE' value='"+tdDiscountStartDate.html()+"'/>"); 
			            		tdDiscountEndDate.html("<input type='text' width='auto' class ='dateTimePicker1' id='DISCOUNT_END_DATE' value='"+tdDiscountEndDate.html()+"'/>"); 
			            		tdDiscountRate.html("<input type='text' id='DISCOUNT_RATE' value='"+tdDiscountRate.html()+"'/>"); 
			            		tdDiscountExtentionFlag.html("<input type='text' id='DISCOUNT_EXTENSION_FLAG' value='"+tdDiscountExtentionFlag.html()+"'/>"); 
			            		tdDiscountExtentionEndDate.html("<input type='text' width='auto' class ='dateTimePicker1' id='DISCOUNT_EXTENSION_END_DATE' value='"+tdDiscountExtentionEndDate.html()+"'/>");
			            		tdSpecialRateFlag.html("<input type='text' id='SPECIAL_RATE_FLAG' value='"+tdSpecialRateFlag.html()+"'/>");
			            		tdSpecialStartDate.html("<input type='text' width='auto' class ='dateTimePicker1' id='SPECIAL_START_DATE' value='"+tdSpecialStartDate.html()+"'/>");
			            		tdSpecialEndDate.html("<input type='text' width='auto' class ='dateTimePicker1' id='SPECIAL_END_DATE' value='"+tdSpecialEndDate.html()+"'/>"); 
			            		tdSpecialRate.html("<input type='text' id='SPECIAL_RATE' value='"+tdSpecialRate.html()+"'/>"); 
			            		tdWarmUpFlag.html("<input type='text' id='WARM_UP_FLAG' value='"+tdWarmUpFlag.html()+"'/>"); 
			            		tdWarmUpStartDate.html("<input type='text' width='auto' class ='dateTimePicker1' id='WARM_UP_START_DATE' value='"+tdWarmUpStartDate.html()+"'/>");
			            		tdWarmUpEndDate.html("<input type='text' width='auto' class ='dateTimePicker1' id='WARM_UP_END_DATE' value='"+tdWarmUpEndDate.html()+"'/>");
			            		tdWarmUpExtentionFlag.html("<input type='text' id='WARM_UP_EXTENSION_FLAG' value='"+tdWarmUpExtentionFlag.html()+"'/>");
			            		tdWarmUpExtentionEndDate.html("<input type='text' id='WARM_UP_EXTENSION_END_DATE' value='"+tdWarmUpExtentionEndDate.html()+"'/>");
			            		tdMonthlySlabFlag.html("<input type='text' id='MONTHLY_SLAB_FLAG' value='"+tdMonthlySlabFlag.html()+"'/>");
			            		tdMinMonthlyslabVol.html("<input type='text' id='MIN_MONTHLY_SLAB_VOLUME' value='"+tdMinMonthlyslabVol.html()+"'/>");
			            		tdMaxMonthlyslabVol.html("<input type='text' id='MAX_MONTHLY_SLAB_VOLUME' value='"+tdMaxMonthlyslabVol.html()+"'/>");
			            		tdMonthlyRate.html("<input type='text' id='MONTHLY_RATE' value='"+tdMonthlyRate.html()+"'/>");
			            		tdQuaterlySlabFlag.html("<input type='text' id='QUARTERLY_SLAB_FLAG' value='"+tdQuaterlySlabFlag.html()+"'/>");
			            		tdMinQuaterlySlabVol.html("<input type='text' id='MIN_QUARTERLY_SLAB_VOLUME' value='"+tdMinQuaterlySlabVol.html()+"'/>");
			            		tdMaxQuaterlySlabVol.html("<input type='text' id='MAX_QUARTERLY_SLAB_VOLUME' value='"+tdMaxQuaterlySlabVol.html()+"'/>");
			            		tdQuaterlyRate.html("<input type='text' id='QUARTERLY_RATE' value='"+tdQuaterlyRate.html()+"'/>");
			            		tdSemiAnnualSlabFlag.html("<input type='text' id='SEMI_ANNUAL_SLAB_FLAG' value='"+tdSemiAnnualSlabFlag.html()+"'/>");
			            		tdMinSemiAnnualSlabVol.html("<input type='text' id='MIN_SEMI_ANNUAL_SLAB_VOLUME' value='"+tdMinSemiAnnualSlabVol.html()+"'/>");
			            		tdMaxSemiAnnualSlabVol.html("<input type='text' id='MAX_SEMI_ANNUAL_SLAB_VOLUME' value='"+tdMaxSemiAnnualSlabVol.html()+"'/>");
			            		tdSemiAnnualSlabRate.html("<input type='text' id='SEMI_ANNUAL_SLAB_RATE' value='"+tdSemiAnnualSlabRate.html()+"'/>");
			            		tdAnnualSlabFlag.html("<input type='text' id='ANNUAL_SLAB_FLAG' value='"+tdAnnualSlabFlag.html()+"'/>");
			            		tdMinAnnualSlabVol.html("<input type='text' id='MIN_ANNUAL_SLAB_VOLUME' value='"+tdMinAnnualSlabVol.html()+"'/>");
			            		tdMaxAnnualSlabVol.html("<input type='text' id='MAX_ANNUAL_SLAB_VOLUME' value='"+tdMaxAnnualSlabVol.html()+"'/>");
			            		tdAnnualSlabRate.html("<input type='text' id='ANNUAL_SLAB_RATE' value='"+tdAnnualSlabRate.html()+"'/>");
			            		tdProcessedVolTillDate.html("<input type='text' id='PROCESSED_VOLUME_TILL_DATE' value='"+tdProcessedVolTillDate.html()+"'/>");
			            		tdProcessedVolTillQtr.html("<input type='text' id='PROCESSED_VOLUME_QUARTER' value='"+tdProcessedVolTillQtr.html()+"'/>");
			            		tdProcessedVolTillSemiAnnual.html("<input type='text' id='PROCESSED_VOLUME_SEMI_ANNUAL' value='"+tdProcessedVolTillSemiAnnual.html()+"'/>");
			            		tdProcessedVolTillAnnual.html("<input type='text' id='PROCESSED_VOLUME_ANNUAL' value='"+tdProcessedVolTillAnnual.html()+"'/>");
			            		tdProcessedVolUpdateDate.html("<input type='text' width='auto' class ='dateTimePicker1' id='PROCESSED_VOLUME_UPDATE_DATE' value='"+tdProcessedVolUpdateDate.html()+"'/>");
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
			            			var par = $(this).parent().parent(); //tr 
			            			var tdButtons = par.children("td:nth-child(1)");
				            		var tdLegalEntity = par.children("td:nth-child(2)"); 
				            		var tdBusinessUnit = par.children("td:nth-child(3)"); 
				            		var tdClientId = par.children("td:nth-child(4)"); 
				            		var tdClientLocation = par.children("td:nth-child(5)");
				            		var tdProductId = par.children("td:nth-child(6)");
				            		var tdClientName = par.children("td:nth-child(7)");
				            		var tdProductName = par.children("td:nth-child(8)");
				            		var tdCategory = par.children("td:nth-child(9)");
				            		var tdContractStartDate = par.children("td:nth-child(10)");
				            		var tdContractEndDate = par.children("td:nth-child(11)");
				            		var tdARProcessDate = par.children("td:nth-child(12)");
				            		var tdOneTimeSetupFlag = par.children("td:nth-child(13)");
				            		var tdOneTimeSetupChargeDate = par.children("td:nth-child(14)");
				            		var tdOneTimeSetupFee = par.children("td:nth-child(15)");
				            		var tdAdditionalServiceCharges = par.children("td:nth-child(16)");
				            		var tdEarlyTerminaltionFlag = par.children("td:nth-child(17)");
				            		var tdEarlyTerminationDate = par.children("td:nth-child(18)");
				            		var tdEarlyTerminationMinVol = par.children("td:nth-child(19)");
				            		var tdProRataCharges = par.children("td:nth-child(20)");
				            		var tdNoMonthsOfCommitment = par.children("td:nth-child(21)");
				            		var tdCommitmentQty = par.children("td:nth-child(22)");
				            		var tdDiscountRateFlag = par.children("td:nth-child(23)");
				            		var tdDiscountStartDate = par.children("td:nth-child(24)");
				            		var tdDiscountEndDate = par.children("td:nth-child(25)");
				            		var tdDiscountRate = par.children("td:nth-child(26)");
				            		var tdDiscountExtentionFlag = par.children("td:nth-child(27)");
				            		var tdDiscountExtentionEndDate = par.children("td:nth-child(28)");
				            		var tdSpecialRateFlag = par.children("td:nth-child(29)");
				            		var tdSpecialStartDate = par.children("td:nth-child(30)");
				            		var tdSpecialEndDate = par.children("td:nth-child(31)");
				            		var tdSpecialRate = par.children("td:nth-child(32)");
				            		var tdWarmUpFlag = par.children("td:nth-child(33)");
				            		var tdWarmUpStartDate = par.children("td:nth-child(34)");
				            		var tdWarmUpEndDate = par.children("td:nth-child(35)");
				            		var tdWarmUpExtentionFlag = par.children("td:nth-child(36)");
				            		var tdWarmUpExtentionEndDate = par.children("td:nth-child(37)");
				            		var tdMonthlySlabFlag = par.children("td:nth-child(38)");
				            		var tdMinMonthlyslabVol = par.children("td:nth-child(39)");
				            		var tdMaxMonthlyslabVol = par.children("td:nth-child(40)");
				            		var tdMonthlyRate = par.children("td:nth-child(41)");
				            		var tdQuaterlySlabFlag = par.children("td:nth-child(42)");
				            		var tdMinQuaterlySlabVol = par.children("td:nth-child(43)");
				            		var tdMaxQuaterlySlabVol = par.children("td:nth-child(44)");
				            		var tdQuaterlyRate = par.children("td:nth-child(45)");
				            		var tdSemiAnnualSlabFlag = par.children("td:nth-child(46)");
				            		var tdMinSemiAnnualSlabVol = par.children("td:nth-child(47)");
				            		var tdMaxSemiAnnualSlabVol = par.children("td:nth-child(48)");
				            		var tdSemiAnnualSlabRate = par.children("td:nth-child(49)");
				            		var tdAnnualSlabFlag = par.children("td:nth-child(50)");
				            		var tdMinAnnualSlabVol = par.children("td:nth-child(51)");
				            		var tdMaxAnnualSlabVol = par.children("td:nth-child(52)");
				            		var tdAnnualSlabRate = par.children("td:nth-child(53)");
				            		var tdProcessedVolTillDate = par.children("td:nth-child(54)");
				            		var tdProcessedVolTillQtr = par.children("td:nth-child(55)");
				            		var tdProcessedVolTillSemiAnnual = par.children("td:nth-child(56)");
				            		var tdProcessedVolTillAnnual = par.children("td:nth-child(57)");
				            		var tdProcessedVolUpdateDate = par.children("td:nth-child(58)");
				            		
				            		var legalEntity ={};
				            		var businessUnit ={};
				            		var clientId ={};
				            		var clientName={};
				            		var clientlocationNo ={};
				            		var productID ={};
				            	
				            		legalEntity["LEGAL_ENTITY"] = $('#legalEntity').val();
				            		dataToSend.push(legalEntity);
				            		
				            		businessUnit["BUSINESS_UNIT"] = $('#businessUnit').val();
				            		dataToSend.push(businessUnit);
				            		
				            		clientId["CLIENT_ID"] = $('#clientId').val();
				            		dataToSend.push(clientId);
				            		
				            		clientName["CLIENT_NAME"] = $('#clientName').val();
				            		dataToSend.push(clientName);
				            		
				            		clientlocationNo["CLIENT_LOCATION_NO"] = $('#clientlocationNo').val();
				            		dataToSend.push(clientlocationNo);
				            		
				            		productID["PRODUCT_ID"] = $('#productID').val();
				            		dataToSend.push(productID);
				            						            		
				            		 $('input:text').each(function(index){
				            			var data = {};		
				            	        data[$(this).attr('id')] =$(this).val();
				            	        dataToSend.push(data);
		            				});
				            		
				            		//tdClientName.html(tdClientName.children("input[type=text]").val());
				            		tdProductName.html(tdProductName.children("input[type=text]").val());
				            		tdCategory.html(tdCategory.children("input[type=text]").val());
				            		tdContractStartDate.html(tdContractStartDate.children("input[type=text]").val());
				            		tdContractEndDate.html(tdContractEndDate.children("input[type=text]").val());
				            		tdARProcessDate.html(tdARProcessDate.children("input[type=text]").val());
				            		tdOneTimeSetupFlag.html(tdOneTimeSetupFlag.children("input[type=text]").val());
				            		tdOneTimeSetupChargeDate.html(tdOneTimeSetupChargeDate.children("input[type=text]").val());
				            		tdOneTimeSetupFee.html(tdOneTimeSetupFee.children("input[type=text]").val());
				            		tdAdditionalServiceCharges.html(tdAdditionalServiceCharges.children("input[type=text]").val());
				            		tdEarlyTerminaltionFlag.html(tdEarlyTerminaltionFlag.children("input[type=text]").val());
				            		tdEarlyTerminationDate.html(tdEarlyTerminationDate.children("input[type=text]").val());
				            		tdEarlyTerminationMinVol.html(tdEarlyTerminationMinVol.children("input[type=text]").val());
				            		tdProRataCharges.html(tdProRataCharges.children("input[type=text]").val());
				            		tdNoMonthsOfCommitment.html(tdNoMonthsOfCommitment.children("input[type=text]").val());
				            		tdCommitmentQty.html(tdCommitmentQty.children("input[type=text]").val());
				            		tdDiscountRateFlag.html(tdDiscountRateFlag.children("input[type=text]").val());
				            		tdDiscountStartDate.html(tdDiscountStartDate.children("input[type=text]").val());
				            		tdDiscountEndDate.html(tdDiscountEndDate.children("input[type=text]").val());
				            		tdDiscountRate.html(tdDiscountRate.children("input[type=text]").val());
				            		tdDiscountExtentionFlag.html(tdDiscountExtentionFlag.children("input[type=text]").val());
				            		tdDiscountExtentionEndDate.html(tdDiscountExtentionEndDate.children("input[type=text]").val());
				            		tdSpecialRateFlag.html(tdSpecialRateFlag.children("input[type=text]").val());
				            		tdSpecialStartDate.html(tdSpecialStartDate.children("input[type=text]").val());
				            		tdSpecialEndDate.html(tdSpecialEndDate.children("input[type=text]").val());
				            		tdSpecialRate.html(tdSpecialRate.children("input[type=text]").val());
				            		tdWarmUpFlag.html(tdWarmUpFlag.children("input[type=text]").val());
				            		tdWarmUpStartDate.html(tdWarmUpStartDate.children("input[type=text]").val());
				            		tdWarmUpEndDate.html(tdWarmUpEndDate.children("input[type=text]").val());
				            		tdWarmUpExtentionFlag.html(tdWarmUpExtentionFlag.children("input[type=text]").val());
				            		tdWarmUpExtentionEndDate.html(tdWarmUpExtentionEndDate.children("input[type=text]").val());
				            		tdMonthlySlabFlag.html(tdMonthlySlabFlag.children("input[type=text]").val());
				            		tdMinMonthlyslabVol.html(tdMinMonthlyslabVol.children("input[type=text]").val());
				            		tdMaxMonthlyslabVol.html(tdMaxMonthlyslabVol.children("input[type=text]").val());
				            		tdMonthlyRate.html(tdMonthlyRate.children("input[type=text]").val());
				            		tdQuaterlySlabFlag.html(tdQuaterlySlabFlag.children("input[type=text]").val());
				            		tdMinQuaterlySlabVol.html(tdMinQuaterlySlabVol.children("input[type=text]").val());
				            		tdMaxQuaterlySlabVol.html(tdMaxQuaterlySlabVol.children("input[type=text]").val());
				            		tdQuaterlyRate.html(tdQuaterlyRate.children("input[type=text]").val());
				            		tdSemiAnnualSlabFlag.html(tdSemiAnnualSlabFlag.children("input[type=text]").val());
				            		tdMinSemiAnnualSlabVol.html(tdMinSemiAnnualSlabVol.children("input[type=text]").val());
				            		tdMaxSemiAnnualSlabVol.html(tdMaxSemiAnnualSlabVol.children("input[type=text]").val());
				            		tdSemiAnnualSlabRate.html(tdSemiAnnualSlabRate.children("input[type=text]").val());
				            		tdAnnualSlabFlag.html(tdAnnualSlabFlag.children("input[type=text]").val());
				            		tdMinAnnualSlabVol.html(tdMinAnnualSlabVol.children("input[type=text]").val());
				            		tdMaxAnnualSlabVol.html(tdMaxAnnualSlabVol.children("input[type=text]").val());
				            		tdAnnualSlabRate.html(tdAnnualSlabRate.children("input[type=text]").val());
				            		tdProcessedVolTillDate.html(tdProcessedVolTillDate.children("input[type=text]").val());
				            		tdProcessedVolTillQtr.html(tdProcessedVolTillQtr.children("input[type=text]").val());
				            		tdProcessedVolTillSemiAnnual.html(tdProcessedVolTillSemiAnnual.children("input[type=text]").val());
				            		tdProcessedVolTillAnnual.html(tdProcessedVolTillAnnual.children("input[type=text]").val());
				            		tdProcessedVolUpdateDate.html(tdProcessedVolUpdateDate.children("input[type=text]").val());
				            		tdButtons.html("<img src='css/images/edit.png' class='btnEdit'/>"); 
				            		
			            			$(".btnEdit").bind("click", Edit); 
			            			
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
			    	                     
	            },
	            error:function(xhr,status)
	            {
	                 console.log(status);
	             }
	        });
				//alert(" Done "); 	     
	 });
	 
});
</script>
<form>
<img src="<%=request.getContextPath()%>/images/Experian_logo.png"/>
<div class="container" id="displayContainer">
<h3 class="heading">Search Contracts</h3>
		<div >
			<div class="labels"><span>Legal Entity:</span></div><select id="legalEntity"></select>
		</div>
		<div>
			<div class="labels"><span>Business Unit:</span></div><select id="businessUnit"></select>
		</div>
		<div>
			<div class="labels"><span>Client Name:</span></div><select id="clientName"></select>
		</div>
		<div>
			<div class="labels"><span>Client Id:</span></div><select id="clientId"></select>
		</div>
		<div>
			<div class="labels"><span>Client Location No:</span></div><select id="clientlocationNo"></select>
		</div>
		<div>
			<div class="labels"><span>Product ID:</span></div><select id="productID"></select>
		</div>
		<div>
		<table align="center" >
		<tr>
			<td><div class="buttondiv"><input type="button" name="submitBtn" id="submitBtn" value="Submit" style="float: right;"/></div></td>
			<td><div class="buttondiv"><input type="button" name="addBtn" id="addBtn" value="Add" onclick="javascript:window.location='ContractInput.jsp'" style="float: right;"/></div></td>
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
		<th class="th" scope="col">CLIENT NAME</th>
		<th class="th" scope="col">PRODUCT NAME</th>
		<th class="th" scope="col">CATEGORY</th>
		<th class="th" scope="col">CONTRACT START DATE</th>
		<th class="th" scope="col">CONTRACT END DATE</th>
		<th class="th" scope="col">AR PROCESS DATE</th>
		<th class="th" scope="col">ONE TIME SETUP FLAG</th>
		<th class="th" scope="col">ONE TIME SETUP CHARGE DATE</th>
		<th class="th" scope="col">ONE TIME SETUP FEE</th>
		<th class="th" scope="col">ADDITIONAL SERVICE CHARGES</th>
		<th class="th" scope="col">EARLY TERMINATION FLAG</th>
		<th class="th" scope="col">EARLY TERMINATION DATE</th>
		<th class="th" scope="col">EARLY TERMINATION MINIMUM VOLUME</th>
		<th class="th" scope="col">PRO RATA CHARGES FOR TERMINATION</th>
		<th class="th" scope="col">NO MONTHS OF COMMITMENT</th>
		<th class="th" scope="col">COMMITMENT QTY</th>
		<th class="th" scope="col">DISCOUNT RATE FLAG</th>
		<th class="th" scope="col">DISCOUNT START DATE</th>
		<th class="th" scope="col">DISCOUNT END DATE</th>
		<th class="th" scope="col">DISCOUNT RATE</th>
		<th class="th" scope="col">DISCOUNT EXTENSION FLAG</th>
		<th class="th" scope="col">DISCOUNT EXTENSION END DATE</th>
		<th class="th" scope="col">SPECIAL RATE FLAG</th>
		<th class="th" scope="col">SPECIAL START DATE</th>
		<th class="th" scope="col">SPECIAL END DATE</th>
		<th class="th" scope="col">SPECIAL RATE</th>
		<th class="th" scope="col">WARM UP FLAG</th>
		<th class="th" scope="col">WARM UP START DATE</th>
		<th class="th" scope="col">WARM UP END DATE</th>
		<th class="th" scope="col">WARM UP EXTENSION FLAG</th>
		<th class="th" scope="col">WARM UP EXTENSION END DATE</th>
		<th class="th" scope="col">MONTHLY SLAB FLAG</th>
		<th class="th" scope="col">MIN MONTHLY SLAB VOLUME</th>
		<th class="th" scope="col">MAX MONTHLY SLAB VOLUME</th>
		<th class="th" scope="col">MONTHLY RATE</th>
		<th class="th" scope="col">QUARTERLY SLAB FLAG</th>
		<th class="th" scope="col">MIN QUARTERLY SLAB VOLUME</th>
		<th class="th" scope="col">MAX QUARTERLY SLAB VOLUME</th>
		<th class="th" scope="col">QUARTERLY RATE</th>
		<th class="th" scope="col">SEMI ANNUAL SLAB FLAG</th>
		<th class="th" scope="col">MIN SEMI ANNUAL SLAB VOLUME</th>
		<th class="th" scope="col">MAX SEMI ANNUAL SLAB VOLUME</th>
		<th class="th" scope="col">SEMI ANNUAL SLAB RATE</th>
		<th class="th" scope="col">ANNUAL SLAB FLAG</th>
		<th class="th" scope="col">MIN ANNUAL SLAB VOLUME</th>
		<th class="th" scope="col">MAX ANNUAL SLAB VOLUME</th>
		<th class="th" scope="col">ANNUAL SLAB RATE</th>
		<th class="th" scope="col">PROCESSED VOLUME TILL DATE</th>
		<th class="th" scope="col">PROCESSED VOLUME QUARTER</th>
		<th class="th" scope="col">PROCESSED VOLUME SEMI ANNUAL</th>
		<th class="th" scope="col">PROCESSED VOLUME ANNUAL</th>
		<th class="th" scope="col">PROCESSED VOLUME UPDATE DATE</th>	
    </tr> 
</table>
</div>
</form>
<jsp:include page="footer.jsp"/>
</body>
</html>