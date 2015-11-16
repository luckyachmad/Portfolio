package inv.com.validator;

import inv.com.entity.Customer;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class BaseValidator implements Validator{

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class class1) {
		// TODO Auto-generated method stub
		return Customer.class.equals(class1);
	}

	@SuppressWarnings("unused")
	@Override
	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stubu
		
//		Customer customer = (Customer) object;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "ID harus di isi.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NAME harus di isi.");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "alamat", "ID harus di isi.");
//			ValidationUtils.rejectIfEmptyOrWhitespace(errors,"", "ID harus di isi.");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock", "ID harus di isi.");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "ID harus di isi.");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "ID harus di isi.");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "ID harus di isi.");
	}

	
	
}
