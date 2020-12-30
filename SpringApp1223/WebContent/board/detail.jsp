<%@page import="com.model2.domain.Board"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	Board board = (Board)request.getAttribute("board"); 
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
		action:"/board/edit", //서블릿에게 요청
		method:"post"
	});
	$("form").submit();
	
}

function del(){
	if(confirm("삭제하시겠어요?")){
		$("form").attr({
			action:"/board/delete",
			method:"post"
		});
		$("form").submit();
	}
	
}


//댓글 목록 가져오기 
function getCommentList(){
	$.ajax({
		url:"/comment/list.do",
		type:"get",
		data:{
			board_id : <%=board.getBoard_id()%>
		},
		success:function(result){
			//var Jsonobj = JSON.parse(result);
			//alert(Jsonobj['list'][0].msg);
			//result 에는 서버에서 전송한 json 배열이 들어있다..이 배열을이용하여 아래의 컨텐츠를
			//완성하기!! 4시 10분까지...
			$("#list-area").html(""); //초기화 시킨 후
			
			var tag="<div class=\"reply-list\">";
			
			for(var i =0 ; i<result['list'].length;i++){
			
			tag+="<p style=\"width:75%\">"+result['list'][i].msg+"</p>"; 
			tag+="<p style=\"width:15%\">"+result['list'][i].author+"</p>";
			tag+="<p style=\"width:10%\">"+result['list'][i].cdate+"</p>"; 
			}
			
			tag+="</div>";
			
			$("#list-area").html(tag);  //innerHTML과 동일
		}
		
	})
}


//현재 페이지가 새로고침 되지않게 비동기 방식으로 등록 요청
//순수 js의 ajax 를 사용하면 처리가 복잡하므로 jquery ajax 로 처리해보자 
function registComment(){
	$.ajax({
		url:"/comment/regist.do",
		type:"post",
		data:{
			board_id:<%=board.getBoard_id()%>,
			msg:$("input[name='msg']").val(),
			author:$("input[name='author']").val()
		},
		//피드백은 success 로 받는다 즉 서버에서 에러없이 데이터가 전송되면
		//success 우측에 명시된 익명 함수가  동작하게된다 
		success:function(result){
			if(result==1){
				getCommentList();
			}else{
				alert("등록실패");
			}

		}
	});
}


</script>
</head>
<body>

<h3>Contact Form</h3>

<div class="container">
  <form>
  			<input type="text" name="board_id" value="<%=board.getBoard_id()%>" hidden>
    <input type="text" id="title" name="title" placeholder="Your name.." value="<%=board.getTitle()%>">
    <input type="text" id="writer" name="writer" placeholder="Your last name.."   value="<%=board.getWriter()%>">
    <textarea id="content" name="content" placeholder="Write something.." style="height:200px">
    	<%=board.getContent() %>
    </textarea>

    <input type="button" value="수정하기">
    <input type="button" value="목록보기" onClick="location.href='/board/list.do'">
    <input type="button" value="삭제하기">
    
    			<div class="reply-box">
			<input type="text" name="msg" placeholder="댓글 입력" style="width: 75%">
			<input type="text" name="author" placeholder="작성자 입력" style="width: 15%">
			<button type="button" onClick="registComment()">댓글 등록</button>
			</div>
    
    		<div id="list-area"></div>
  </form>
</div>

</body>
</html>