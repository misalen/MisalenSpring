package org.misalen.common.validate;

import java.util.List;

import org.misalen.common.validate.exception.ValidateError;

import junit.framework.TestCase;

public class ValidableTest extends TestCase {

	public void testApp() {
		List<ValidateError> errors = ValidatorFactory.validate(new LoginForm());
		for (ValidateError validateError : errors) {
			System.err.println(validateError.toString());
		}
	}
}
