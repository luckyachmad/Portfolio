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
$(document).ready(function() {
	$("#uploadPicture").click(function(){
		$(this).parents("form").trigger("submit");
	}) });
</script>
</head>
<body>
	<form action="<c:url value="/master/admin/detailProduct"/>"  enctype="multipart/form-data" method="post">
		<input name="mode" value="${mode}" type="hidden">
		
		<div id="container-full">
			<h2>Product</h2>
			<div id ="main-full" >
			<jsp:include page="../../message.jsp">
					<jsp:param value="customer" name="command"/>
				</jsp:include>
				
				<h3>Detail Produt</h3>
				<fieldset>
					<table>
						<tr>
							<td>
								<label>ID</label>
								<c:if test="${mode == 0}">
									<input type="text" class="input" name="id" value="${idGenrator}" />
								</c:if>
								<c:if test="${mode == 1}">
									<input class=input-disable name="id" value="${product.id}" readonly="readonly"/>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>
								<label>Name</label>
                	 			<input type="text" class="input" name="name" size="40" value="${product.name}"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>Jenis</label>
                	 			<input type="text" class="input" name="jenis" size="40" value="${product.jenis}"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>Type</label>
                	 			<input type="text" class="input" name="type" size="40" value="${product.type}"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>Description</label>
                	 			<input type="text" class="input" name="description" size="40" value="${product.description}"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>Price</label>
                	 			<input type="text" class="input" name="price" size="40" value="${product.price}"/>
							</td>
						</tr>	
						<tr>
							<td>
								<label>Stock</label>
                	 			<input type="text" class="input" name="stock" size="40" value="${product.stock}"/>
							</td>
						</tr>	
						
						<tr>
							<td>
							File: <input type="file" name="file" class="smallInput"/> &nbsp; &nbsp;
								<!-- <input type="submit" id="uploadPicture" value = "submit" /> -->
									<br/>Note: Max 2MB, Format: jpg, png, gif
							</td>
						</tr>
					</table>
					<br/><br/>
					<input type="submit"id="uploadPicture" value="Simpan" class="button"/>
				</fieldset>
				<a class="button" href="<c:url value="/master/admin/searchProduct"/>">Kembali ke list Product</a>
			</div>
			<div class="clear"></div>
		</div>
	</form>
	
</body>
</html>