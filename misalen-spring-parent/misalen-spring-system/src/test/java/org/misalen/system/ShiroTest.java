package org.misalen.system;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.misalen.system.service.SysConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroTest {
	@Autowired
	SysConversionService sysConversionService; 
	@Test
	public void run() throws Exception {
		sysConversionService.findByChinese("提交");
		sysConversionService.findByChinese("登陆");
		sysConversionService.findByChinese("登录");
	}

}
