<%@page import="java.math.BigDecimal"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
window.onload = function(){
	var samplecanvas = document.getElementById("samplecanvas");
	var samplecontext = samplecanvas.getContext("2d");
	var image = new Image();
	image.src = "/faceimages/<%=image%>";
	image.onload = function(){
		samplecontext.drawImage(image, 0, 0, image.width, image.height);
		
//jsp 코드
		<%
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
			//out.println
			//("얼굴 x좌표 =" + x + "y좌표=" + y + "가로크기=" + width + "세로크기=" + height + "<br>");
		%>

		//자바스크립트 코드
			var x = <%=x %>;
			var y = <%=y %>;
			var width = <%=width %>;
			var height = <%=height %>;

		/* 1. id="samplecanvas"에 image 변수 저장된 파일 그리기 
		2. 1번 이미지에서 얼굴 x, y, width, height 값 가져오기 
		3. 1번 이미지에서 2번 영역 얼굴만 복사하여 id="targetcanvas"로 붙여넣기 
		*/

			var faceimage = samplecontext.getImageData(x, y, width, height);
			//색상 반전 - rgb 모드 - 이미지 구성하는 기본 단위 점 - pixel - 1개 (4개 구성성분 - rgbi) - 0~ 255 숫자로 구성 
			//ff000 000ff / ff00ff -> 00ff00
			var data = faceimage.data; //이미지 구성하는 기본단위 절 *4  
			var numpixels = data.length; //
			for (var i = 0; i < numpixels; i=i+4){
				data[i] = 255 - data[i];//r
				data[i+1] = 255 - data[i+1]; //g
				data[i+2] = 255 - data[i+2]; //g
				
 			}
			//색상 반전 종료 
			
			samplecontext.putImageData(faceimage, x, y);
			
			var targetcanvas = document.getElementById("targetcanvas");
			var targetcontext = targetcanvas.getContext("2d");
			targetcontext.putImageData(faceimage, 50, 50);
			

		<% 
		}

		%>
		
	}
}
</script>
</head>
<body>
<h1> 얼굴 인식 서비스</h1>
<h1> 이미지 전체 캔버스 </h1>
<canvas id="samplecanvas" width=400 height=400 style="border : solid 2px pink"></canvas>
<h1> 얼굴만 캔버스 </h1>
<canvas id="targetcanvas" width=400 height=400 style="border : solid 2px pink"></canvas>

</body>
</html>