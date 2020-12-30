
<%@page import="com.study.springfinal.domain.Gallery"%>
<%@page import="com.study.springfinal.common.Pager"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%  
	List list = (List)request.getAttribute("galleryList");
	Pager pager = new Pager();
	pager.init(request, list);
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
</style>
</head>
<body>

	<table>
		<tr>
			<th>No</th>
			<th>이미지</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
	<%int num = pager.getNum();
		int curPos = pager.getCurPos();
	%>
		<%for(int i = 0 ; i <pager.getPageSize(); i++) {
		if(num<1)break;
		Gallery gallery = (Gallery)list.get(curPos++);

	%>
		<tr>
			<td><%=num-- %></td>
			<td><img alt="" src="/data/<%=gallery.getFilename()%>" width="100px" height="100px"></td>
			<td><a href="/gallery/detail?gallery_id=<%=(gallery.getGallery_id())%>"><%=gallery.getTitle() %></a>[<%=gallery.getHit() %>]</td>
			<td><%=gallery.getWriter() %></td>
			<td><%=gallery.getRegdate().toString().substring(10) %></td>
			<td><%=gallery.getHit() %></td>
		</tr>
	<%} %>

		<tr>
		<td colspan="5" style="text-align: center">
		<%if(pager.getFirstPage()-1 != 0){ %>
		<a href="/view/board/list.jsp?currentPage=<%=pager.getFirstPage()-1%>">&#9664;</a>
		<%}else{ %>
		<a href="javascript:alert('처음 페이지입니다')">&#9664;</a>
		<%} %>
		<%for(int i = pager.getFirstPage(); i<=pager.getLastPage(); i++){ %>
			<%if(pager.getTotalPage()<i)break; %>
			<a href="/view/board/list.jsp?currentPage=<%=i%>" <%if(pager.getCurrentPage()==i){%> class="pageNum" <% } %> >[<%=i %>]</a>
		<%} %>
		<%if((pager.getLastPage()+1)<pager.getTotalPage()){ %>
		<a href="/view/board/list.jsp?currentPage=<%=pager.getLastPage()+1%>">&#9654;</a>
		<%}else{ %>
		<a href="javascript:alert('마지막 페이지입니다')">&#9654;</a>
		<%} %>
		</td>
		</tr>
		<tr>
			<td colspan="5">
				<button onClick="location.href='/gallery/regist_form.jsp'"> 글등록</button>
			</td>
		</tr>
	</table>

</body>
</html>