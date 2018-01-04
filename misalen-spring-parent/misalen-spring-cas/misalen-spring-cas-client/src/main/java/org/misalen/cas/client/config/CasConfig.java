package org.misalen.cas.client.config;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.misalen.cas.client.autoconfig.SpringCasAutoconfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CasConfig {

	@Autowired
	SpringCasAutoconfig autoconfig;

	private static boolean casEnabled = true;

	public CasConfig() {
	}

	@Bean
	public SpringCasAutoconfig getSpringCasAutoconfig() {
		return new SpringCasAutoconfig();
	}

	@Bean
	public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener() {
		ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> listener = new ServletListenerRegistrationBean<SingleSignOutHttpSessionListener>();
		listener.setEnabled(casEnabled);
		listener.setListener(new SingleSignOutHttpSessionListener());
		listener.setOrder(1);
		return listener;
	}

	@Bean
	public FilterRegistrationBean<SingleSignOutFilter> singleSignOutFilter() {
		FilterRegistrationBean<SingleSignOutFilter> filterRegistration = new FilterRegistrationBean<SingleSignOutFilter>();
		filterRegistration.setFilter(new SingleSignOutFilter());
		filterRegistration.setEnabled(casEnabled);
		if (autoconfig.getSignOutFilters().size() > 0) {
			filterRegistration.setUrlPatterns(autoconfig.getSignOutFilters());
		} else {
			filterRegistration.addUrlPatterns("/*");
		}
		filterRegistration.addInitParameter("casServerUrlPrefix", autoconfig.getCasServerUrlPrefix());
		filterRegistration.setOrder(2);
		return filterRegistration;
	}

	@Bean
	public FilterRegistrationBean<AuthenticationFilter> authenticationFilter() {
		FilterRegistrationBean<AuthenticationFilter> filterRegistration = new FilterRegistrationBean<AuthenticationFilter>();
		filterRegistration.setFilter(new AuthenticationFilter());
		filterRegistration.setEnabled(casEnabled);
		if (autoconfig.getAuthFilters().size() > 0) {
			filterRegistration.setUrlPatterns(autoconfig.getAuthFilters());
		} else {
			filterRegistration.addUrlPatterns("/*");
		}
		filterRegistration.addInitParameter("casServerLoginUrl", autoconfig.getCasServerLoginUrl());
		filterRegistration.addInitParameter("serverName", autoconfig.getServerName());
		filterRegistration.addInitParameter("useSession", autoconfig.isUseSession() ? "true" : "false");
		filterRegistration.addInitParameter("redirectAfterValidation",
				autoconfig.isRedirectAfterValidation() ? "true" : "false");
		filterRegistration.setOrder(3);
		return filterRegistration;
	}

	@Bean
	public FilterRegistrationBean<Cas20ProxyReceivingTicketValidationFilter> cas20ProxyReceivingTicketValidationFilter() {
		FilterRegistrationBean<Cas20ProxyReceivingTicketValidationFilter> filterRegistration = new FilterRegistrationBean<Cas20ProxyReceivingTicketValidationFilter>();
		filterRegistration.setFilter(new Cas20ProxyReceivingTicketValidationFilter());
		filterRegistration.setEnabled(casEnabled);
		if (autoconfig.getValidateFilters().size() > 0) {
			filterRegistration.setUrlPatterns(autoconfig.getValidateFilters());
		} else {
			filterRegistration.addUrlPatterns("/*");
		}
		filterRegistration.addInitParameter("casServerUrlPrefix", autoconfig.getCasServerUrlPrefix());
		filterRegistration.addInitParameter("serverName", autoconfig.getServerName());
		filterRegistration.setOrder(4);
		return filterRegistration;
	}

	@Bean
	public FilterRegistrationBean<HttpServletRequestWrapperFilter> httpServletRequestWrapperFilter() {
		FilterRegistrationBean<HttpServletRequestWrapperFilter> filterRegistration = new FilterRegistrationBean<HttpServletRequestWrapperFilter>();
		filterRegistration.setFilter(new HttpServletRequestWrapperFilter());
		filterRegistration.setEnabled(true);
		if (autoconfig.getRequestWrapperFilters().size() > 0) {
			filterRegistration.setUrlPatterns(autoconfig.getRequestWrapperFilters());
		} else {
			filterRegistration.addUrlPatterns("/*");
		}
		filterRegistration.setOrder(5);
		return filterRegistration;
	}

	@Bean
	public FilterRegistrationBean<AssertionThreadLocalFilter> assertionThreadLocalFilter() {
		FilterRegistrationBean<AssertionThreadLocalFilter> filterRegistration = new FilterRegistrationBean<AssertionThreadLocalFilter>();
		filterRegistration.setFilter(new AssertionThreadLocalFilter());
		filterRegistration.setEnabled(true);
		if (autoconfig.getAssertionFilters().size() > 0) {
			filterRegistration.setUrlPatterns(autoconfig.getAssertionFilters());
		} else {
			filterRegistration.addUrlPatterns("/*");
		}
		filterRegistration.setOrder(6);
		return filterRegistration;
	}
}
