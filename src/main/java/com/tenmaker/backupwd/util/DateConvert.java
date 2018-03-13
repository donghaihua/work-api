package com.tenmaker.backupwd.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.DateFormat;

public class DateConvert extends MappingJackson2HttpMessageConverter {

	
	public void setObjectMapper(ObjectMapper objectMapper) {
		DateFormat myDateFormat = DateFormat.getDateInstance();

		objectMapper.setDateFormat(myDateFormat); // 1.8 and above
		objectMapper.getDeserializationConfig().with(myDateFormat);

		super.setObjectMapper(objectMapper);
	}

}
