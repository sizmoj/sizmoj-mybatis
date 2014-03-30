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
<form:form id="inputForm" modelAttribute="menu" action="${ctx}/sys/menu/save" method="post" class="navbar-form navbar-left" cssStyle="padding-left:20px">
  <c:if test="${not empty menu.id}">
  <form:hidden path="id"/>
  </c:if>
  <form:hidden path="parent.id"/>
  <div class="input-group">
  <span class="input-group-addon">上级菜单:${menu.parent.id }</span>
  <form:input path="parent.name" htmlEscape="false" maxlength="50" readonly="true" class="required form-control"/>
</div>
<br/>
<div class="input-group">
  <span class="input-group-addon">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:</span>
  <form:input path="name" htmlEscape="false" maxlength="50" class="required form-control"/>
</div>
<br/>
<div class="input-group">
  <span class="input-group-addon">链&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;接:</span>
  <form:input path="href" htmlEscape="false" maxlength="50"  class="form-control"/>
</div>
<br/>
<div class="input-group">
  <span class="input-group-addon">目&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;标:</span>
  <form:input path="target" htmlEscape="false" maxlength="50" class="form-control"/>
</div>
<br/>
<div class="input-group">
  <span class="input-group-addon">排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序:</span>
  <form:input path="sort" htmlEscape="false" maxlength="50" class="form-control"/>
</div>
<br/>
<div class="input-group">
  <span class="input-group-addon">可&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;见:</span>
  <form:radiobuttons path="isShow" items="${fns:getDictList('show_hide')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
</div>
<br/>
<div class="input-group">
  <span class="input-group-addon">权限标识:</span>
  <form:input path="permission" htmlEscape="false" maxlength="100" class="form-control"/>
</div>
<br/>
<button type="submit" class="btn btn-primary">
  修改
</button>
<button type="button" class="btn btn-default" onclick="history.go(-1)">放回</button>
</form:form>
</body>
</html>