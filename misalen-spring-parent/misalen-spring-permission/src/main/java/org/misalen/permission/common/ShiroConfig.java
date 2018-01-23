package org.misalen.permission.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.misalen.permission.service.SysRoleResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import net.sf.ehcache.CacheManager;

@Configuration
public class ShiroConfig {

	@Autowired
	private SysRoleResourcesService sysRoleResourcesService;
	@Autowired
	private ShiroRealmDb shiroRealmDb;
	@Autowired
	private ShiroAuthorizationByRole shiroAuthorizationByRole;

	@Bean("shiroFilter")
	protected ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/home/");
		shiroFilterFactoryBean.setUnauthorizedUrl("/result/unauth");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());
		Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
		filters.put("orRole", shiroAuthorizationByRole);
		shiroFilterFactoryBean.setFilters(filters);
		return shiroFilterFactoryBean;
	}

	@Bean(name = "securityManager")
	protected SecurityManager securityManager(RememberMeManager meManager, EhCacheManager ehCacheManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealmDb);
		securityManager.setCacheManager(ehCacheManager);
		securityManager.setRememberMeManager(meManager);
		return securityManager;
	}

	@Bean
	protected RememberMeManager rememberMeManager() {
		CookieRememberMeManager manager = new CookieRememberMeManager();
		manager.setCipherKey(Base64.decode("5aaC5qKm5oqA5pyvAAAAAA=="));
		SimpleCookie cookie = new SimpleCookie("RememberMeManager");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(7 * 24 * 60 * 60);
		manager.setCookie(cookie);
		return manager;
	}

	@Bean
	protected EhCacheManager ehCacheManager() {
		CacheManager cacheManager = CacheManager.getCacheManager("es");
		if (cacheManager == null) {
			try {
				cacheManager = CacheManager
						.create(ResourceUtils.getInputStreamForPath("classpath:config/ehcache-shiro.xml"));
			} catch (IOException e) {
				throw new RuntimeException("initialize cacheManager failed");
			}
		}
		EhCacheManager ehCacheManager = new EhCacheManager();
		ehCacheManager.setCacheManager(cacheManager);
		return ehCacheManager;

	}

	protected Map<String, String> loadFilterChainDefinitions() {
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		filterChainDefinitionMap.put("/**/css/**", "anon");
		filterChainDefinitionMap.put("/**/images/**", "anon");
		filterChainDefinitionMap.put("/plugins/**", "anon");
		filterChainDefinitionMap.put("/api/**", "anon");
		List<Object[]> list = sysRoleResourcesService.getSystemPerm();
		Map<String, String> map = new HashMap<String, String>();
		for (Object[] objects : list) {
			if (map.containsKey(objects[0])) {
				map.put(objects[0].toString(), map.get(objects[0].toString()) + "," + objects[1].toString());
			} else {
				map.put(objects[0].toString(), objects[1].toString());
			}
		}
		for (Entry<String, String> objects : map.entrySet()) {
			filterChainDefinitionMap.put(objects.getKey(), "orRole[" + objects.getValue() + "]");
		}
		filterChainDefinitionMap.put("/**", "user");
		return filterChainDefinitionMap;
	}

	@Bean
	protected FilterRegistrationBean<DelegatingFilterProxy> delegatingFilterProxy() {
		FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBean = new FilterRegistrationBean<DelegatingFilterProxy>();
		DelegatingFilterProxy proxy = new DelegatingFilterProxy();
		proxy.setTargetFilterLifecycle(true);
		proxy.setTargetBeanName("shiroFilter");
		filterRegistrationBean.setFilter(proxy);
		filterRegistrationBean.addInitParameter("targetFilterLifecycle", "true");
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
}
