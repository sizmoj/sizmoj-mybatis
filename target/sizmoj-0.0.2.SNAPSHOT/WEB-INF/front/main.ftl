<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=2.0">
    <title>细枝末节的个人空间</title>
    <meta name="description" content="细枝末节的空间，记录个人轨迹,文档">
    <meta name="author" content="细枝末节">
    <link href="rss" rel="alternate" title="细枝末节的个人空间" type="application/rss+xml" />
    <link href="${request.contextPath}/static/front/stylesheets/default/document.css"  rel="stylesheet" type="text/css" />
	<link href="${request.contextPath}/static/front/stylesheets/default/content.css"  rel="stylesheet" type="text/css" />
	<script src="${request.contextPath}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="${request.contextPath}/static/front/js/application.js" type="text/javascript"></script>
	<script src="${request.contextPath}/static/front/js/jquery-ujs.js" type="text/javascript"></script>
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
    <div id="flash-notice"></div>
    <div class="header">
		<#include "/header.html">
    </div><!-- header -->
    
    <div class="nav">
      	<#include "/nav.ftl">
    </div><!-- nav -->
    
    <div class="wrap clearfix">
      
<div class="left">
	<#include "/left.ftl">
</div>
<!-- left content -->

<div class="right">
	<#include "/right.ftl">
</div><!-- right content -->
    </div><!-- content -->
    
    <div class="footer">
    	<#include "/footer.html">
    </div><!-- footer -->  
  </body>
</html>
