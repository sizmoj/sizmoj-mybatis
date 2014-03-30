<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/layouts/taglib.jsp"%>
<html>
<head>
	<title>文章管理</title>
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
		<li class="active"><a href="${ctx}/blog/post/">文章列表</a></li>
		<li><a href="${ctx}/blog/post/form">文章添加</a></li>
	</ul>
	<form:form id="searchForm" action="${ctx}/blog/post/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div>
			<label>文章分类：</label>
			<select name="termId">
			<option value=""> </option>
			<c:forEach items="${terms}" var="term">
			<option value="${term.id}">${term.name}</option>
			</c:forEach>
			</select>
			<label>文章标题：</label><input id="postTitle" name="postTitle" type="text" maxlength="50" class="input-medium" value="${postTitle}"/>
		</div><div style="margin-top:8px;">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
		</div>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-hover .table-condensed">
		<thead>
        <tr>
			<th>标题</th>
            <th>作者</th>
            <th>内容</th>
            <th>发布时间</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
		<c:forEach items="${page.list}" var="post">
			<tr>
				<td>${post.postTitle}</td>
				<td><strong>${post.author.name}</strong></td>
				<td>${fn:substring(post.content, 0, 20)}</td>
				<td><fmt:formatDate value="${post.publicDate}" type="both"/></td>
			
				<td>
					<a href="${ctx}/blog/post/form?id=${post.id}">修改</a>
					<a href="${ctx}/blog/post/delete?id=${post.id}">删除</a>
					<a href="${ctx}/blog/comment/list?id=${post.id}">查看留言</a>
				</td>
			
			</tr>
			</c:forEach>
	</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
