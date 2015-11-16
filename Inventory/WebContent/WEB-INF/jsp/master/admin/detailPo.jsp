<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value="/master/admin/detailPo"/>" method="post">
		<input name="mode" value="${mode}" type="hidden">
		
		<div id="container-full">
			<h2>Purchase Order</h2>
			<div id = "main-full">
				
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
									<input type="text" class="input" name="id" value="${po.id}" />
								</th>
							</c:if>
						<th width="right">
							<label>Date  :</label>			                	
						</th>
						<th>
							<input type="text" class="input" name="tglSo" size="40" value="${po.tglPo}"/>
						</th>
					</tr>
					<tr>
						<th>
							<label>Supplier Name  :</label>		                	
						</th>
						<th>
							<input type="text" class="input" name="supplier.id" size="40" value="${po.supplier.name}"/>
						</th>
					</tr>
				</table>		
			</fieldset>
				<h3>Detail Purchase Order</h3>
				<fieldset>
					<table  class="second" >
						<thead >
							<tr>
								<th>Product</th>
								<th>Harga</th>
								<th>Jumlah Product</th>
								<th>Total</th>															
							</tr>
						</thead>
						<tbody>
						<c:if test="${mode == 1}">
						<c:forEach items="${po.purchaseOrderDetails}" var="pod">
						<tr>
							<td>
								<input type="text" class="input" name="product.id" size="20" value="${pod.product.name}"/>
							</td>
							<td>
								<input type="text" class="input" name="harga" size="20" value="${pod.harga}"/>
							</td>
							<td>
								<input type="text" class="input" name="jumlah" size="20" value="${pod.jumlah}"/>
							</td>
							<td>
								<input type="text" class="input" name="total" size="20" value="${pod.total}"/>
							</td>
						</tr>	
													
								<input type="hidden" class="input" name="purchaseOrder.id" size="40" value="${pod.purchaseOrder.id}"/>
						</c:forEach>
						</c:if>
								<c:if test="${mode == 0}">	
								<tr>
									<td>
										<input type="text" class="input" name="product.id" size="20" value=""/>
									</td>
									<td>
										<input type="text" class="input" name="harga" size="20" value=""/>
									</td>
									<td>
										<input type="text" class="input" name="jumlah" size="20" value=""/>
									</td>
									<td>
										<input type="text" class="input" name="total" size="20" value=""/>
									</td>
									<input type="hidden" class="input" name="purchaseOrder.id" size="40" value="${idGenerator}"/>
								</tr>		
								
								<tr>
									<td>
										<input type="text" class="input" name="product.id" size="20" value=""/>
									</td>
									<td>
										<input type="text" class="input" name="harga" size="20" value=""/>
									</td>
									<td>
										<input type="text" class="input" name="jumlah" size="20" value=""/>
									</td>
									<td>
										<input type="text" class="input" name="total" size="20" value=""/>
									</td>
										<input type="hidden" class="input" name="purchaseOrder.id" size="40" value="${idGenerator}"/>
								</tr>								
								
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
					<table>
						<tr>
							<th width="right"></th>
						</tr>
					</table>
					<br/><br/>
					<c:if test="${mode == 0}">	
						<input type="submit" value="Check" class="button"/>
					</c:if>
					<c:if test="${mode == 1}">	
						<input type="submit" value="Save" class="button"/>
					</c:if>
				</fieldset>
				<a class="button" href="<c:url value="/master/admin/searchPO"/>">Back</a>
			</div>
			<div class="clear"></div>
		</div>
	</form>
</body>
</html>