package com.multi.myboot01;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
//servlet-context.xml - <context:component-scan base - packages = "@Controller..클래스의 패키지명 
@Controller
public class UploadController {
	public static String getUuid() { // 동일 이름의 파일도 업로드 가능하도록 랜덤으로 파일명 변경하여 업로드하는 메소드 
			return UUID.randomUUID().toString().replaceAll("-","").substring(0,10); 
			//toString 문자열 리턴, replace "-"를 " "로 교체, substring 시작과 끝 인덱스 사이의 문자열 추출 
		} 

	
	@RequestMapping(value="/fileupload", method = RequestMethod.GET) //조회 
	public String uploadform() {
		System.out.println(1);
		return "/upload/uploadform"; //WEB-INF 의 views의 upload의 uploadform.jsp 
	}
	
	@RequestMapping(value="/fileupload", method=RequestMethod.POST)
	@ResponseBody
	public String uploadresult(@ModelAttribute("vo") UploadVO vo) throws IOException{
		//html form input name="a" vo 동일명 변수 
		//업로드한 파일 객체
		MultipartFile multipartfile1 = vo.getFile1();
		MultipartFile multipartfile2 = vo.getFile2();
		
		System.out.println(multipartfile1.getOriginalFilename());
		
		//업로드한 파일명 추출
		String filename1 = multipartfile1.getOriginalFilename(); //클라이언트 원본 파일명
		String filename2 = multipartfile2.getOriginalFilename();
		
		//서버 저장 경로 설정
		String savePath = "c:/upload";
		
		//서버 저장 파일명 (클라이언트 원본 파일명).확장자
		// 중복파일처리1 : 
	      // api : 랜덤암호화변경이름
	      // a.txt --> 123wsdjhfckdjf.txt
	      String ext1 = filename1.substring(filename1.lastIndexOf("."));
	      String ext2 = filename2.substring(filename2.lastIndexOf("."));   
	      
	      System.out.println(ext1 +":"+ ext2);
		filename1 = getUuid() + "(" + multipartfile1.getOriginalFilename() + ")" + ext1;
		filename2 = getUuid() + "(" + multipartfile2.getOriginalFilename() + ")" + ext2;

		File file1 = new File(savePath + filename1);
		File file2 = new File(savePath + filename2);
		//서버 저장 
		multipartfile1.transferTo(file1);
		multipartfile1.transferTo(file2);
		
		//return "/upload/uploadresult"; //뷰에서 업로드 파일명 출력 
		return  "잘 받았습니다. " ; //ajax 클라이언트("a":"b")
}
}


