package fjpc.zeebe.zeebeimporter.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JsonConfig {

    @Value("classpath:schemas/process-message.json")
    private Resource processMessageJson;
    @Value("classpath:schemas/process-instance-message.json")
    private Resource processInstanceMessageJson;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public Map<String, JsonSchema> schemas() throws IOException {
        Map<String, JsonSchema> schemas = new HashMap<>();
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        schemas.put("process-message", factory.getSchema(processMessageJson.getContentAsString(Charset.defaultCharset())));
        schemas.put("process-instance-message", factory.getSchema(processInstanceMessageJson.getContentAsString(Charset.defaultCharset())));
        return schemas;
    }

}
