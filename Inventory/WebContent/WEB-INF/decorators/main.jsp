<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@page import="inv.com.util.SpringSecurityUtil"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Application</title>

    <script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.ui.core.js"/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/green.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/themes/default/easyui.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/themes/icon.css"/>" />
    
    
    <script type="text/javascript">
	$(document).ready(function() {
		
	});
    </script>
    
    <!-- CSS -->
	<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css" media="screen" />
	<link href="<c:url value="/resources/css/smoothness/ui.css"/>" rel="stylesheet" type="text/css" media="screen" />
	<!--[if IE 6]><link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/ie6.css"/>" /><![endif]-->
	<!--[if IE 7]><link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/ie7.css"/>" /><![endif]-->
	
    <decorator:head/>
</head>

<body>

	<div id="wrapper">
		<% if(SpringSecurityUtil.isAuthenticated()) { %>
			<c:if test="${empty param.output}">
			<jsp:include page="_link.jsp" />
			<br/>
			<br/>
			<br/>
		<ul id="mainNav">
			<li>
				<ul>
					<sec:authorize url="/master/admin/*">
					<li><a href="<c:url value="/master/admin/"/>">Customer</a></li>
					</sec:authorize>
					<sec:authorize url="/master/admin/*">
					<li><a href="<c:url value="/master/admin/searchSupplier"/>"><span>Supplier</span></a></li>
					</sec:authorize>
					<sec:authorize url="/master/admin/*">
					<li><a href="<c:url value="/master/admin/searchProduct"/>"><span>Product</span></a></li>
					</sec:authorize>
					<sec:authorize url="/master/admin/*">
					<li><a href="<c:url value="/master/admin/searchPO"/>"><span>Purchse Order</span></a></li>
					</sec:authorize>
					<sec:authorize url="/master/admin/*">
					<li><a href="<c:url value="/master/admin/searchSO"/>"><span>Sales Order</span></a></li>
					</sec:authorize>
					<sec:authorize url="/master/user/*">
					<li><a href="<c:url value="/master/user/user_search"/>"><span>Employee</span></a></li>
					</sec:authorize>
					<sec:authorize url="/master/admin/*">
					<li><a href="<c:url value="/master/admin/report"/>"><span>Report</span></a></li>
					</sec:authorize>
				</ul>
			</li>			
		</ul>
		
		<div id="containerHolder">
			<decorator:body/>
        </div>
        
        <p id="footer">
        	Theme: <a href="http://www.transdmin.perspectived.com/">transadmin</a>
        </p>
        </c:if>
<% } %>
	</div>
</body>
</html>
