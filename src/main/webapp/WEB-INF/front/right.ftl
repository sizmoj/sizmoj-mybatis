<h3>文章分类</h3>
<div class="tag">
	<#list terms as term>	
		<a href="${request.contextPath}/f/term/${term.id}">${term.name}<span>${term.postCount}</span></a>
	</#list>
</div>
  
<h3>最新文章</h3>
<div class="hot_blog">
	<#list newPost as newpost>	
		<a href="${request.contextPath}/f/detail/${newpost.id}">${newpost.postTitle}</a>
	</#list>
</div>

<h3>最新评论</h3>
<ul class="comment">
	<#list newComment as newcomment>
		<li>
	    	<div><#if newcomment.text?length gt 15>
					${newcomment.text?substring(0, 15)}...
				<#else>	
					${newcomment.text}
				</#if>
			</div>
	  		${newcomment.author}评论了<a href="${request.contextPath}/f/detail/${newcomment.post.id}">${newcomment.post.postTitle}</a>
	  	</li>
	</#list>
</ul>
