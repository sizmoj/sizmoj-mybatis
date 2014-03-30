<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>登录页</title>
	<style>
	html{
		background: url(${ctx}/static/images/loginback.jpg) no-repeat center center fixed;
		-webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover;
	}
    .login-form {
        padding-top: 20px;
    }
    body {
		font-size: 16px;
		font-family: "Open Sans",serif;
		background: transparent;
    }
	h1 {
		font-family: "Abel", Arial, sans-serif;
		font-weight: 400;
		font-size: 40px;
       }
    .margin-base-vertical {
        margin: 40px 0;
    }
    .panel {
        background-color: rgba(255, 255, 255, 0.9);
    }
    #footer {
    	color:black;   	
    }
    #footer a{
    	color:black;
    	display:block;
    }
    #header
    {
		visibility: hidden
    }
	.alert-dismissable {
		padding-center:35px
	}
    </style>	
</head>

<body>
 <div id="header">
	<div id="title">
	    <h1><a href="${ctx}">sizmoj</a>
		</h1>
	</div>
</div>
	<div class="container login-form">
		<div class="row">
			<div class="col-md-6 col-md-offset-3 panel panel-default">
			<h1 class="help-block margin-base-vertical text-center">WELCOME</h1>
			<div class="alert alert-warning alert-dismissable">
		<%
		String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		if(error != null){
		%>
			

			  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			  <strong>Warning!</strong>密码错误,如果你是游客,请使用账户名密码:guest/guest


		<% } %>
			</div>
			    <form id="loginForm" action="${ctx}/login" method="post" class="margin-base-vertical">
			       <p class="input-group">
			           <span class="input-group-addon">用户名:</span>
			           <input type="text" class="form-control input-lg required" id="username" name="username"  value="${username}" placeholder="请输入用户名" />
			       </p>
			       <p class="input-group">
			           <span class="input-group-addon">密 &nbsp码:</span>
			           <input type="password" class="form-control input-lg required" name="password" id="password" placeholder="请输入密码" />
			       </p>
			       <p class="help-block text-center"><small>如果你是游客,请使用账户名密码:guest/guest</small></p>
			       <p class="text-center">
			           <button type="submit" class="btn btn-success btn-lg">登入</button>
			       </p>
			    </form>
		   </div>
	   </div>
    </div>
    	<script>
		$(document).ready(function() {
			$("#loginForm").validate();
		});
	</script>
</body>
</html>
