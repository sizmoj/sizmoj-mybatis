<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/layouts/taglib.jsp"%>
<html>
<head>
	<title>日志管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/log/">日志列表</a></li>
	</ul>
	<form:form id="searchForm" action="${ctx}/sys/log/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div>
			<label>用户ID：</label><input id="createById" name="createById" type="text" maxlength="50" class="input-medium" value="${createById}"/>
			<label>URI：</label><input id="requestUri" name="requestUri" type="text" maxlength="50" class="input-medium" value="${requestUri}"/>
			&nbsp;<label for="exception"><input id="exception" name="exception" type="checkbox"${exception eq '1'?' checked':''} value="1"/>只查询异常信息</label>
		</div><div style="margin-top:8px;">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
		</div>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>操作用户</th><th>URI</th><th>提交方式</th><th>操作者IP</th><th>创建时间</th></thead>
		<tbody>
		<c:forEach items="${page.list}" var="log">
			<tr>
				<td>${log.createBy.name}</td>
				<td><strong>${log.requestUri}</strong></td>
				<td>${log.method}</td>
				<td>${log.remoteAddr}</td>
				<td><fmt:formatDate value="${log.createDate}" type="both"/></td>
			</tr>
			<tr>
				<td colspan="8">用户代理: ${log.userAgent}<br/>提交参数: ${log.params}
				<c:if test="${not empty log.exception}"><br/>异常信息: <br/><%request.setAttribute("strEnter", "\n"); %>
				</c:if></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>