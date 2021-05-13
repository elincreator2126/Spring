<%@page import="java.math.BigDecimal"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%=request.getAttribute("poseResult") %>
<%
String image = request.getParameter("image"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="jquery-3.2.1.min.js"></script>
<script>
var bodynames = ['코' ,
	'목' ,
	'오른쪽 어깨' ,
	'오른쪽 팔굼치' ,
	'오른쪽 손목' ,
	'왼쪽 어깨' ,
	'왼쪽 팔굼치' ,
	'왼쪽 손목' ,
	'오른쪽 엉덩이' ,
	'오른쪽 무릎' ,
	'오른쪽 발목' ,
	'왼쪽 엉덩이' ,
	'왼쪽 무릎' ,
	'왼쪽 발목' ,
	'오른쪽 눈' ,
	'왼쪽 눈' ,
	'오른쪽 귀' ,
	'왼쪽 귀'];
window.onload = function(){
	var resultdiv = document.getElementById("result");
	var samplecanvas = document.getElementById("samplecanvas");
	var samplecontext = samplecanvas.getContext("2d");
	samplecontext.fillStyle = "red";
	samplecontext.lineWidth = 5;
	samplecontext.strokeStyle = "green";
	samplecontext.font = "10px batang" ;
	
	
	var image = new Image();
	image.src = "/faceimages/<%=image%>";
	image.onload = function(){
		samplecontext.drawImage(image, 0, 0, image.width, image.height);
		

		<%
		/* //자바 String을 json 변환 파싱 */
		//네이버 포즈 인식 결과 
		String poseResult = (String)request.getAttribute("poseResult"); //네이버 얼굴인식 결과 json 

		%>
		var json = JSON.parse('<%=poseResult%>');
		for(var i in json.predictions){
			for(var ii in json.predictions[i]){
				var body = json.predictions[i][ii]; //한 사람에 대한 정보 (한 사람, 18개 정보(객체) "0":{score= , x= , y= }.. "17":{})
				resultdiv.innerHTML += ii + "신체부위: x=" + body.x + "  y=" + body.y + "<br>";
				samplecontext.fillRect(body.x * image.width , body.y * image.height, 5, 5);
				
				//신체부위이름 출력 
				//samplecontext.fillText
				//(bodynames[ii], body.x * image.width+5, body.y * image.height+5)
				
				//1. 왼쪽 오른쪽 손목만 이름 출력 
				//같은 표현 if(bodynames[ii] == ("오른쪽 손목")) //js 는 == 로, java는 equals로
				if(bodynames[ii].indexOf("오른쪽 손목") >= 0 
				|| 	bodynames[ii].indexOf("왼쪽 손목") >= 0  ) {
					samplecontext.fillText(bodynames[ii], body.x * image.width+5, body.y * image.height+5);
				};
				if(bodynames[ii].indexOf("오른쪽 손목") >= 0 ) {
					var rightx = body.x + image.width;
					var righty = body.y + image.height;
				};
				if(bodynames[ii].indexOf("왼쪽 손목") >= 0 ) {
					var leftx = body.x + image.width;
					var lefty = body.y + image.height;
				};
				
				//2. 왼쪽 오른쪽 손목 선 연결 
				samplecontext.beginPath();
				samplecontext.moveTo(leftx,lefty); //시작점 
				samplecontext.lineTo(rightx,righty); //종료점까지 선 그려라 
				samplecontext.closePath();
				samplecontext.stroke(); //plt.show()
				
				}//inner for 
			
			
			}//outer for 
			
	}//image onload end 
}//window onload end 
</script> 
</head>

<body>
<h1> 얼굴 인식 서비스</h1>
<div id="result"> </div>
<h1> 이미지 전체 캔버스 </h1>
<canvas id="samplecanvas" width=900 height=900 style="border : solid 2px pink"></canvas>

</body>
</html>