package com.multi.myboot01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadController {
   @RequestMapping("/filedownload")
   public ModelAndView downloadform() {
      //c:upload 폴더 저장 파일 리스트 모델로 생성 뷰로 전달
      ModelAndView mv = new ModelAndView();
      File path = new File("c:/upload");
      String[] filelist = path.list(); //파일명 추출
      mv.addObject("details", filelist);
      mv.setViewName("upload/downloadform");
      
      return mv;
   }
   
   @RequestMapping("/downloadresult")
   public void downloadresult(String file, HttpServletResponse response) throws IOException { //다운로드 파일명
      //file 다운로드
      System.out.println("파일명: "+file);
      File f = new File("c:/upload", file); // c:/upload/파일명
      //파일 다운로드에 필요한 3가지
      response.setContentType("application/download");
      response.setContentLength((int)f.length()); //파일의 길이
      response.setHeader("Content-Disposition", "attachment;filename=\""+file+"\"");
      
      //파일명에 해당하는 파일을 읽어서 클라이언트에서 출력
      OutputStream out = response.getOutputStream(); //클라이언트에게 출력
      FileInputStream fin = new FileInputStream(f);
      FileCopyUtils.copy(fin, out);
      fin.close();
      out.close();
   }
}