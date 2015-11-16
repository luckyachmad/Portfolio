<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value="/master/admin/detail"/>" method="post">
		<input name="mode" value="${mode}" type="hidden">
		
		<div id="container-full">
			<h2>Customer</h2>
			<div id = "main-full">
				
				<jsp:include page="../../message.jsp">
					<jsp:param value="customer" name="command"/>
				</jsp:include>
				
				<h3>Detail Customer</h3>
				<fieldset>
					<table>
						<tr>
							<td>
								<label>ID</label>
								<c:if test="${mode == 0}">
									<input type="text" class="input" name="id" value="${idGenrator}" />
								</c:if>
								<c:if test="${mode == 1}">
									<input class=input-disable name="id" value="${customer.id}" readonly="readonly"/>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>
								<label>Nama</label>
                	 			<input type="text" class="input" name="name" size="40" value="${customer.name}"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>Alamat</label>
                	 			<input type="text" class="input" name="alamat" size="40" value="${customer.alamat}"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>No Telepon</label>
                	 			<input type="text" class="input" name="No_tlp" size="40" value="${customer.no_tlp}"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>Email</label>
                	 			<input type="text" class="input" name="email" size="40" value="${customer.email}"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>Status</label>
								<input type="radio" name="enable" value="1" <c:if test="${customer.enable}">checked="checked"</c:if>/> Aktif
								<input type="radio" name="enable" value="0" <c:if test="${not empty customer.enable && !customer.enable}">checked="checked"</c:if>/> Tidak Aktif
                	 			
							</td>
						</tr>
					</table>
					<br/><br/>
					<input type="submit" value="Simpan" class="button"/>
				</fieldset>
				<a class="button" href="<c:url value="/master/admin/"/>">Kembali ke list Karyawan</a>
			</div>
			<div class="clear"></div>
		</div>
	</form>
</body>
</html>