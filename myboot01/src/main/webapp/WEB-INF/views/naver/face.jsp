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
<h1> 닮은 연예인 찾아주기</h1>
<%-- ${faceResult } --%>

<%-- <% 

String faceResult = (String)request.getAttribute("faceResult");
out.println(faceResult);
//faces 다음 celebrity 다음 value 다음 값 --> 닮은 연예인 이름 
//다음 confidence 다음 값 --> 확률 


<%-- %>
<%="닮은 연예인이름=" + xxxx %><br>
<%="확률="+xxxx %><br> --%>

<%-- 
String faceResult = (String)request.getAttribute("faceResult");
out.println(faceResult + "<br>");
String faceInfo[] = faceResult.split("\"faces\":");
out.println(faceInfo[1] + "<br>");
String celeInfo[] = faceInfo[1].split("\"celebrity\":");
out.println(celeInfo[celeInfo.length - 1] + "<br>");
String one = celeInfo[celeInfo.length - 1] ; 
int valueIndex = one.indexOf("\"value\":");
int valuelength = "\"value\":".length();
int configIndex = one.indexOf("\"confidence\":");
int configlength = "\"confidence\":".length();

out.println(celeInfo[celeInfo.length - 1].substring(valueIndex + valuelength, configIndex-1 )+"<br>");
out.println(celeInfo[celeInfo.length - 1].substring(configIndex + configlength, configIndex + configlength+8)+"<br>");

--%> 


<%-- <%

String faceResult = (String)request.getAttribute("faceResult");
out.println(faceResult+"<br>");
String faceInfo[] = faceResult.split("\"faces\":");
out.println(faceInfo[1] +"<br>");
String celeInfo[] = faceInfo[1].split("\"celebrity\":");
out.println(celeInfo[celeInfo.length - 1] + "<br>") ;
String one = celeInfo[celeInfo.length - 1] ;
int valueIndex = one.indexOf("\"value\":");
int valueLength = "\"value\":".length();
int confiIndex =  one.indexOf("\"confidence\":");
int confiLength = "\"confidence\":".length();

out.println(celeInfo[celeInfo.length - 1].substring(valueIndex + valueLength,  confiIndex-1) + "<br>");
out.println(celeInfo[celeInfo.length - 1].substring(confiIndex + confiLength,  confiIndex + confiLength + 8) + "<br>");
%> --%>
<!-- 
파이선 딕셔너리 
자바 java.utill.HashMap
json => {"변수명":{"a":"문자값", "b":1213}, "변수명2":100, "변수명3":[{"a":"문자값", "b":1213},{"a":"문자값", "b":1213}] }
{"info": {......}, "faces":[ celebrity:{"value":"", confidence : 0.123}, , , ]}
 자동으로 parsing 해주는 라이브러리 설치해서 사용
 facecount에 상관없이 independent한 것으로 쓰기 위해 
 -->

<%
/* //자바 String을 json 변환  */
String faceResult = (String)request.getAttribute("faceResult");
JSONObject obj = new JSONObject(faceResult);
Object imsi = obj.get("faces"); //faces는 배열 형태
JSONArray faces = (JSONArray)imsi;
boolean find = false; //

for (Object cele : faces){
	out.println((JSONObject)cele + "<br>");
	JSONObject celebrity = (JSONObject) ((JSONObject)cele).get("celebrity");
	find = true; //한 번이라도 반복문일 돌아서 하나라도 닮은 연예인을 찾았다. 
/* 	//String value = (String) celebrity.get("value");
	//String confidence = (String)celebrity.get("confidence");
	//double confidence2 = (double)((JSONObject)cele).get("confidence"); */
	BigDecimal confidence = (BigDecimal) celebrity.get("confidence");
/* 	//confidence.메소드*100 */
	out.println("닮은 연예인이름 =" + celebrity.get("value") +", 닮은 확률=" 
	+ Math.round(confidence.doubleValue()*100) + "%<br>");
}

/* JSONObject cele = (JSONObject)faces[4].get("celebrity");
Object imsi2 = obj.get("info"); //info는 문자열
Object info = (JSONObject)imsi; */
String image = request.getParameter("image");
if( find == false){
	out.println("닮은 연예인을 찾을 수가 없습니다.<br>");
}
%>

<!-- 닮은 연예인이 없으면 닮은 연예인을 찾을수가 없습니다. 추가 
분석 이미지 파일 1개 출력  -->

<%-- <img src="/faceimages/<%=image %>"> --%> 

</body>
</html>