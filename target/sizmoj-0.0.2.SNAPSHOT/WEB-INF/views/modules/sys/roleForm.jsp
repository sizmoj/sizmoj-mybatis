<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/layouts/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="${ctx}/static/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/static/jquery-ztree/3.5.12/js/jquery.ztree.core-3.5.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-ztree/3.5.12/js/jquery.ztree.excheck-3.5.min.js" type="text/javascript"></script>

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
$(document).ready(function(){
	var setting = {check:{enable:true,nocheckInherit:true},view:{selectedMulti:false},
			data:{simpleData:{enable:true}},callback:{beforeClick:function(id, node){
				tree.checkNode(node, !node.checked, true, true);
				return false;
			}}};
	
	// 用户-菜单
	var zNodes=[
			<c:forEach items="${menuList}" var="menu">{id:${menu.id}, pId:${not empty menu.parent.id?menu.parent.id:0}, name:"${not empty menu.parent.id?menu.name:'权限列表'}"},
            </c:forEach>];
	// 初始化树结构
	var tree = $.fn.zTree.init($("#menuTree"), setting, zNodes);
	// 默认选择节点
	var ids = "${role.menuIds}".split(",");
	for(var i=0; i<ids.length; i++) {
		var node = tree.getNodeByParam("id", ids[i]);
		try{tree.checkNode(node, true, false);}catch(e){}
	}
	// 默认展开全部节点
	tree.expandAll(true);	
	$("#submitbtn").bind("click",function(){
		var ids = [], nodes = tree.getCheckedNodes(true);
		for(var i=0; i<nodes.length; i++) {
			ids.push(nodes[i].id);
		}
		$("#menuIds").val(ids);
		$("#inputForm").submit();
	});
});
</script>
</head>
<body>
<form:form id="inputForm" modelAttribute="role" action="${ctx}/sys/role/save" method="post" class="navbar-form navbar-left" cssStyle="padding-left:20px">
  <c:if test="${not empty role.id}">
  <form:hidden path="id"/>
  </c:if>
<div class="input-group">
  <span class="input-group-addon"> 角色名称:</span>
  <form:input path="name" htmlEscape="false" maxlength="50" class="required form-control"/>
</div>
<br/>
<div class="input-group">
  <span class="input-group-addon"> 英文名称:</span>
  <form:input path="enname" htmlEscape="false" maxlength="50"  class="form-control"/>
</div>
<br/>
<div class="input-group">
<span class="input-group-addon"> 角色授权:</span>
	<form:hidden path="menuIds"/>
	<div id="menuTree" class="ztree" style="margin-top:3px;float:left;"></div>
</div>
<br/>
<shiro:hasPermission name="sys:role:edit">
<button type="button" id="submitbtn" class="btn btn-primary">
  修改
</button>
</shiro:hasPermission>
<button type="button" class="btn btn-default" onclick="history.go(-1)">放回</button>
</form:form>
</body>
</html>