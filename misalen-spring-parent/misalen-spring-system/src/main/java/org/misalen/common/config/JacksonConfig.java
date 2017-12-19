package org.misalen.common.config;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;


@Configuration
@ConditionalOnClass(MappingJackson2HttpMessageConverter.class)
public class JacksonConfig {
	@Bean
	@ConditionalOnMissingBean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		return new MyMappingJackson2HttpMessageConverter();
	}
	public class MyMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
		@Override
		protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage)
				throws IOException, HttpMessageNotWritableException {
			if(object instanceof String){
				outputMessage.getBody().write(((String) object).getBytes("UTF-8"));
			}else{
				super.writeInternal(object, type, outputMessage);
			}
		}

	}
}
