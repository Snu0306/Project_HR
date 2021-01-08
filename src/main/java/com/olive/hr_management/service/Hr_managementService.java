/*
	파일명: Hr_managementService.java
    설명: 인사관리 Service 
    작성일: 2021-01-02
    작성자: 백희승
*/
package com.olive.hr_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olive.dto.Class;
import com.olive.dto.Dept;
import com.olive.dto.Emp;
import com.olive.dto.Head;
import com.olive.dto.Position;
import com.olive.hr_management.dao.Hr_managementDao;

import paging.Criteria;

@Service
public class Hr_managementService {

	private SqlSession sqlsession;

	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
		System.out.println(this.sqlsession);
	}

//	public List<Emp> getEmpList() {
//		System.out.println("getEmpList 서비스 진입");
//		List<Emp> result = null;
//		Hr_managementDao hr_managementDao = sqlsession.getMapper(Hr_managementDao.class);
//		try {
//			result = hr_managementDao.getEmpList();
//			System.out.println("getEmpList result : " + result);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("getEmpList error : " + e.getMessage());
//		}
//
//		return result;
//	}

//	public List<Map<String, Object>> getEmpList(Criteria cri) {
//		Hr_managementDao dao = sqlsession.getMapper(Hr_managementDao.class);
//		return dao.getEmpList(cri);
//	}
//
//	public int getListCount() {
//		System.out.println("getListCount 서비스 시작");
//		int result = 0;
//		Hr_managementDao hr_managementDao = sqlsession.getMapper(Hr_managementDao.class);
//		try {
//			result = hr_managementDao.getListCount();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}

//	public List<Emp> getEmpList() {
//		System.out.println("getEmpList 서비스 진입");
//		List<Emp> result = null;
//		Hr_managementDao hr_managementDao = sqlsession.getMapper(Hr_managementDao.class);
//		try {
//			result = hr_managementDao.getEmpList();
//			System.out.println("getEmpList result : " + result);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("getEmpList error : " + e.getMessage());
//		return result;
//	}

	// 인사관리 : 사원 신규 등록
	public void insertNewEmp(Emp emp) {
		Hr_managementDao dao = sqlsession.getMapper(Hr_managementDao.class);
		dao.insertNewEmp(emp);
		System.out.println("서비스단 거침스");
	}

	// 인사관리 : 사원 리스트 출력 테스트
	public List<Emp> selectAllList() {
		List<Emp> list = new ArrayList<Emp>();
		Hr_managementDao dao = sqlsession.getMapper(Hr_managementDao.class);
		list = dao.selectAllList();
		return list;
	}

	// 인사관리 : 사원 신규 등록 시 Ajax 사번 중복 검증용
	public Emp checkEmpNo(String empNo) {
		Hr_managementDao dao = sqlsession.getMapper(Hr_managementDao.class);
		Emp dto = dao.checkEmpNo(empNo);
		return dto;
	}

	// 인사관리 : 사원 신규 등록 시 Ajax 본부 목록 호출
	public List<Head> getHeadQuarters() {
		Hr_managementDao dao = sqlsession.getMapper(Hr_managementDao.class);
		List<Head> HQList = dao.getHeadQuarters();
		return HQList;
	}

	// 인사관리 : 사원 신규 등록 시 Ajax 본부 코드 선택 시 부서 코드 호출
	public List<Dept> getDepartments(String headCode) {
		Hr_managementDao dao = sqlsession.getMapper(Hr_managementDao.class);
		List<Dept> deptList = dao.getDepartments(headCode);
		return deptList;
	}

	// 인사관리 : 사원 신규 등록 시 포지션(임무) 가져오기
	public List<Position> getPositions() {
		Hr_managementDao dao = sqlsession.getMapper(Hr_managementDao.class);
		List<Position> positionList = dao.getPositions();
		return positionList;
	}

	// 인사관리 : 사원 신규 등록 시 클래스(직급) 가져오기
	public List<Class> getClasses() {
		Hr_managementDao dao = sqlsession.getMapper(Hr_managementDao.class);
		List<Class> classList = dao.getClasses();
		return classList;
	}

}
