package org.misalen.common.advice.structure;

import java.util.List;

import org.misalen.common.exception.CustomerException;
import org.misalen.common.utils.ListUtil;
import org.misalen.common.validate.ValidatorFactory;
import org.misalen.common.validate.exception.ValidateError;

public class FormUtil {

	/**
	 * 检查注解
	 * 
	 * @return 错误信息
	 * @throws CustomerException
	 */
	public static void validate(Object object) {
		List<ValidateError> errors = ValidatorFactory.validate(object);
		if (!ListUtil.isNullOrEmpty(errors)) {
//			for (ValidateError validateError : errors) {
//				logger.error(object.getClass().getName() + "\t" + validateError.getMessage());
//			}
			throw new CustomerException(ErrorCode.SYS_ERROR_CODE_700, errors.get(0).getMessage());
		}

	}
}
