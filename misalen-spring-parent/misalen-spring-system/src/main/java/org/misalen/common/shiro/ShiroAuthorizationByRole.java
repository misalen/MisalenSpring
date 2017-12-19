package org.misalen.common.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

public class ShiroAuthorizationByRole extends PermissionsAuthorizationFilter {

	/**
	 * 自定义鉴权 true 通过 false 未通过
	 */
	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {
		Subject subject = getSubject(request, response);
		String[] roles = (String[]) mappedValue;
		boolean isPermitted = false;
		if (roles != null && roles.length > 0) {
			for (String string : roles) {
				if (subject.hasRole(string)) {
					isPermitted = true;
					break;
				}
			}
		} else {
			isPermitted = true;
		}
		return isPermitted;
	}

}
