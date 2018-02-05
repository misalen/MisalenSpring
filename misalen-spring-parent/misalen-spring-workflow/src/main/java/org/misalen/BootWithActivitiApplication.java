package org.misalen;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication
@ComponentScan({ "org.activiti.rest.diagram", "org.misalen" })
// @EnableAsync
public class BootWithActivitiApplication extends WebMvcConfigurerAdapter {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(BootWithActivitiApplication.class);
//		Map<String, Object> map = new HashMap<>();
//		String[] names = ModuleUtil.getPropertiesName();
//		String name = TextUtil.join(names);
//		map.put("spring.config.name", name);
//		application.setDefaultProperties(map);
		application.run(args);
	}

}
