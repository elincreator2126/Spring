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
<% 
String[] filelist = (String[])request.getAttribute("filelist");
for(String file:filelist){ //*.mp3 *.txt 제외
	//if(file.indexOf("mp3")>=0) //amp3b.gif -->NO
	String file_split [] = file.split("[.]"); //문자열 패턴 (자바 - a.b)
	String ext = file_split[file_split.length-1];
	if(!(ext.equals("mp3") || ext.equals("txt")) ){
%>
	<h1>${fileInput}</h1>
	<a href="/pose?image=<%=file %>"> <%=file %></a> 
	<img src="/faceimages/<%=file %>" width=300 height=300><br> 
	<!-- WebConfig.java 에 url설정 및 실제 경로 작성하기 (외부에있는 그림들 보여줄 수 있도록 -->
<%
	}
}
%>

</body>
</html>