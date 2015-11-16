<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
   <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	
	
	$("#printInvestigationVendor").click(function(){
			// untuk mengeluarkan window baru untuk mengeluarkan report nya
    		window.open('<c:url value="/master/admin/suratTugasKerja"/>', 'suratTugasSurvey', "menubar=0,toolbar=0,scrollbars=yes,resizable=yes,status=yes");
		
	});
	
	$(document).ready(function() {
		$("#menu_report, #menu_report_helpdesk").addClass("current");
    	$("#submenu_report").show();
		
    	$("#export").click(function(){
    		$("#form").trigger("submit");
    	});
	});
	
	 $(document).ready(function() {
	$("#uploadPicture").click(function(){
		$(this).parents("form").trigger("submit");
	}) });

	
	function openPelaporan(){
		window.open('<c:url value="/master/admin/suratTugasKerja"/>', 'suratTugasSurvey', "menubar=0,toolbar=0,scrollbars=yes,resizable=yes,status=yes");
		}
	
	
	</script>
</head>
<body>
	<form action ="<c:url value="/master/admin/productReport/"/>" method = "post">
	<div id="container-full">
		
		<div id = "main-full">
			<fieldset>
			<table>
				<label>Report Product</label>
				<tr>
					<td width="30%">
						<label>Periode</label>
						<input type="text" class="easyui-datebox" name="startDate"/> to
						<input type="text" class="easyui-datebox" name="endDate"/>
					</td>
				</tr>
				<tr>
					<td>
					<input type="submit" onclick="openPelaporan();" value = "submit" />
					</td>
				</tr>
				</table>
			</fieldset>		
			<fieldset>
				<label>Report Sales Order</label>
				<table>
				<tr>
					<td width="30%">
						<label>Periode</label>
						<input type="text" class="easyui-datebox" name="startDate"/> to
						<input type="text" class="easyui-datebox" name="endDate"/>
					</td>
				</tr>
				<tr>
					<td>
					<input type="submit" onclick="openPelaporan();" value = "submit" />
					</td>
				</tr>
				</table>
			</fieldset>
			<fieldset>
				<label>Report Purchase Order</label>
				<table>
				<tr>
					<td width="30%">
						<label>Periode</label>
						<input type="text" class="easyui-datebox" name="startDate"/> to
						<input type="text" class="easyui-datebox" name="endDate"/>
					</td>
				</tr>
				<tr>
					<td>
					<input type="submit" onclick="openPelaporan();" value = "submit" />
					</td>
				</tr>
				</table>
			</fieldset>
		</div>
		
		<div class="clear"></div>
	</div>
	<!-- <table width="100%">
				<tr>
					<td width="30%">
						<label>Type</label>
						<select class="smallInput small" name="code">
							<option value="">-- Pilih --</option>
							<option value="REQUEST">Request</option>
							<option value="PROBLEM">Problem</option>
						</select>
					</td>
					<td width="30%">
						<label>Periode</label>
						<input type="text" class="easyui-datebox" name="startDate"/> to
						<input type="text" class="easyui-datebox" name="endDate"/>
					</td>
					<td width="30%">
						<label>&nbsp;</label>
						</td>
				</tr>
			</table>
			<input type="submit" onclick="openPelaporan();" value = "submit" />
			<a class="easyui-linkbutton" iconCls="icon-basic" onclick="openPelaporan();">testing</a>
</form>
<form action="<c:url value="/master/admin/upload/"/>" enctype="multipart/form-data" method="post">
				File: <input type="file" name="file" class="smallInput"/> &nbsp; &nbsp;
				<input type="submit" id="uploadPicture" value = "submit" />
				<a class="easyui-linkbutton" iconCls="icon-save" id="uploadPicture">Upload</a>
				<a target="_blank" href="<c:url value="/resources/upload/room/9d6ffedc.jpg"/>">view</a> |
				<br/>Note: Max 2MB, Format: jpg, png, gif -->
</form>
</body>
</html>