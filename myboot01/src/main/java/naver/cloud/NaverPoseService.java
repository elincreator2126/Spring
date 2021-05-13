package naver.cloud;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Service;

@Service
public class NaverPoseService implements NaverService {
//서비스를 컨트롤러가 호출 
	
	@Override
	public String test(String image) {
		//apiExamFace 메인 소스 가져오기 
				StringBuffer response = new StringBuffer();
		        StringBuffer reqStr = new StringBuffer();
		        String clientId = "36anypopa6";//애플리케이션 클라이언트 아이디값";
		        String clientSecret = "aZi8kPvvuIZY0Cg7x1TPhr8jYaCK5eEKLuqoLcRA";//애플리케이션 클라이언트 시크릿값";
				/*
				 * String clientId = "vgahoraqwl";//애플리케이션 클라이언트 아이디값"; String clientSecret =
				 * "lfOaoMj49s5i52qn9eayzImMPSj4YNWtcT6Ssdzz";//애플리케이션 클라이언트 시크릿값";
				 */
		        try { //<form methodpost multitype="multipart/form-data <input typefile name="image"
		        	
		            String paramName = "image"; // 파라미터명은 image로 지정 (얼굴 인식 url 요구 파라미터 이름)
		            String imgFile = "C:/kdigital/NaverClova/images/"+image; // 이부분
		            File uploadFile = new File(imgFile);
		            String apiURL = "https://naveropenapi.apigw.ntruss.com/vision-pose/v1/estimate"; // 사람 인식
		            
		            URL url = new URL(apiURL);
		            HttpURLConnection con = (HttpURLConnection)url.openConnection();
		            con.setUseCaches(false);
		            con.setDoOutput(true);
		            con.setDoInput(true); //서버로 전송해야할 데이터가 있다. 
		            // multipart request
		            String boundary = "---" + System.currentTimeMillis() + "---";
		            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
		            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
		            OutputStream outputStream = con.getOutputStream();
		            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
		            String LINE_FEED = "\r\n";
		            // file 추가
		            String fileName = uploadFile.getName();
		            System.out.println("'" + fileName + "'");
		            writer.append("--" + boundary).append(LINE_FEED);
		            writer.append("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
		            writer.append("Content-Type: "  + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
		            writer.append(LINE_FEED);
		            writer.flush();
		       
		            FileInputStream inputStream = new FileInputStream(uploadFile);
		            byte[] buffer = new byte[4096];
		            int bytesRead = -1;
		            while ((bytesRead = inputStream.read(buffer)) != -1) {
		                outputStream.write(buffer, 0, bytesRead);
		            }
		            outputStream.flush();
		            inputStream.close();
		            writer.append(LINE_FEED).flush();
		            writer.append("--" + boundary + "--").append(LINE_FEED);
		            writer.close();
		            //=====================================요청 보내기=================================
		            
		            //응답 받기 
		            BufferedReader br = null;
		            int responseCode = con.getResponseCode();
		            if(responseCode==200) { // 정상 호출
		                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		            } else {  // 오류 발생 400, 500
		            	System.out.println(con.getResponseMessage());
		                System.out.println("error!!!!!!! responseCode= " + responseCode);
		                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		            }
		            String inputLine;
		            //지역변수 블럭 하나가 변수의 사용범위 => response는 if 블럭 안에서만 사용 가능 
		            if(br != null) {
		                //StringBuffer response = new StringBuffer();
		                while ((inputLine = br.readLine()) != null) {
		                    response.append(inputLine);
		                }
		                br.close();
		                System.out.println(response.toString());
		            } else {
		                System.out.println("error !!!");
		            }
		        } catch (Exception e) {
		            System.out.println(e);
		            return e.toString();//클라이언트 파일...오류메시지
		        }
		        return response.toString(); // 서버로부터의 결과(오류,정상결과)
		    }
	}
	

