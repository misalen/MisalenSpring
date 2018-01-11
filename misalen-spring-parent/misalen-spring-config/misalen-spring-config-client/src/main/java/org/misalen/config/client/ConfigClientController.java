package org.misalen.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {
	// 取配置文件中的值
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.thymeleaf.prefix}")
	private String prefix;

	@GetMapping("/url")
	public String getProfile() {
		return url + "==" + prefix;
	}
}