<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
$(document).ready(function() {
	$("#menu_user").addClass("current");
	
	$("#btn-save").click(function(){
		var okToSubmit = true;
			
		      		
		if(okToSubmit) {
				$("#form").trigger("submit");
		}
	});
<c:forEach var="role" items="${user.roles}">
$(":input[value='${role.id}']").attr("checked", true);
</c:forEach>
});
</script>
</head>
<body>
<form id="form" action="<c:url value="/master/user/saveUser"/>" method="post">
	
	<input type="text" name="id" value="${user.id}"/>
		<div id="container-full">
		<div id = "main-full">
		<h3>Premission</h3>
			<fieldset>				
				<table >
				<tr>
					<td width="10%">NPK</td>
					<td width="30%">: ${employee.id}</td>
					<td width="10%">Nama</td>
					<td width="20%">: ${employee.name}</td>
					<td width="10%">Enabled</td>
					<td width="20%">: ${user.enable}</td>
					
				</tr>
				</table>
			</fieldset>
			<fieldset>
			<table>
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
					<input type="submit" id="btn-save" value = "submit" />
					</td>
				</tr>
				</table>
			</fieldset>
		</div>
		
		<div class="clear"></div>
	</div>
	
	</form>
</body>
</html>