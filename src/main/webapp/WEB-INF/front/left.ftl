<#list page.list as post>
<div class="con">
	<div class="tit clearfix">
    <span class="blog_icon" title="博客文章"></span>
    <a href="${request.contextPath}/f/detail/${post.id}">${post.postTitle}</a>
  </div>
  <div class="text">
		<div class="infor_tag">    
  		<#list post.tags as tag>
			<a class="tag" rel="tag" href="${request.contextPath}/f/tag/${tag.id}"><span>${tag.name}</span></a>
		</#list>
		</div>
		<#if post.content?length gt 150>
			${post.content?substring(0, 150)}...
		<#else>	
			${post.content}
		</#if> 		
  </div>
  <div class="info clearfix">
    <div class="fr">
	  <span class="author">${post.author.name}</span>
      <span class="time">${post.publicDate?string("yyyy-MM-dd")}发表</span>
      <span class="edit">${post.postModified?string("yyyy-MM-dd")}更新</span>
      <span class="views" title="浏览次数">未知</span>
      <a class="comment" title="评论" href=#>${post.commentCount}</a>
    </div>
  </div>
</div>
</#list>
<div>
    <div class="pagination">${page}</div>
</div>
<form id="searchForm" action="${request.contextPath}/${pageurl}" method="post">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
</form>
  <script type="text/javascript">
  $(function(){
    $('div.pagination').append('共计${page.count}篇文章');
  });
  function page(n,s){
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#searchForm").submit();
	    return false;
	}
  </script>