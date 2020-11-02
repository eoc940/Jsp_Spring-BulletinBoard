package com.kg.myapp.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.kg.myapp.vo.EmpVO;
import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing.Validation;

@Component
public class EmpValidator implements Validator {
//	private javax.validation.Validator validator;
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		ValidatorFactory vFactory = Validation.buildDefaultValidatorFactory();
//		validator = vFactory.usingContext().getValidator();
//	}
	
	
	@Override
	public boolean supports(Class<?> clazz) {

		return EmpVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
//		Set<ConstraintViolation<Object>> violations = validator.validate(target);
//		for(ConstraintViolation<Object> violation : violations) {
//			String propertyPath = violation.getPropertyPath().toString();
//			String message = violation.getMessage();
//			errors.rejectValue(propertyPath, message, message);
//		}
		EmpVO form = (EmpVO)target;
		if(form.getEmployeeId() <= 206) {
			errors.rejectValue("employeeId", "emp.employeeId", "사원번호는 207번 이상");
		}

	}

}
