<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>菜单管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctx}/static/treeTable/themes/vsStyle/treeTable.min.css" rel="stylesheet" type="text/css" />
	<script src="${ctx}/static/treeTable/jquery.treeTable.min.js" type="text/javascript"></script>
	<style type="text/css">.table td i{margin:0 2px;}</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel : 3});
		});
	</script>
	<style type="text/css">
		.modal-main {
    		padding: 20px;
    		position: relative;
		}
	</style>
</head>
<body>
<table id="treeTable" class="table table-hover .table-condensed">
    <thead>
        <tr>
            <th>名称</th>
            <th>链接</th>
            <th>排序</th>
            <th>可见</th>
            <th>权限标识</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${list}" var="menu">
        <tr id="${menu.id}" pId="${menu.parent.id ne 1?menu.parent.id:'0'}">
            <td>${menu.name}</td>
            <td>${menu.href}</td>
         	<td>${menu.sort}</td>
            <td>${menu.isShow eq 1?'显示':'隐藏'}</td>
            <td>${menu.permission}</td>
            <shiro:hasPermission name="sys:menu:edit">
            <td>
				<a href="${ctx}/sys/menu/form?id=${menu.id}">修改</a>
				<a href="${ctx}/sys/menu/delete?id=${menu.id}">删除</a>
				<a href="${ctx}/sys/menu/form?parent.id=${menu.id}">添加下级菜单</a> 
			</td>
			</shiro:hasPermission>
        </tr>           
        </c:forEach>
    </tbody>
</table>
</body>
</html>
