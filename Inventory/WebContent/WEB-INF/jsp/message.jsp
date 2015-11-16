<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>
                  
                  <!-- Response message -->
                  <c:if test="${not empty message}">
                  	<h4 class="message">Message</h4>
                  	<ul>
                  	<c:if test="${message.type == 'info'}">
                  		<li class="warning"><c:out value="${message.text}"/></li>
                  	</c:if>
                  	
                  	<c:if test="${message.type == 'success'}">
                  		<li class="warning"><c:out value="${message.text}"/></li>
                  	</c:if>
                  	
                  	<c:if test="${message.type == 'warning'}">
                  		<li class="warning"><c:out value="${message.text}"/></li>
                  	</c:if>
                  	
                  	<c:if test="${message.type == 'error'}">
						<li class="warning"><c:out value="${message.text}"/></li>
                  	</c:if>
                  	</ul>
                  </c:if>
                  

                  