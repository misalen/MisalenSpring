package org.misalen.cas.pac4j;

import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.realm.Realm;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.client.rest.CasRestFormClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.cas.config.CasProtocol;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.http.client.direct.ParameterClient;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.pac4j.jwt.profile.JwtGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import io.buji.pac4j.realm.Pac4jRealm;
import io.buji.pac4j.subject.Pac4jSubjectFactory;

@Configuration
@Order(2)
public class Pac4jConfig {

	@Value("#{ @environment['cas.prefixUrl'] ?: null }")
	private String prefixUrl;

	@Value("#{ @environment['cas.loginUrl'] ?: null }")
	private String casLoginUrl;

	@Value("#{ @environment['cas.callbackUrl'] ?: null }")
	private String callbackUrl;

	/**
	 * JWT Token 生成器，对CommonProfile生成然后每次携带token访问
	 * 
	 * @return
	 */
	@Bean
	protected JwtGenerator<CommonProfile> jwtGenerator() {
		return new JwtGenerator<CommonProfile>();
	}

	/**
	 * JWT校验器，也就是目前设置的ParameterClient进行的校验器，是rest/或者前后端分离的核心校验器
	 * 
	 * @return
	 */
	@Bean
	protected JwtAuthenticator jwtAuthenticator() {
		return new JwtAuthenticator();
	}

	/**
	 * cas的基本设置，包括或url等等，rest调用协议等
	 * 
	 * @return
	 */
	@Bean
	public CasConfiguration casConfiguration() {
		CasConfiguration casConfiguration = new CasConfiguration(casLoginUrl);
		casConfiguration.setProtocol(CasProtocol.CAS20);
		casConfiguration.setPrefixUrl(prefixUrl);
		return casConfiguration;
	}

	/**
	 * pac4jRealm
	 * 
	 * @return
	 */
	@Bean(name = "pac4jRealm")
	public Realm pac4jRealm() {
		return new Pac4jRealm();
	}

	/**
	 * 通过rest接口可以获取tgt，获取service ticket，甚至可以获取CasProfile
	 * 
	 * @return
	 */
	@Bean
	protected CasRestFormClient casRestFormClient(CasConfiguration casConfiguration) {
		CasRestFormClient casRestFormClient = new CasRestFormClient();
		casRestFormClient.setConfiguration(casConfiguration);
		casRestFormClient.setName("rest");
		return casRestFormClient;
	}

	/**
	 * casClient
	 * 
	 * @return
	 */
	@Bean
	public CasClient casClient(CasConfiguration casConfiguration) {
		CasClient casClient = new CasClient();
		casClient.setConfiguration(casConfiguration);
		casClient.setCallbackUrl(callbackUrl);
		casClient.setName("CasClient");
		return casClient;
	}

	/**
	 * token校验相关
	 * 
	 * @return
	 */
	@Bean
	protected Clients clients(CasClient casClient, CasRestFormClient casRestFormClient) {
		// 可以设置默认client
		Clients clients = new Clients();
		// token校验器，可以用HeaderClient更安全
		ParameterClient parameterClient = new ParameterClient("token", jwtAuthenticator());
		parameterClient.setSupportGetRequest(true);
		parameterClient.setName("jwt");
		clients.setClients(casClient, casRestFormClient, parameterClient);
		return clients;
	}

	@Bean
	protected Config casConfig(Clients clients) {
		Config config = new Config();
		config.setClients(clients);
		return config;
	}

	/**
	 * 由于cas代理了用户，所以必须通过cas进行创建对象
	 *
	 * @return
	 */
	@Bean(name = "subjectFactory")
	protected SubjectFactory subjectFactory() {
		return new Pac4jSubjectFactory();
	}

	/**
	 * 注册单点登出的listener
	 * 
	 * @return
	 */
	@Bean
	public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener() {
		ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> bean = new ServletListenerRegistrationBean<SingleSignOutHttpSessionListener>();
		bean.setListener(new SingleSignOutHttpSessionListener());
		bean.setEnabled(true);
		return bean;
	}

	/**
	 * 注册单点登出filter
	 * 
	 * @return
	 */
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public FilterRegistrationBean<SingleSignOutFilter> singleSignOutFilter() {
		FilterRegistrationBean<SingleSignOutFilter> bean = new FilterRegistrationBean<SingleSignOutFilter>();
		bean.setName("singleSignOutFilter");
		SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
		singleSignOutFilter.setCasServerUrlPrefix(prefixUrl);
		singleSignOutFilter.setIgnoreInitConfiguration(true);
		bean.setFilter(singleSignOutFilter);
		bean.addUrlPatterns("/*");
		bean.setEnabled(true);
		return bean;
	}
}
