<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/layouts/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	.container {
    	width: 950px;
	}
	body {
		overflow-x : hidden; 	
	}
	input[type="radio"], input[type="checkbox"] {
    line-height: normal;
    margin: 4px 5px 0;
	}
</style>
<script type="text/javascript">
</script>
</head>
<body>
<form:form id="inputForm" modelAttribute="term" action="${ctx}/blog/term/save" method="post" class="navbar-form navbar-left" cssStyle="padding-left:20px">
  <c:if test="${not empty term.id}">
  	<form:hidden path="id"/>
  </c:if>
<br/>
<div class="input-group">
  <span class="input-group-addon">名字:${term.id }</span>
  <form:input path="name" htmlEscape="false" maxlength="50" class="required form-control"/>
</div>
<br/>
<div class="input-group">
  <span class="input-group-addon">缩&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;略:</span>
  <form:input path="slug" htmlEscape="false" maxlength="50" class="required form-control"/>
</div>
<br/>
<button type="submit" class="btn btn-primary">
  修改
</button>
<button type="button" class="btn btn-default" onclick="history.go(-1)">放回</button>
</form:form>
</body>
</html>