  <h3>文章分类</h3>
  <div class="con">
  	<div class="tag_list">
  		<ul class="clearfix">
  		<#list tags as tag>
        	<li>
          		<a class="tag" href="${request.contextPath}/f/tag/${tag.id}"><span>${tag.name}</span></a>
         	 	X <span>${tag.post?size}</span>
       		 </li>
        </#list>
      </ul>
  	</div>
  </div>