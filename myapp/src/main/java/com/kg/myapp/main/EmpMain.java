package com.kg.myapp.main;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.kg.myapp.dao.EmpService;
import com.kg.myapp.dao.IEmpService;
import com.kg.myapp.vo.EmpVO;

public class EmpMain {

	public static void main(String[] args) {

		AbstractApplicationContext con =
				new GenericXmlApplicationContext("app_config.xml");
		
		
		 IEmpService empService = con.getBean("empService",IEmpService.class);
		 /* System.out.println("-----------------------------");
		 * System.out.println(empService.getEmpCount());
		 * System.out.println("-----------------------------");
		 * System.out.println(empService.getEmpCount(30));
		 * System.out.println("-----------------------------");
		 * System.out.println(empService.getEmpList());
		 * System.out.println("-----------------------------");
		 * System.out.println(empService.getAllManagerId());
		 * System.out.println("-----------------------------");
		 */
		 empService.deleteEmp(100);
		 con.close();
	}

}
