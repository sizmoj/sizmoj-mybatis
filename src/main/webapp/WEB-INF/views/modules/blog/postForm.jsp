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
<form:form id="inputForm" modelAttribute="post" action="${ctx}/blog/post/save" method="post" class="navbar-form navbar-left" cssStyle="padding-left:20px">
  <c:if test="${not empty post.id}">
  	<form:hidden path="id"/>
  </c:if>
<br/>
<div class="input-group">
  <span class="input-group-addon">文章标题:${post.id }</span>
  <form:input path="postTitle" htmlEscape="false" maxlength="50" class="required form-control"/>
</div>
<br/>
<div class="input-group">
  <span class="input-group-addon">标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签:</span>
  <form:input path="tagsString" htmlEscape="false" maxlength="50" class="required form-control"/>
</div>
<br/>
<div class="input-group">
  <span class="input-group-addon">排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序:</span>
  <form:input path="menuOrder" htmlEscape="false" maxlength="50" class="required form-control"/>
</div>
<br/>
<div class="input-group">
	<label>文章分类：</label>
	<select name="term.id">
	<option value=""> </option>
	<c:forEach items="${terms}" var="term">
	<option value="${term.id}"<c:if test="${term.id eq post.term.id }">selected="selected"</c:if>>${term.name}</option>
	</c:forEach>	
	</select>
</div>
<br/>
<div class="input-group">
  <span class="input-group-addon">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:${post.status}</span>
  <div class="controls">
	 <form:radiobuttons path="status" items="${fns:getDictList('cms_del_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"  class="required"/>
  </div>
</div>
<br/>
<div class="input-group">
  <span class="input-group-addon">内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;容:</span>
  <div class="controls">
	 <form:textarea path="content" htmlEscape="false" maxlength="12000" cssStyle="height:200px; width:700px;" class="required form-control"/>
  </div>
</div>
<br/>
<br/>
<button type="submit" class="btn btn-primary">
  修改
</button>
<button type="button" class="btn btn-default" onclick="history.go(-1)">放回</button>
</form:form>
</body>
</html>