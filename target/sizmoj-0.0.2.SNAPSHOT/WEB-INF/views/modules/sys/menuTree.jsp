<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>菜单导航</title>
	<style type="text/css">
	    .container { width: 1300px; } .level1 { } .level2 { }
	</style>
	<script type="text/javascript">
	    $(document).ready(function() {
	        $(".level1 > a").click(function() {
	            $(this).next().show().parent().siblings().children("a").next().hide();
	        });
	    });
	</script>
	<style type="text/css">
		#footer {
			 display: none;
			
		}
		body {
			overflow-x : hidden; 	
		}
	</style>
</head>
<body>
<div class="bs-sidebar hidden-print" id="menutree">
	<c:set var="menuList" value="${fns:getMenuList()}"/>
	<c:set var="firstMenu" value="true"/>
    <ul class="nav bs-sidenav">
	<c:forEach items="${menuList}" var="menu" varStatus="idxStatus">
	<c:if test="${menu.parent.id eq (not empty param.parentId?param.parentId:1)&&menu.isShow eq 1}">
        <li class="level1">
            <a href="#overview">
                	${menu.name}
            </a>
            <ul class="nav level2">
            <c:forEach items="${menuList}" var="childMenu" varStatus="ids">
            <c:if test="${childMenu.parent.id eq menu.id && childMenu.isShow eq 1}">
                <li>
                    <a href="${fn:indexOf(childMenu.href, '://') eq -1?ctx:''}${childMenu.href ? childMenu.href : childMenu.href}" target="${not empty childMenu.target?childMenu.target:'mainFrame'}">
                        ${childMenu.name}
                    </a>
                </li>
            <c:set var="firstMenu" value="false"/>
            </c:if>
            </c:forEach>
            </ul>
        </li>
    </c:if>
    </c:forEach>
    </ul>
  </div>
</body>
</html>
