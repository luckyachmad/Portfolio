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
	<form action="<c:url value="/master/admin/saveSO"/>"  enctype="multipart/form-data" method="post">
		<input name="mode" value="${mode}" type="hidden">
		
		<div id="container-full">
			<h2>Sales Order</h2>
			<div id ="main-full" >
			<jsp:include page="../../message.jsp">
					<jsp:param value="customer" name="command"/>
				</jsp:include>
				
				<fieldset>			
				<table>
					<tr>
						<th>
							<label>No Purchase Order  :  </label>							
						</th>
							<c:if test="${mode == 0}">
								<th>
									<input type="text" class="input" name="id" value="${idGenerator}" />
								</th>
							</c:if>
							<c:if test="${mode == 1}">
								<th>
									<input type="text" class="input" name="id" value="${salesOrder.id}" />
								</th>
							</c:if>
						<th width="right">
							<label>Date  :</label>			                	
						</th>
						<th>
							<input type="text" class="input" name="tglSo" size="40" value="${salesOrder.tglSo}"/>
						</th>
					</tr>
					<tr>
						<th>
							<label>Sales Name  :</label>		                	
						</th>
						<th>
							<input type="text" class="input" name="name" size="40" value="${salesOrder.name}"/>
						</th>
					</tr>
				</table>		
			</fieldset>
			<h3>Detail Sales Order</h3>
				<fieldset>
					<table class="second" width="100%">
						<thead >
							<tr>
								<th>Product</th>
								<th>Harga</th>
								<th>Jumlah Product</th>
								<th>Total</th>
								<th>Image</th>								
							</tr>
						</thead>
						<tbody>
							<c:if test="${mode == 0}">
							<tr>
								<td>
									<input type="text" class="input" name="product.id" size="15" value=""/>
								</td>
								<td>
									<input type="text" class="input" name="harga" size="15" value=""/>
								</td>
								<td>
									<input type="text" class="input" name="jumlah" size="15" value=""/>
								</td>
								<td>
									<input type="text" class="input" name="total" size="15" value="20"/>
								</td>
								<td>
									<input type="file" name="file" class="smallInput"/> &nbsp; &nbsp;
									<br/>Note: Max 2MB, Format: jpg, png, gif
								</td>
								<input type="hidden" class="input" name="salesOrder.id" value="${idGenerator}" />
							</tr>
							<tr>
								<td>
									<input type="text" class="input" name="product.id" size="15" value=""/>
								</td>
								<td>
									<input type="text" class="input" name="harga" size="15" value=""/>
								</td>
								<td>
									<input type="text" class="input" name="jumlah" size="15" value=""/>
								</td>
								<td>
									<input type="text" class="input" name="total" size="15" value="10"/>
								</td>
								<td>
									<input type="file" name="file" class="smallInput"/> &nbsp; &nbsp;
									<br/>Note: Max 2MB, Format: jpg, png, gif
								</td>
								<input type="hidden" class="input" name="salesOrder.id" value="${idGenerator}" />
							</tr>
							<tr>
								<td>
									<input type="text" class="input" name="product.id" size="15" value=""/>
								</td>
								<td>
									<input type="text" class="input" name="harga" size="15" value=""/>
								</td>
								<td>
									<input type="text" class="input" name="jumlah" size="15" value=""/>
								</td>
								<td>
									<input type="text" class="input" name="total" size="15" value="10"/>
								</td>
								<td>
									<input type="file" name="file" class="smallInput"/> &nbsp; &nbsp;
									<br/>Note: Max 2MB, Format: jpg, png, gif
								</td>
								<input type="hidden" class="input" name="salesOrder.id" value="${idGenerator}" />
							</tr>
							</c:if>
							<c:if test="${mode == 1}">
							<c:forEach  var="sod" items="${salesOrder.salesOrderDetails}">
							<tr>
								<td>
									<input width="10%" type="text" class="input" name="product.id" size="20" value="${sod.product.name}"/>
								</td>
								<td>
									<input width="10%" type="text" class="input" name="harga" size="20" value="${sod.harga}"/>
								</td>
								<td>
									<input width="100px" type="text" class="input" name="jumlah" size="20" value="${sod.jumlah}"/>
								</td>
								<td>
									<input width="10px" type="text" class="input" name="total" size="20" value="${sod.total}"/>
								</td>
								<td>
									<a target="_blank" href="<c:url value="/${product.map.folder}${product.map.fileName }"/>">view</a>
								</td>
								<input type="hidden" class="input" name="salesOrder.id" value="${sod.salesOrder.id}" />
							</tr>
							</c:forEach>
							</c:if>
						</tbody>
								<tr>
									<td>
										<label>Total   :</label>
									</td>
									<td>
										<input type="text" class="input" readonly="readonly" name="totalJumlah" value="Rp.${total[0]}"/>
									</td>
								</tr>
					</table>
					<br/><br/>
					<input type="submit"id="uploadPicture" value="Simpan" class="button"/>
				</fieldset>
				<a class="button" href="<c:url value="/master/admin/searchSO"/>">Back</a>
			</div>
			<div class="clear"></div>
		</div>
	</form>
	
</body>
</html>