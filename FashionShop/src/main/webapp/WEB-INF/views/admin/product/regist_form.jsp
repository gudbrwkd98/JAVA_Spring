<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../inc/header.jsp" %>
<script>
$(function(){ 
	CKEDITOR.replace( 'detail' );
	
	$($("input[type='button']")[0]).click(function(){
		regist();
	});
});

//글등록 요청
function regist(){
	$("form").attr({
		action:"/gallery/regist", //서블릿에게 요청
		method:"post",

	});
	$("form").submit();
	
}

</script>
</head>
<body>
	<%@ include file="../inc/main_navi.jsp" %>
<h3>Contact Form</h3>

<div class="container">
  <form enctype="multipart/form-data">
  	<select  id="title" name="title">
  		<option>상위 카테고리 선택</option>
  	</select>
  	
  	<select  id="title" name="title">
  		<option>하위 카테고리 선택</option>
  	</select>
  	
    <input type="text" id="title" name="title" placeholder="상품명">
    <input type="text" id="writer" name="writer" placeholder="가격">
    <input type="text" id="writer" name="writer" placeholder="브랜드">
    	<!-- 파일 최대 4개까지 지원 -->
	<p>대표이미지 : <input type="file" name="photo"><p>

	<p>추가이미지 : <input type="file" name="photo"><p>
	<p>추가이미지 : <input type="file" name="photo"><p>
	<p>추가이미지 : <input type="file" name="photo"><p>
	<p>추가이미지 : <input type="file" name="photo"><p>

	<!-- 지원 사이즈 선택 -->
	<p>
		XS<input type="checkbox">
		S<input type="checkbox">
		M<input type="checkbox">
		L<input type="checkbox">
		XL<input type="checkbox">
		XXL<input type="checkbox">
	</p>
	
	<p>
		컬러피커를 훔쳐올 예정
	</p>
    
    <textarea id="detail" name="detail" placeholder="상세정보" style="height:200px"></textarea>

	
    <input type="button" value="글등록">
    <input type="button" value="목록보기" onClick="location.href='/board/list'">
    
  </form>
</div>

</body>
</html>