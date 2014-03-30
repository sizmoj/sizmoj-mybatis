  
  <div class="con">
    
    <div class="content">
    	<div class="tit clearfix">
  			<span class='note_icon' title='学习笔记'></span>
        <h1><a href="${request.contextPath}/f/detail/${post.id}">${post.postTitle}</a></h1>
        <a class="markdown" title="Markdown格式原文" href="${request.contextPath}/f/postmd/${post.id}"></a>
      </div>
      <div class="info clearfix">
      	<#list post.tags as tag>
        <a class="tag" rel="tag" href="${request.contextPath}/f/tag/${tag.id}"><span>${tag.name}</span></a>
        </#list>
      </div>
      <div class="text">
		${post.content}
      </div>
      <div class="info clearfix">
        <div class="fr">
          <span class="author">${post.author.name}</span>
          <span class="time">${post.publicDate?string("yyyy-MM-dd")}发表</span>
          <span class="edit">${post.postModified?string("yyyy-MM-dd")}更新</span>
          <span class="views" title="浏览次数">未知</span>
        </div>
      </div>
    </div><!-- content -->  
    <div class="share clearfix">
      <span>
</span>
    </div><!-- share -->
    
    <div class="comment clearfix">
      <div id="comments">      
        <h2><span>${post.commentCount?default('0')}</span>条评论</h2>
        <ul>
<#list post.comments as comment>       
<li class="clearfix" id="430">
  <div class="user_img"></div>
  <div class="cot_con">
 		<div class="info">
      <span class="user_name">作者:${comment.author}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;主页:${comment.url}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱:${comment.email}</a></span>
      <span class="time">${comment.dateTime?string("yyyy-MM-dd")}</span>
    </div>
    <div class="comment_content">${comment.text}</div>
  </div>
</li>
</#list>
        </ul>
      </div>
      <a name="comments"></a>
       <div class="relative">
       <form id="addComment" name="addComment" action="../addComment" method="post">
      	 <input id="postId" name="postId" type="hidden"  value="${post.id}"/>
	       <p>
	    		<label for="author">昵称:</label>
	    		<input id="author" name="author" class="required" type="text" class=""/>
	   		 	<label for="email" >邮箱:</label>
	    		<input id="email" name="email" class="required email" type="text" />
	   		 	<label for="url">主页:</label>
	    		<input id="url" name="url" type="text" />
	    		<span style="float:right;">[<a href="http://wowubuntu.com/markdown/" target="_blank">评论可以使用Markdown格式</a>]</span>   		
	  		</p>
  			</br>      
			<textarea class="comment required" id="text" name="text" rows="" cols=""></textarea>
	        <input class="comment_btn" value="" type="submit" />  
          </form>    
      	</div><!-- relative -->
      
    </div><!-- comment -->
    <script type="text/javascript">
		$(document).ready(function(){
			$("#addComment").validate();
		});
	</script>
  </div><!-- con -->
