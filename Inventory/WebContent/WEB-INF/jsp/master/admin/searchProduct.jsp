<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	
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
<form id="form" action="<c:url value="/master/admin/searchProduct"/>" method="post">
	<input type="hidden" name="offset" value="${pagingInfo.offset}"/>
	<input type="hidden" name="pageSize" value="${pagingInfo.pageSize}"/>
	<input type="hidden" name="totalRows" value="${pagingInfo.totalRows}"/>
	
	<div id="container-full">
		<h2>Product</h2>
		
		<div id="main-full">
					
			<h3>Kriteria Pencarian</h3>
			<fieldset>
				<table width="100%">
					<tr>
						<td>
							<label>ID:</label>
							<input type="text" name="id" class="input" size="50" value=""/>
						</td>
						<td>
							<label>Name Product:</label>
							<input type="text" name="name" class="input" size="50" value=""/>
						</td>
					</tr>
					<tr>
						<td>
							<label>Code:</label>
							<input type="text" name="code" class="input" size="50" value=""/>
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
							<th>Code Product</th>
							<th>Name</th>
							<th>Jenis</th>
							<th>type</th>
							<th>Description</th>
							<th>Price</th>
							<th>Stock</th>
							<th>Image</th>							
						</tr>
					</thead>
					<tbody>
					<c:forEach var="product" items="${product}" varStatus="status">
						<tr>
							<td align="right">${status.index + pagingInfo.offset + 1}</td>
							<td><a class="view" href="<c:url value="/master/admin/detailProduct?id=${product.id}"/>">${product.id}</a></td>
							<td>${product.name}</td>
							<td>${product.jenis}</td>
							<td>${product.type}</td>
							<td>${product.description}</td>
							<td>${product.price}</td>
							<td>${product.stock}</td>	
							<c:if test="${product.map.product != null}">
							<td><a target="_blank" href="<c:url value="/${product.map.folder}${product.map.fileName }"/>">view</a> </td>						
							</c:if>
						</tr>
					</c:forEach>
					<c:if test="${empty product}">
               	 		<tr>
               	 			<td colspan="9" align="center">no record(s) found.</td>
               	 		</tr>
               	 	</c:if>
					</tbody>
				</table>
			</fieldset>
			
			<a class="button" href="<c:url value="/master/admin/detailProduct"/>">Create Product</a>
			<br/><br/>
			
		</div>
		<div class="clear"></div>
	</div>
</form>

</body>
</html>
