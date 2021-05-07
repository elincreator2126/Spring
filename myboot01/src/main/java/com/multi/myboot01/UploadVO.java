package com.multi.myboot01;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class UploadVO {
		String name; 
		String description;
		//*.jar - pom.xml (maven 틀 (자동 라이브러리 다운로드) - 스프링 프로젝트 내장)
		MultipartFile file1;
		MultipartFile file2;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public MultipartFile getFile1() {
			return file1;
		}
		public void setFile1(MultipartFile file1) {
			this.file1 = file1;
		}
		public MultipartFile getFile2() {
			return file2;
		}
		public void setFile2(MultipartFile file2) {
			this.file2 = file2;
		}
		
		
		
		
		
}