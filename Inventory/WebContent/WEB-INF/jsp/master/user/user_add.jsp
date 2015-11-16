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
	<form action="<c:url value="/master/user/saveEmployee"/>" method="post">
		
		<div id="container-full">
			<h2>Employee</h2>
			<div id = "main-full">
				
				<jsp:include page="../../message.jsp">
					<jsp:param value="customer" name="command"/>
				</jsp:include>
				
				<h3>Detail Employee</h3>
				<fieldset>
					<table>
						<tr>
							<td>
								<label>ID</label>
								
									<input type="text" class="input" name="id" value="" />
							
							</td>
						</tr>
						<tr>
							<td>
								<label>Nama</label>
                	 			<input type="text" class="input" name="name" size="40" value="${employee.name}"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>Alamat</label>
                	 			<input type="text" class="input" name="alamat" size="40" value="${employee.alamat}"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>No Telepon</label>
                	 			<input type="text" class="input" name="noTlp" size="40" value="${employee.noTlp}"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>Email</label>
                	 			<input type="text" class="input" name="email" size="40" value="${employee.email}"/>
							</td>
						</tr>
						<tr>
							<td>
								<label>Gender</label>
                	 			<input type="radio" name="gender" value="Laki laki" <c:if test="${employee.gender}">checked="checked"</c:if>/> Laki laki
								<input type="radio" name="gender" value="Perempuan" <c:if test="${not empty employee.gender && !employee.gender}">checked="checked"</c:if>/> Perempuan
                	 			
							</td>
						</tr>
						<tr>
							<td>
								<label>Password</label>
                	 			<input type="password" class="input" name="password" size="40" value="${user.password}"/>
							</td>
						</tr>
						<tr>
							<td width="30%">
								<label>Access</label>
								<input type="checkbox" name="roles" value="ROLE_SA"/> Administrator<br/>
								<input type="checkbox" name="roles" value="ROLE_USER"/> User<br/>
								<input type="checkbox" name="roles" value="ROLE_SUPER"/> Super Aministrator<br/>
							</td>
						</tr>
						<tr>
							<td>
								<label>Status</label>
								<input type="radio" name="enable" value="1" <c:if test="${employee.enable}">checked="checked"</c:if>/> Aktif
								<input type="radio" name="enable" value="0" <c:if test="${not empty employee.enable && !employee.enable}">checked="checked"</c:if>/> Tidak Aktif
                	 			
							</td>
						</tr>
					</table>
					<br/><br/>
					<input type="submit" value="Simpan" class="button"/>
				</fieldset>
				<a class="button" href="<c:url value="/master/user/user_search"/>">Back</a>
			</div>
			<div class="clear"></div>
		</div>
	</form>
</body>
</html>