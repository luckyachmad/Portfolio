<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
	<!-- Le styles -->
    <link type="text/css" href='<c:url value = "/resources/css/bootstrap.css"/>' rel="stylesheet" />
    <style type="text/css">
      /* Override some defaults */
      html, body {
        background-color: #eee;
      }
      body {
        padding-top: 150px; 
      }
      .container {
        width: 300px;
      }

      /* The white background content wrapper */
      .container > .content {
        background-color: #fff;
        padding: 20px;
        margin: 0 -20px; 
        -webkit-border-radius: 10px 10px 10px 10px;
           -moz-border-radius: 10px 10px 10px 10px;
                border-radius: 10px 10px 10px 10px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.15);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.15);
                box-shadow: 0 1px 2px rgba(0,0,0,.15);
      }

	  .login-form {
		margin-left: 65px;
	  }
	
	  legend {
		margin-right: -50px;
		font-weight: bold;
	  	color: #404040;
	  }

    </style>
</head>
<body onload='document.f.j_username.focus();'>
	<div class="container">
		<div class="content">
			<div class="row">
				<div class="login-form">
	          		<h2>Login</h2>
	          		<form name='f' action="<c:url value='/j_spring_security_check' />" method='POST'>
		            	<fieldset>
		            		<label class="control-label">Username</label>
		              		<div class="clearfix">
		                		<input type= "text" name='j_username' />
		              		</div>
		              		<label class="control-label">Password</label>
		              		<div class="clearfix">
		                		<input type='password' name='j_password' />
		              		</div>
		              		<button class="btn primary" type="submit">Login</button>
		            	</fieldset>
	          		</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>