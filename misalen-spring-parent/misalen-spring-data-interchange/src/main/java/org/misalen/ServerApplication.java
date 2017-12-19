package org.misalen;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @EnableCaching 开启缓存
 * 
 * @MapperScan mybatis Mapper包
 * 
 * @ServletComponentScan 自定义Servlet 包路径
 * 
 * @EnableTransactionManagement 开启事物
 * 
 * @SpringBootApplication 程序主类
 * 
 *
 */
@EnableCaching
@ServletComponentScan
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = { "org.misalen" })
public class ServerApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication application = new SpringApplication(ServerApplication.class);
		Map<String, Object> map = new HashMap<>();
		map.put("spring.config.name", "system");
		application.setDefaultProperties(map);
		application.run(args);
	}

}
