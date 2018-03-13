package com.tenmaker.backupwd.components;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonTimeSerializer extends JsonSerializer<Date> {

	public void serialize(Date value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
            JsonProcessingException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String formattedDate = formatter.format(value);
		jgen.writeString(formattedDate);
	}



}
