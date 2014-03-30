]<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/layouts/taglib.jsp"%>
<html>
<head>
	<title>角色管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
        .input-group .form-control {
   			 margin-bottom: 0;
    		 width: 120px;
		}
		body {
    		 overflow-x : hidden;  
     		 overflow-y : hidden; 
		}
        </style>
        <script type="text/javascript">
	        function page(n,s){
				$("#pageNo").val(n);
				$("#pageSize").val(s);
				$("#searchForm").attr("action","${ctx}/sys/user/");
				$("#searchForm").submit();
		    	return false;
		    }
        </script>
</head>
<body>
<form class="form-inline breadcrumb" role="form">
  <button type="submit" class="btn btn-primary">添加</button>
</div>    
</form>
<table class="table table-hover .table-condensed">
    <thead>
        <tr>
			<th>角色名称</th>
            <th>英文名称</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
	<c:forEach items="${list}" var="role" varStatus="ids">
        <tr>
            <th>${role.name }</th>
            <th>${role.enname }</th>
            <shiro:hasPermission name="sys:role:edit">
            <td>
				<a href="${ctx}/sys/role/form?id=${role.id}">修改</a>
				<a href="${ctx}/sys/role/delete?id=${role.id}">删除</a>
			</td>
			</shiro:hasPermission>
        </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>