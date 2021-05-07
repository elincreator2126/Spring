package com.multi.myboot01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	@RequestMapping("/")
	@ResponseBody
	public String test() {
		return "hello";
	}
}


/*
 * spring mvc 는 컨텍스트명 필요 
 * 		기본 view jsp 
 * 
 * 
 * 
 * spring boot 는 컨텍스트명 없어도 됨
 *  / => 포트번호까지만 입력됐을 때
 *  	1> src/main/webapp/WEB-INF/view 폴더 생성 
 *  	2> hello.jsp 생성 
 *  	3> application.properties 설정 
 *  	4> pom.xml - jsp 사용 라이브러리 다운로드 실행  
 */