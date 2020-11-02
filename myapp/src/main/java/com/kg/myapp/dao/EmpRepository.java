package com.kg.myapp.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kg.myapp.vo.EmpDetailVO;
import com.kg.myapp.vo.EmpVO;


//Repository는 DAO라고 생각하면 된다
@Repository
public class EmpRepository implements IEmpRepository{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//익명 클래스 : 클래스를 바로 만들어서 한번만 쓸려고 바로 만든다
	RowMapper<EmpVO> empMapper = new RowMapper<EmpVO>() {
		
			@Override
			public EmpVO mapRow(ResultSet rs, int count) 
					throws SQLException {
			EmpVO emp = new EmpVO();
			emp.setEmployeeId(rs.getInt("employee_id"));
			emp.setFirstName(rs.getString("first_name"));
			emp.setLastName(rs.getString("last_name"));
			emp.setEmail(rs.getString("email"));
			emp.setPhoneNumber(rs.getString("phone_number"));
			emp.setHireDate(rs.getDate("hire_date"));
			emp.setJobId(rs.getString("job_id"));
			emp.setSalary(rs.getDouble("salary"));
			emp.setCommissionPct(rs.getDouble("commission_pct"));
			emp.setManagerId(rs.getInt("manager_id"));
			emp.setDepartmentId(rs.getInt("department_id"));
			return emp;
			}
	};
		
	
	
	

	@Override
	public int getEmpCount() {
		String sql = "select count(*) from employees";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public int getEmpCount(int deptId) {
		String sql = "select count(*) from employees where department_id=?";
		
		return jdbcTemplate.queryForObject(sql, Integer.class,deptId);
	}
	
	@Override
	public int getManagerCount(int empId) {
		String sql = "select count(*) from employees where manager_id=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, empId);
	}

	@Override
	public int getDepartmentCount(int empId) {
		String sql = "select count(*) from departments where manager_id=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, empId);
	}
	
	@Override
	public List<EmpVO> getEmpList() {
		String sql = "select * from employees";
		
		return jdbcTemplate.query(sql, empMapper);
	}
	
	@Override
	public List<EmpVO> getEmpList(int deptId) {
		String sql = "select * from employees where department_id=?";
		return jdbcTemplate.query(sql, empMapper, deptId);
	}
	
	@Override
	public List<EmpVO> getEmpList(String name) {
		String sql = "select * from employees where first_name||last_name like ?";
		return jdbcTemplate.query(sql, empMapper, name);
	}
	
	@Override
	public List<EmpVO> getEmpList(int minPay, int maxPay) {
		String sql = "select * from employees where salary Between ? and ?";
		return jdbcTemplate.query(sql, empMapper, minPay, maxPay);
	}
	

	@Override
	public EmpVO getEmpInfo(int empId) {
		String sql = "select employee_id, first_name, last_name, email, phone_number, "
				+ "hire_date, e.job_id, job_title, salary, commission_pct, e.manager_id, "
				+ "manager_name, e.department_id, department_name "
				+ "from employees e "
				+ "left join jobs j "
				+ "on e.job_id=j.job_id "
				+ "left join departments d "
				+ "on e.department_id=d.department_id "
				+ "left join (select employee_id manager_id, first_name||' '||last_name manager_name "
				+ "from employees "
				+ "where employee_id in (select distinct manager_id from employees)) m "
				+ "on e.manager_id=m.manager_id "
				+ "where employee_id=?";
		
		return jdbcTemplate.queryForObject(sql, new RowMapper<EmpDetailVO>(){
			@Override
			public EmpDetailVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				EmpDetailVO emp = new EmpDetailVO();
				
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommissionPct(rs.getDouble("commission_pct"));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDepartmentId(rs.getInt("department_id"));
				emp.setJobTitle(rs.getString("job_title"));
				emp.setManagerName(rs.getString("manager_name"));
				emp.setDepartmentName(rs.getString("department_name"));
				return emp;
			}
			
		}, empId);
	
	}
	

	@Override
	public void updateEmp(EmpVO emp) {
		String sql = "update employees set first_name=?,"
				+ " last_name=?, email=?, phone_number=?,"
				+ " hire_date=?, job_id=?, salary=?,"
				+ " commission_pct=?, manager_id=?,"
				+ " department_id=? where employee_id=?";
		jdbcTemplate.update(sql,emp.getFirstName(),
				emp.getLastName(),emp.getEmail(),
				emp.getPhoneNumber(),emp.getHireDate(),
				emp.getJobId(),emp.getSalary(),emp.getCommissionPct(),
				emp.getManagerId(),emp.getDepartmentId(),emp.getEmployeeId());
	}
	
	@Override 
	public void updateManager(int empId) {
		String sql = "update (select * from employees where manager_id=?) set manager_id=null";
		jdbcTemplate.update(sql, empId);
		sql = "update (select * from departments where manager_id=?) set manager_id=null";
		jdbcTemplate.update(sql, empId);
		
	}

	@Override
	public void insertEmp(EmpVO emp) {
		String sql = "insert into employees "
				+ "values(?,?,?,?,?,sysdate,?,?,?,?,?)";
		jdbcTemplate.update(sql,emp.getEmployeeId(),emp.getFirstName(),
				emp.getLastName(),emp.getEmail(),emp.getPhoneNumber(),
				emp.getJobId(),emp.getSalary(),emp.getCommissionPct(),
				emp.getManagerId(),emp.getDepartmentId());
	}

	@Override
	public void deleteEmp(int empId) {
		String sql = "delete from employees where employee_id=?";
		jdbcTemplate.update(sql, empId);
		
	}

	@Override
	public void deleteJobHistory(int empId) {
		String sql = "delete from job_history where employee_id=?";
		jdbcTemplate.update(sql, empId);
		
	}

	@Override
	public List<Map<String, Object>> getAllDeptId() {
		String sql = "select department_id as departmentId,"
				+ " department_name as departmentName from departments";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> getAllJobId() {
		String sql = "select job_id as jobId, job_title as jobTitle "
				+ "from jobs";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> getAllManagerId() {
		String sql = "select employee_id as managerId, "
				+ "first_name||' '||last_name as managerName "
				+ "from employees "
				+ "where employee_id in "
				+ "(select distinct manager_id from employees)";
		return jdbcTemplate.queryForList(sql);
	}
	
	
}
