package com.zeta.Configurations.JsonDeserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
//https://stackoverflow.com/questions/30841981/how-to-deserialize-a-blank-json-string-value-to-null-for-java-lang-string
public class CustomEmptyStringToNullDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
        String result = StringDeserializer.instance.deserialize(jsonParser, context);
        if (StringUtils.isEmpty(result)) {
            return null;
        }
        return result;
    }
}
