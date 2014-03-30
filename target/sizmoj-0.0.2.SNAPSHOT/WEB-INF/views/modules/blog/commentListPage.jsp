<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/layouts/taglib.jsp"%>
<html>
<head>
	<title>评论管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
	<style type="text/css">
	body {
     overflow-x : hidden;  
     overflow-y : hidden; 
	}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/blog/comment/">留言列表</a></li>
		
	</ul>
	<table id="contentTable" class="table table-hover .table-condensed">
		<thead>
        <tr>
			<th>作者</th>
            <th>评论者邮箱</th>
            <th>内容</th>
            <th>评论者主页</th>
            <th>评论者内容</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
		<c:forEach items="${page.list}" var="comment">
			<tr>
			<th>${comment.author }</th>
            <th>${comment.email }</th>
            <th>${comment.text }</th>
            <th>${comment.url}</th>
            <th>${comment.text }</th>
            <th>操作</th>
				<td>
					<a href="${ctx}/blog/comment/delete?id=${comment.id}">删除</a>
				</td>
			</tr>
			</c:forEach>
	</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
