package com.kg.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kg.myapp.dao.IEmpService;
import com.kg.myapp.util.EmpValidator;
import com.kg.myapp.vo.EmpVO;

@Controller
public class EmpController {
	
	@Autowired
	IEmpService empService;
	

	//getmapping, postmapping 어노테이션 통해 get,post방식 설정 가능, value에는 주소가 들어옴
	//주소와 메서드 매핑
	//model은 데이터를 저장하는 공간이라고 생각하자
	@GetMapping(value="/emp")
	public String mainPage(Model model) {
		model.addAttribute("message","Hello! Welcome to EmpApp.");
		//리턴
		return "home";
	}
	
	@GetMapping(value="/emp/count")
	public String empCount(@RequestParam(value="deptId",required=false,defaultValue="0")int deptId, Model model) {
		if(deptId==0) {
			model.addAttribute("count", empService.getEmpCount());
		}else {
			model.addAttribute("count", empService.getEmpCount(deptId));
		}
		return "emp/count";
	}
	//리턴 타입 void는 요청 주소로 응답한다
	@GetMapping(value="/emp/list")
	public void empList(Model model) {
		model.addAttribute("empList", empService.getEmpList());
	}
	//파라미터 말고 주소로 넘길때 value를 저렇게 설정해주면 된다
	//pathvariable은 경로를 변수로 상호작용해주게 하는 어노테이션(파라미터를 보여주지 말아야 하거나, 파라미터가 너무 많아 가리고 싶을때)
	@GetMapping(value="/emp/{employeeId}")
	public String empView(@PathVariable int employeeId, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(employeeId));
		return "emp/view";
	}
	
	@GetMapping(value="/emp/listofdept")
	public String empListOfDept(@RequestParam(value="deptId",required=false,defaultValue="0")int deptId, Model model) {
		model.addAttribute("empList", empService.getEmpList(deptId));
		//리스트 만들어준걸로 리턴하면 다른 jsp페이지 안만들어도 된다.
		return "emp/list";
	}
	
	@GetMapping(value="/emp/name")
	//requestparam어노테이션 없어도 parameter를 매개변수로 받아준다
	public String empListName(String name, Model model) {
		name = "%"+name+"%";
		model.addAttribute("empList", empService.getEmpList(name));
		return "emp/list";
	}
	
	@GetMapping(value="/emp/pay")
	public String empListPay(int minPay, int maxPay, Model model) {
		model.addAttribute("empList", empService.getEmpList(minPay, maxPay));
		return "emp/list";
	}
	
	@GetMapping(value="/emp/insert")
	public String empInsert(Model model) {
		model.addAttribute("emp", new EmpVO());
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("manList", empService.getAllManagerId());
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("message","insert");
		return "emp/insert";
	}
	
	//EmpVO를 넣어주면 변수대로 매핑해준다. 단 insert.jsp에서 선언한name과 empVO 이름과 같아야한다.
	//insert페이지에서 정보 입력하고 넘어가면 주소에 입력정보가 다 뜨기 때문에  post로 해주어야 한다.
	@PostMapping(value="/emp/insert")
	public String empInsert(@ModelAttribute("emp") @Valid EmpVO emp,
			BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("jobList", empService.getAllJobId());
			model.addAttribute("manList", empService.getAllManagerId());
			model.addAttribute("deptList", empService.getAllDeptId());
			model.addAttribute("message","insert");
			return "emp/insert";
		}
		empService.insertEmp(emp);
		redirectAttributes.addFlashAttribute("message", "사원 정보 저장 완료");
		//redirectattributes를 통해 sendredirect에 세션을 걸어 redirect를 해도 데이터를 보낼 수 있다. 원래는 주소 뒤에 ?empId=xx이런식으로 쓴다.
		return "redirect:/emp/list";
		//list는 get방식으로 보내야 하기 때문에 요청을 다시 보내야 함. sendredirect를 저렇게 쓸 수 있다.
	}
	
	@GetMapping(value="/emp/update/{empId}")
	public String empUpdate(@PathVariable int empId, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(empId));
		model.addAttribute("message","update");
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("manList", empService.getAllManagerId());
		model.addAttribute("deptList", empService.getAllDeptId());
		return "emp/insert";
	}
	
	@PostMapping(value="/emp/update")
	public String empUpdate(EmpVO emp, Model model) {
		empService.updateEmp(emp);
		return "redirect:/emp/" + emp.getEmployeeId();
	}
	
	@GetMapping(value="/emp/delete")
	public String empDelete(int empId, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(empId));
		model.addAttribute("manCount", empService.getManagerCount(empId));
		model.addAttribute("deptCount", empService.getDepartmentCount(empId));
		return "emp/delete";
	}
	
	@PostMapping(value="/emp/delete")
	//위 함수와 duplicate될 경우 매개변수 순서를 바꾸거나 함수이름을 예를들어 empDeleteGet, empDeletePost라고 하면 된다.
	public String empDelete(Model model, int empId) {
		empService.deleteEmp(empId);
		return "redirect:/emp/list";
	}
	
	//괄호에는 예외처리하고 싶은 클래스를 쓰면 된다
	@ExceptionHandler(RuntimeException.class)
	public String runtimeException(Exception e, HttpServletRequest request, Model model) {
		model.addAttribute("url", request.getRequestURI());
		model.addAttribute("exception", e);
		return "error/runtime";
	}
	
}
