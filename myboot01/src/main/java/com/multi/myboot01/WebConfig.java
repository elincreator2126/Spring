package com.multi.myboot01;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 현재 클래스 설정 모든 결과 xml 파일 <bean 
//@ComponentScan - < context;component-scan - 대신) 
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")//url 설정 
                .addResourceLocations("file:///c:/upload/");//실제 경로
        registry.addResourceHandler("/faceimages/**") //외부의 경로를 내부에서 사용가능하도록 url가상으로 매핑해준 것 
        		.addResourceLocations("file:///C:/kdigital/NaverClova/images/");
        //보여야 할 데이터가 외부환경에 있을 때, 외부 디렉토리 file:/// 로 시작 
    }
}


/* http://127.0.0.1:9090/upload/a.png - c:/upload/a.png */