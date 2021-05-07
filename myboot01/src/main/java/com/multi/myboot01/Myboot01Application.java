package com.multi.myboot01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


import naver.cloud.NaverController;
import spring_mybatis.EmpController;
import spring_mybatis.EmpDAO;
//부트 시작 클래스, @, 패키지명 추가  
@SpringBootApplication
@ComponentScan //(basePackageClasses = Myboot01Application.class)
@ComponentScan(basePackageClasses = EmpController.class)
@ComponentScan(basePackageClasses = NaverController.class)
@MapperScan(basePackageClasses = EmpDAO.class)
public class Myboot01Application {
	//톰캣 내장 서버 자동 실행 

	public static void main(String[] args) {
		SpringApplication.run(Myboot01Application.class, args);
	}

}
