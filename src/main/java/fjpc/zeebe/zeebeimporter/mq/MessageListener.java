package fjpc.zeebe.zeebeimporter.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import fjpc.zeebe.zeebeimporter.mq.dto.ProcessInstanceMessage;
import fjpc.zeebe.zeebeimporter.service.ProcessInstanceService;
import fjpc.zeebe.zeebeimporter.service.ProcessService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;


@Component
public class MessageListener {

	private final ObjectMapper mapper;
	private final Map<String, JsonSchema> schemas;
	private final ProcessService processService;
	private final ProcessInstanceService processInstanceService;

	public MessageListener(ObjectMapper mapper, Map<String, JsonSchema> schemas, ProcessService processService, ProcessInstanceService processInstanceService) {
		this.mapper = mapper;
		this.schemas = schemas;
		this.processService = processService;
		this.processInstanceService = processInstanceService;
	}

	@RabbitListener(queues = "zeebe")
	public void read(String json) throws IOException {
        switch (getMessageType(json)) {
			case PROCESS:
				processService.save(json);
				break;
			case PROCESS_INSTANCE:
				processInstanceService.save(json);
				break;
			default:
		}
    }

	private MessageType getMessageType(String json) throws JsonProcessingException {
		JsonNode node = mapper.readTree(json);
		if (schemas.get("process-message").validate(node).isEmpty()) {
			return MessageType.PROCESS;
		}
		if (schemas.get("process-instance-message").validate(node).isEmpty()) {
			return MessageType.PROCESS_INSTANCE;
		}
		return MessageType.UNKNOWN;
	}

}
