<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="jquery-3.2.1.min.js"></script>
<script>
$(document).ready(function() {
	
});
</script>
</head>
<body>
<h1> 업로드 파일 목록은 다음과 같습니다. </h1>
<% 
String [] details = (String[])request.getAttribute("details");
for(String one : details ) { 
%>

 <a href="/downloadresult?file=<%=one %>" > <%=one %> 파일 다운로드</a> <br>
<% } %>
</body>
</html>