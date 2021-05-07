package naver.cloud;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NaverController {
	//service 실행 요청 (service가 해달라고 하고 결과만 받아온다)
	@Autowired
	NaverFaceService service; //서비스 객체 만들어진 것 자동으로 가져와라
	
	@RequestMapping("/imagetest")
	public String imagetest() {

		return "/naver/imagetest";
		
	}
	
	//언제 실행해야할 지 정해 
	@RequestMapping("/faceinput")
	public ModelAndView faceinput() {
		//face메소드 호출 => NaverFaceService에게 실행 요청 
		//images폴더를 file 객체 생성 
		//file 객체 list 메소드 이용하면 파일이름만 배열 리턴 
		//리턴받은 배열을 모델 생성 - 모델명 filelist
		
		File f = new File("C:\\kdigital\\NaverClova\\images");
		String[] filelist = f.list();
		// *.jfif jpg png gif
		ModelAndView mv = new ModelAndView();
		mv.addObject("filelist", filelist);
		mv.setViewName("/naver/faceinput");
		//faceinput.jsp 구현 
		//filelist 모델값을 <a href="/face 호출하면서 파일이름 전달" > 파일이름 </a>
		
		return mv; 
	}
	
	
	@RequestMapping(value="/face", method=RequestMethod.GET)
	public ModelAndView face(String image) { //전달받는 face메소드가 매개변수가 선언되어 있어야 함. -> test로 전달 
		//face메소드 호출 => NaverFaceService에게 실행 요청 
		String result = service.test(image);//아니면 콘솔 보실래요?
		ModelAndView mv = new ModelAndView();
		mv.addObject("faceResult", result);
		mv.setViewName("/naver/face"); //이 부분이 안넘어가지는데...
		return mv; 
	}
	
}
