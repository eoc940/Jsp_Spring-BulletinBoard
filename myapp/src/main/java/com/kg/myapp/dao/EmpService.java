package com.kg.myapp.dao;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kg.myapp.vo.EmpVO;


@Service
public class EmpService implements IEmpService {
	
	@Autowired
	EmpRepository empRepository;
	

	@Override
	public int getEmpCount() {
		// TODO Auto-generated method stub
		return empRepository.getEmpCount();
	}

	@Override
	public int getEmpCount(int deptId) {
		// TODO Auto-generated method stub
		return empRepository.getEmpCount(deptId);
	}
	
	@Override
	public int getManagerCount(int empId) {
		// TODO Auto-generated method stub
		return empRepository.getManagerCount(empId);
	}

	@Override
	public int getDepartmentCount(int empId) {
		// TODO Auto-generated method stub
		return empRepository.getDepartmentCount(empId);
	}


	@Override
	public List<EmpVO> getEmpList() {
		// TODO Auto-generated method stub
		return empRepository.getEmpList();
	}
	
	@Override
	public List<EmpVO> getEmpList(int deptId) {
		// TODO Auto-generated method stub
		return empRepository.getEmpList(deptId);
	}

	@Override
	public List<EmpVO> getEmpList(String name) {
		// TODO Auto-generated method stub
		return empRepository.getEmpList(name);
	}

	@Override
	public List<EmpVO> getEmpList(int minPay, int maxPay) {
		// TODO Auto-generated method stub
		return empRepository.getEmpList(minPay, maxPay);
	}


	@Override
	public EmpVO getEmpInfo(int empId) {
		// TODO Auto-generated method stub
		return empRepository.getEmpInfo(empId);
	}

	@Override
	public void updateEmp(EmpVO emp) {
		// TODO Auto-generated method stub
		empRepository.deleteJobHistory(emp.getEmployeeId());
		empRepository.updateEmp(emp);
	}
	
	@Override
	public void updateManager(int empId) {
		// TODO Auto-generated method stub
		empRepository.updateManager(empId);
	}

	@Override
	public void insertEmp(EmpVO emp) {
		// TODO Auto-generated method stub
		empRepository.insertEmp(emp);
	}

	@Override
	public void deleteEmp(int empId) {
		// TODO Auto-generated method stub
		empRepository.deleteJobHistory(empId);
		empRepository.updateManager(empId);
		empRepository.deleteEmp(empId);
	}

	@Override
	public void deleteJobHistory(int empId) {
		// TODO Auto-generated method stub
		empRepository.deleteJobHistory(empId);
	}

	@Override
	public List<Map<String, Object>> getAllDeptId() {
		// TODO Auto-generated method stub
		return empRepository.getAllDeptId();
	}

	@Override
	public List<Map<String, Object>> getAllJobId() {
		// TODO Auto-generated method stub
		return empRepository.getAllJobId();
	}

	@Override
	public List<Map<String, Object>> getAllManagerId() {
		// TODO Auto-generated method stub
		return empRepository.getAllManagerId();
	}

	
	

	
	
	
	
	
}
