package com.pathwheel.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@ComponentScan(basePackages = "com.pathwheel")
@RestController
@RequestMapping("/api")
public class JacksonContextResolver{

	private ObjectMapper objectMapper;

	/*public JacksonContextResolver() throws Exception {

		Logger.info("Pathwheel API v1.0.0.12 (01/08/2021 13:33:00)");
		PostgreSql.init("pathwheel", "127.0.0.1", "PATHWHEEL", "pathwheel", "xxxxxx", 5, 10);
		PostgreSql.conectar();
		this.objectMapper = new Jackson2ObjectMapperBuilder()
				.featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
				.featuresToEnable(SerializationFeature.INDENT_OUTPUT)
				.build();
	}*/

	@GetMapping(value = "/endpoint", produces = MediaType.APPLICATION_JSON_VALUE)
	public MappingJackson2HttpMessageConverter getObjectMapper() {
		return new MappingJackson2HttpMessageConverter(objectMapper);
	}
}

/*
//sugestão
@Bean
	public ObjectMapper objectMapper() throws Exception {

		Logger.info("Pathwheel API v1.0.0.12 (01/08/2019 13:33:00)");
		PostgreSql.init("pathwheel", "127.0.0.1", "PATHWHEEL", "pathwheel", "xxxxxx", 5, 10);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

		return objectMapper;
	}
*/