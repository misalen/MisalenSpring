package org.misalen.system;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRestTest {
	private TestRestTemplate template=new TestRestTemplate();

	@Test
	public void run() {
		String url = "http://localhost:8080/sys/dictionary/data/list/123";
		String result = template.postForObject(url,null, String.class);
		System.err.println(result);
	}
}
