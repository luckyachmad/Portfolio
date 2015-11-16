<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="grid_16" align="center">
		<div style="height: 34px; display: block;">&nbsp;</div>	
		<div class="applicationLink">
			<ul>
			<!--[if IE 6]>
				<li class="single-link" style="width: 1px;"></li>
			<![endif]-->
				<li class="single-link" style="display: inline; width: 100px;" id="link_all"><a href="<c:url value="/master/menu"/>">INVENTORY</a></li>
				
				<li><a class="">Aplikasi Lainnya &raquo;</a>
					<ul>
					<sec:authorize url="/admin/*">
						<li style="border-bottom: 1px solid #e5e5e5;"><a href="<c:url value="/admin/home/"/>" style="width: 180px;">Administration</a></li>
						<!-- li><div class="mid-line"> </div></li -->
					</sec:authorize>
						<li><a href="<c:url value="/services/home/"/>" style="width: 180px;">HelpDesk Service</a></li>
						<li><a href="<c:url value="/meeting/home/"/>" style="width: 180px;">Meeting Room</a></li>
					</ul>
				</li>
				
				<li class="right">
					<div id="user_tools">
						<span><a href="<c:url value="/j_spring_security_logout"/>" class="logout">Logout</a></span>
						<span><a href="<c:url value="/password/change"/>">Ganti Password</a></span>
						<span><!-- a href="#" class="mail">(1)</a  --> Selamat Datang - NPK: <em style="color: #FFF;">${loginEmployee.id}</em>, Nama: <em style="color: #FFF;">${loginEmployee.name}</em></span>
					</div>
				</li>
			</ul>
		</div>
    </div>