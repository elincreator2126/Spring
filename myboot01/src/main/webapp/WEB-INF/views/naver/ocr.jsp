<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
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
<script> 
window.onload = function() {
	var div = document.getElementById("ocrResult");
<%
String image = request.getParameter("image");
String ocrResult = (String)request.getAttribute("ocrResult"); 
JSONObject obj = new JSONObject (ocrResult);
JSONArray images = (JSONArray)obj.get("images");
JSONObject oneimage = (JSONObject)images.get(0);
JSONArray fields = (JSONArray) oneimage.get("fields");

for (int i = 0; i < fields.length(); i++){
	JSONObject onefield = (JSONObject)fields.get(i);
	String inferText = (String)onefield.get("inferText");
%>
	div.innerHTML += "<%=inferText %>"";
<%
}
%>	
}
</script>

<img src="/faceimages/<%=image %>"><br>
<div id="ocrResult" > </div>

</body>
</html>