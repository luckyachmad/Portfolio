<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Insert title here</title>
<link href="<c:url value="/resources/css/pagination.css"/>" rel="stylesheet" type="text/css" media="screen" />
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.pagination.js"/>"></script>
	
	<script type="text/javascript">
	   	var totalRows;
		var pageSize;
		var currentPage;
	
		var isFirstLoad = true;
	
		function pageSelectCallback(page_index, jq){
		   	if (isFirstLoad) {
		   		isFirstLoad = false;
		   		return false;
		   	}
		   	
		   	$("input[name='offset']").val(page_index * pageSize);
		   	$("input[name='output']").val("");

	        $("#form").trigger("submit");
	    }
    
        $(document).ready(function() {

            totalRows = $("input[name='totalRows']").val();
			pageSize = $("input[name='pageSize']").val();
			currentPage = $("input[name='offset']").val() / pageSize;

			$(".pagination").pagination(totalRows, 
	                {callback:pageSelectCallback, 
	        		items_per_page:pageSize,
	        		current_page:currentPage,
	        		num_edge_entries:2});
        });
    </script>
</head>
<body>
	<form id="form" action="<c:url value="/master/admin/"/>" method="post">
	<input type="hidden" name="offset" value="${pagingInfo.offset}"/>
	<input type="hidden" name="pageSize" value="${pagingInfo.pageSize}"/>
	<input type="hidden" name="totalRows" value="${pagingInfo.totalRows}"/>
	
	<div id="container-full">
		<h2>Employee</h2>
		
		<div id="main-full">
			
				<jsp:include page="../../message.jsp">
					<jsp:param value="customer" name="command"/>
				</jsp:include>
			
			<h3>Kriteria Pencarian</h3>
			<fieldset>
				<table width="100%">
					<tr>
						<td>
							<label>ID:</label>
							<input type="text" name="id" class="input" size="50" value=""/>
						</td>
						<td>
							<label>Email:</label>
							<input type="text" name="email" class="input" size="50" value=""/>
						</td>
					</tr>
					<tr>
						<td>
							<label>Nama:</label>
							<input type="text" name="name" class="input" size="50" value=""/>
						</td>
						<td>
							<label>Alamat:</label>
							<input type="text" name="alamat" class="input" size="50" value=""/>
						</td>						
					</tr>
				</table>
				<br/><br/>
				<input type="submit" value="Search" class="button"/>
			</fieldset>
			
			<h3>Hasil Pencarian</h3>
			<fieldset>
				<div class="pagination"></div>
				<table class="second">
					<thead>
						<tr>
							<th>No</th>
							<th>ID</th>
							<th>Nama</th>
							<th>Access</th>
							
						</tr>
					</thead>
					<tbody>
					<c:forEach var="row" items="${user}" varStatus="status">
						<tr>
							<td align="right">${status.index + pagingInfo.offset + 1}</td>
							<td><a class="view" href="<c:url value="/master/user/open/${row[0].id}"/>">${row[0].id}</a></td>
							<td>${row[1]}</td>
							<td>
								<table width="100%">
								<c:forEach var="role" items="${row[0].roles}">
									<tr><td>&raquo; ${role.description}</td></tr>
								</c:forEach>
								</table>
							</td>							
						</tr>
					</c:forEach>
					<c:if test="${empty user}">
               	 		<tr>
               	 			<td colspan="9" align="center">no record(s) found.</td>
               	 		</tr>
               	 	</c:if>
					</tbody>
				</table>
			</fieldset>
			
			<a class="button" href="<c:url value="/master/user/user_add"/>">Create Employee</a>
			<br/><br/>
			
		</div>
		<div class="clear"></div>
	</div>
	
</form>
</body>
</html>