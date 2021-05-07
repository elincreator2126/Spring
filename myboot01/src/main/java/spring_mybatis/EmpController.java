package spring_mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmpController {
	
	@Autowired
	EmpService service; 
	
	
//	//employees 테이블 전체 조회 
	@RequestMapping("/emplist")
	public ModelAndView getEmpList() { //page 전달값이 보여줄 페이지, 한 페이지 당 출력개수 10개. 입사일이 빠른 사원부터 출력
		//mybatis SqlSession --EmpDAO  -- EmpService들 하위
		List<EmpVO> list = service.getAllEmp();
		ModelAndView mv = new ModelAndView();
		mv.addObject("emplist", list); 
		//request.setAttribute("boardlist", list) ${boardlist} -- jsp상에서
		mv.setViewName("/mybatis/emplist"); //WEB-INF/views/mybatis/emplist.jsp
		return mv; 
	}

	//employees 테이블
	//page 전달값이 보여줄 페이지, 한 페이지 당 출력개수 10개. 입사일이 빠른 사원부터 출력
	@RequestMapping("/emplistpage")
	public ModelAndView getEmpList(int page) { //3
		//mybatis SqlSession --EmpDAO  -- EmpService들 하위
		int rownum [] = new int [2];
		rownum[0] = (page - 1)*10 +1; //21
		rownum[1] = (page - 1)*10; //30 
		List<EmpVO> list = service.getPagingEmp(rownum);
		ModelAndView mv = new ModelAndView();
		mv.addObject("emplist", list); 
		//request.setAttribute("emplist", list) ${emplist} -- jsp상에서
		mv.setViewName("/mybatis/emplist"); //WEB-INF/views/mybatis/emplist.jsp
		return mv; 
	}
	
	
	
	//클라이언트 입력 id 파라미터 = 200
	// /empdetail url 
	//employees 테이블 -  1개 레코드 조회  
	//model로 생성 
	//view - /mybatis/empdetail.jsp
	//empdetail.jsp 출력 
	@RequestMapping("/empdetail")
	public ModelAndView getOneEmp(int id) {
		EmpVO vo = service.getOneEmp(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("detail", vo); 
		mv.setViewName("/mybatis/empdetail");
		return mv;

		
	}
	
	//추가물 출력  / 사원정보 입력 
	
	@RequestMapping(value="/empadd", method=RequestMethod.GET)
	public String addEmp() {
		return "/mybatis/addform";
	}
	
	/*
	 * <form action="/empadd", method=post>
사번 <input type=text name="employee_id"> <br>
이름 <input type=text name="first_name"> <br>
성 <input type=text name="last_name"> <br>
이메일 <input type=text name="email"> <br>
직종 <input type=text name="job_id"> <br>
<input type=submit value="사원등록">
</form>
	 * */
	
	@RequestMapping(value="/empadd", method=RequestMethod.POST)
	public String addEmp2(EmpVO vo) {
		//System.out.println(vo.toString()); //null, 0 
		service.registerEmp(vo);
		return "redirect:/emplist"; //==> /emplist 매핑 메소드 호출 
	}
	
	//수정폼 
	@RequestMapping(value="/empmodify", method=RequestMethod.GET)
	public ModelAndView modifyEmp (int id) {
	ModelAndView mv= new ModelAndView();
	EmpVO vo = service.getOneEmp(id);
	mv.addObject("emp", vo);
	mv.setViewName("/mybatis/modifyform");
	return mv; 
	}
	
	//수정처리 
	
	//삭제 
	@RequestMapping(value="/empdelete", method=RequestMethod.GET)
	public String deleteEmp (int id) {
	//service.xxxxx();
	return "redirect:/emplist";
	}
	
	
}
