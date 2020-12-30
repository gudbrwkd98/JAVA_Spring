<%@page import="com.study.springfinal.domain.Gallery"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	Gallery gallery = (Gallery)request.getAttribute("gallery"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=button] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}

.reply-box{
	background-color: yellow;
}

.reply-list{
	overflow: hidden;
}

.reply-list p{
	float: left

}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.ckeditor.com/4.15.1/standard/ckeditor.js"></script>
<script>
$(function(){

	CKEDITOR.replace( 'content' );
	
	$($("input[type='button']")[0]).click(function(){
		edit();
	});
	
	$($("input[type='button']")[2]).click(function(){
		del();
	});
	
	getCommentList();
});

//글등록 요청
function edit(){
	$("form").attr({
		action:"/gallery/edit", //서블릿에게 요청
		method:"post"
	});
	$("form").submit();
	
}

function del(){
	if(confirm("삭제하시겠어요?")){
		$("form").attr({
			action:"/gallery/delete",
			method:"post"
		});
		$("form").submit();
	}
	
}




</script>
</head>
<body>

<h3>Contact Form</h3>

<div class="container">
  <form>
  	<input type="hidden" name="gallery_id" value="<%=gallery.getGallery_id()%>">
  	<input type="hidden" name="filename" value="<%=gallery.getFilename()%>">
    <input type="text" id="title" name="title" placeholder="Your name.." value="<%=gallery.getTitle()%>">
    <input type="text" id="writer" name="writer" placeholder="Your last name.."   value="<%=gallery.getWriter()%>">
    <textarea id="content" name="content" placeholder="Write something.." style="height:200px">
    	<%=gallery.getContent() %>
    </textarea>
    <input type="button" value="수정하기">
    <input type="button" value="목록보기" onClick="location.href='/board/list.do'">
    <input type="button" value="삭제하기">
    
    			<div class="reply-box">
			<input type="text" name="msg" placeholder="댓글 입력" style="width: 75%">
			<input type="text" name="author" placeholder="작성자 입력" style="width: 15%">
			<button type="button" onClick="registComment()">댓글 등록</button>
			</div>
    
    		
  </form>
</div>

</body>
</html>