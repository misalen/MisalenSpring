package org.misalen;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.misalen.common.global.ModuleUtil;
import org.misalen.common.utils.TextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//"org.activiti.rest.diagram",
@ComponentScan({  "org.misalen" })
public class BootWithActivitiApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication application = new SpringApplication(BootWithActivitiApplication.class);
		Map<String, Object> map = new HashMap<>();
		String[] names = ModuleUtil.getPropertiesName();
		String name = TextUtil.join(names);
		map.put("spring.config.name", name);
		application.setDefaultProperties(map);
		application.run(args);
	}
//
//	@Bean
//	InitializingBean usersAndGroupsInitializer(final SysAdminRepository sysAdminRepository) {
//
//		return new InitializingBean() {
//			public void afterPropertiesSet() throws Exception {
//				SysAdmin admin =sysAdminRepository.findOne("8a80ace761ffbc700161ffbc7e330000");	
////				SysAdmin admin=new SysAdmin();
////				admin.setPrimaryKey("8a80ace761ffbc700161ffbc7e330000");
//				admin.setUsername("52");
//				admin.setNickname(null);
//				admin.setLoginPwd("2");
//				sysAdminRepository.save(admin);
//			}
//		};
//	}
}
