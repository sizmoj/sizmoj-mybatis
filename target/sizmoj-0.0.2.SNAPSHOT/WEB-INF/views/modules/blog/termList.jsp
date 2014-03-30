<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/layouts/taglib.jsp"%>
<html>
<head>
	<title>评论管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
	body {
     overflow-x : hidden;  
     overflow-y : hidden; 
	}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/blog/term/">类别列表</a></li>
		<shiro:hasPermission name="blog:post:edit">
		<li><a href="${ctx}/blog/term/form">类别添加</a></li>
		</shiro:hasPermission>
	</ul>
	<table id="contentTable" class="table table-hover .table-condensed">
		<thead>
        <tr>
			<td>名字</td>
            <td>缩略名</td>
            <td>操作</td>
        </tr>
    </thead>
    <tbody>
		<c:forEach items="${terms}" var="term">
			<tr>
			<td>${term.name }</td>
            <td>${term.slug }</td>
				<td><shiro:hasPermission name="blog:post:edit">
					<a href="${ctx}/blog/term/delete?id=${term.id}">删除</a>
					<a href="${ctx}/blog/term/form?id=${term.id}">编辑</a>
					</shiro:hasPermission>
				</td>
			</tr>
			</c:forEach>
	</tbody>
	</table>
</body>
</html>
