package spring_mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("dao")
public interface EmpDAO {

	public EmpVO getOneEmp(int employee_id) ; //<select id="getOneEmp"

	public List<EmpVO> getAllEmp() ; //select id="getAllEmp"

	public List<EmpVO> getPagingEmp(int page[]) ; 
	
	public EmpVO checkEmp(EmpVO vo); // 사번, 이메일 중복 검사 - 결과 존재하면 사번이나 이메일 등록 불가 
	
	public String checkJob(EmpVO vo); //job_id - jobs테이블 조회 존재 여부
	
	public void insertEmp(EmpVO vo);  
	//
//	public void insertEmp(EmpVO vo) {
//		 session.insert("kdigital.manyEmp", vo);
//	}
//		
//	public void updateEmp(EmpVO vo) {
//		session.update("kdigital.manyEmp", vo);
//	}
//	
//	public void deleteEmp(int employee_id) {
//		session.delete("kdigital.deleteEmp", employee_id);
//	}
//	
//	public List<EmpVO> getPageEmp(int[] nums) {
//		return session.selectList("kdigital.pageEmp", nums);
//	}
//	public void insertEmp2(EmpVO vo) {
//		 session.insert("kdigital.manyEmp", vo);
//	}
//	
//	public List<EmpVO> getEmpDept (List<Integer> deptList) {
//		return session.selectList("kdigital.selectwithList", deptList); 
//	}
//	
//	public void updateEmpMap(Map<String, String> map) {
//		session.update("kdigital.updatewithmap", map); 
//	}
//	
	
	
	
}