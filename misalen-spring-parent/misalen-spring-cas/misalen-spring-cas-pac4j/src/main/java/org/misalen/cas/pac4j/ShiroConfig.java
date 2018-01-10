package org.misalen.cas.pac4j;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.AbstractShiroWebFilterConfiguration;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.pac4j.core.config.Config;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.DelegatingFilterProxy;

import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.filter.SecurityFilter;

/**
 * Shiro生命周期处理器,开启了就会启动报错不知道为什么？---设置为优先启动！！见：LifecycleBeanPostProcessorConfig
 * 
 * @Description:TODO
 * @author:hsj qq:2356899074
 * @time:2017年12月13日 上午11:34:49
 */
@Configuration
@Order(2)
public class ShiroConfig extends AbstractShiroWebFilterConfiguration {

	// -------------------------------------------shiro
	// 相关---------------------------
	/**
	 * shiro管理器
	 * 
	 * @Description:TODO
	 * @author:hsj qq:2356899074
	 * @time:2017年12月11日 下午2:33:05
	 * @param pac4jRealm
	 * @param subjectFactory
	 * @return
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager securityManager(Realm pac4jRealm, SubjectFactory subjectFactory) {
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		defaultWebSecurityManager.setRealm(pac4jRealm);
		defaultWebSecurityManager.setSubjectFactory(subjectFactory);
		return defaultWebSecurityManager;
	}

	@Bean
	public FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBean() {
		FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBean = new FilterRegistrationBean<DelegatingFilterProxy>();
		filterRegistrationBean.setFilter(new DelegatingFilterProxy("shiroFilter"));
		filterRegistrationBean.addInitParameter("targetFilterLifecycle", "true");
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}

	/**
	 * 对过滤器进行调整
	 *
	 * @param securityManager
	 * @return
	 */
	@Bean(name = "shiroFilter")
	protected ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager, Config config) {
		ShiroFilterFactoryBean filterFactoryBean = super.shiroFilterFactoryBean();
		filterFactoryBean.setSecurityManager(securityManager);

		Map<String, Filter> filters = new HashMap<>();
		SecurityFilter securityFilter = new SecurityFilter();
		securityFilter.setClients("CasClient,rest,jwt");
		securityFilter.setConfig(config);
		filters.put("casSecurityFilter", securityFilter);

		CallbackFilter callbackFilter = new CallbackFilter();
		callbackFilter.setConfig(config);
		filters.put("callbackFilter", callbackFilter);

		filterFactoryBean.setFilters(filters);

		return filterFactoryBean;
	}

	@Bean
	public ShiroFilterChainDefinition shiroFilterChainDefinition() {
		DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
		definition.addPathDefinition("/callback", "callbackFilter");
		definition.addPathDefinition("/**", "casSecurityFilter");
		return definition;
	}

}
