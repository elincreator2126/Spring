package spring_mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("service")
public class EmpServiceImpl implements EmpService {
	@Autowired
	EmpDAO dao; 
	
	@Override
	public EmpVO getOneEmp(int employee_id) {
		return dao.getOneEmp(employee_id);
	}

//	@Override
	public List<EmpVO> getAllEmp() {
//		//dao 다른 메소드 호출 실행 가능
//		//service는 sql이 아니라 기능 단위 
		return dao.getAllEmp();
		
	}
	
	public List<EmpVO> getPagingEmp(int page[]) {
		return dao.getPagingEmp(page);
	}

	@Override
	public void registerEmp(EmpVO vo) { //사원등록서비스기능 - sql 
		//last_name null 인지 여부 - db sql 실행 후 결과 알 수 
		System.out.println("성=" + vo.getLast_name() );
		if(vo.getLast_name() != null) {
			// 사번, 이메일 중복 검사 - 결과 존재하면 사번이나 이메일 등록 불가 
			EmpVO vo2 = dao.checkEmp(vo); // 사번 이메일 중복 
			String job_id = dao.checkJob(vo);
			System.out.println("vo2=" + vo2 + ", job id=" + job_id);
			if(vo2==null && job_id != null) {
				dao.insertEmp(vo);
			}
			
			
			
			/*
			 * select employee_id, email from employees where employee_id=? or email =?
			 * //job_id - jobs테이블 조회 존재 여부 - 결과 존재하면 직종 등록 가능 select job_id from jobs where
			 * job_id=?
			 */
		}
		
		//job_id - jobs테이블 조회 존재 여부 
		
		//insert
		
	}
	
	
	
//
//	@Override
//	public void insertEmp(EmpVO vo) {
//		dao.insertEmp(vo);
//
//		
//	}
//
//	@Override
//	public void updateEmp(EmpVO vo) {
//		dao.updateEmp(vo);
//	}
//
//	@Override
//	public void deleteEmp(int employee_id) {
//		dao.deleteEmp(employee_id);
//		
//	}
//
//	@Override
//	public List<EmpVO> getPageEmp(int[] nums) {
//		return dao.getPageEmp(nums);
//	}
//
//	@Override
//	public void insertEmp2(EmpVO vo) {
//		dao.insertEmp2(vo);
//		
//	}
//
//	@Override
//	public List<EmpVO> getEmpDept(List<Integer> deptList) {
//		return dao.getEmpDept(deptList);
//	}
//
//	@Override
//	public void updateEmpMap(Map<String, String> map) {
//		dao.updateEmpMap(map);
//		
//	}
	
	
	
	
	


}
