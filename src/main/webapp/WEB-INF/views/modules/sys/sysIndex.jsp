<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理页面</title>
<script type="text/javascript">
$(document).ready(function() {
	$("#menu a.menu").click(function(){
		$("#menu li.menu").removeClass("active");
		$(this).parent().addClass("active");
	});
});
</script>
<style type="text/css">
	#footer{
		display:block;
	}
</style>
</head>
<body>
 <!-- Docs master nav -->
        <header class="navbar navbar-inverse navbar-fixed-top bs-docs-nav" role="banner">
            <div class="container">
                <div class="navbar-header">
                    <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
                        <span class="sr-only">
                            Toggle navigation
                        </span>
                        <span class="icon-bar">
                        </span>
                        <span class="icon-bar">
                        </span>
                        <span class="icon-bar">
                        </span>
                    </button>
                    <a href="../" class="navbar-brand">
                        Bootstrap
                    </a>
                </div>
                <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
                    <ul id="menu" class="nav navbar-nav">
                    	<c:set var="firstMenu" value="true"/>
                    	<c:forEach items="${fns:getMenuList()}" var="menu" varStatus="idxStatus">
                    	<c:if test="${menu.parent.id eq 1 && menu.isShow eq 1}">
                    		<li class="menu ${firstMenu?' active':''}"><a class="menu" href="${ctx}/sys/menu/tree?parentId=${menu.id}" target="menuFrame" >${menu.name}</a></li>
							<c:if test="${firstMenu}">
								<c:set var="firstMenuId" value="${menu.id}"/>
							</c:if>
						<c:set var="firstMenu" value="false"/>
                    	</c:if>
                        </c:forEach>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="${ctx}/logout">
                                                                                         退出
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </header>
        <div class="container bs-docs-container">
            <div class="row">
                <div class="col-md-2">
                 <iframe id="menuFrame" name="menuFrame" src="${ctx}/sys/menu/tree?parentId=${firstMenuId}" style="overflow:visible;"
						scrolling="no" frameborder="no" width="100%" height="650"></iframe>
				</div>
                <div class="col-md-9" role="main">
                	<iframe id="mainFrame" name="mainFrame" src="${ctx}/blog/comment" style="overflow:visible;"
						scrolling="yes" frameborder="no" width="100%" height="750"></iframe>
                </div>
            </div>
        </div>
    </body>
</body>
</html>