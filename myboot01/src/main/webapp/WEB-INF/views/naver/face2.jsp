<%@page import="java.math.BigDecimal"%>
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
<h1> 얼굴 인식 서비스</h1>

<%
String image = request.getParameter("image");
/* //자바 String을 json 변환 파싱 */
String faceResult2 = (String)request.getAttribute("faceResult2"); //네이버 얼굴인식 결과 json 
//out.println(faceResult2); 

JSONObject obj = new JSONObject(faceResult2);
JSONArray faces = (JSONArray)obj.get("faces");

for (Object one : faces){
	
	JSONObject roi = (JSONObject) ((JSONObject)one).get("roi");
	int x = (int)roi.get("x");
	int y = (int)roi.get("y");
	int width = (int)roi.get("width");
	int height = (int)roi.get("height");
	out.println
	("얼굴 x좌표 =" + x + "y좌표=" + y + "가로크기=" + width + "세로크기=" + height + "<br>");
}

//추가
//표정 성별 나이 
for(int i=0; i< faces.length(); i++){
	JSONObject face = (JSONObject)faces.get(i);
	JSONObject gender = (JSONObject)face.get("gender");
	String gendervalue = (String )gender.get("value");
	JSONObject age = (JSONObject)face.get("age");
	String agevalue = (String)age.get("value");
	JSONObject emotion = (JSONObject)face.get("emotion");
	String emotionvalue = (String)emotion.get("value");
	out.println
	("표정 =" + emotionvalue +  "성별=" + gendervalue + "나이=" + agevalue + "<br>");

}


%>
 <img src="/faceimages/<%=image %>">

</body>
</html>