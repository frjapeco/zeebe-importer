package fjpc.zeebe.zeebeimporter.amqp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import fjpc.zeebe.zeebeimporter.service.ProcessInstanceService;
import fjpc.zeebe.zeebeimporter.service.ProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

import static fjpc.zeebe.zeebeimporter.amqp.MessageType.UNKNOWN;


@Component
public class MessageListener {

	private final ObjectMapper mapper;
	private final Map<String, JsonSchema> schemas;
	private final ProcessService processService;
	private final ProcessInstanceService processInstanceService;
	private final Logger log = LoggerFactory.getLogger(MessageListener.class);

	public MessageListener(ObjectMapper mapper, Map<String, JsonSchema> schemas, ProcessService processService, ProcessInstanceService processInstanceService) {
		this.mapper = mapper;
		this.schemas = schemas;
		this.processService = processService;
		this.processInstanceService = processInstanceService;
	}

	@RabbitListener(queues = "zeebe")
	public void receive(String json) throws IOException {
		log.info("Receiving message: {}", json);
		final String traceId = MDC.get("traceId");
        switch (getMessageType(json)) {
			case PROCESS:
				processService.save(json)
						.doOnSuccess(p -> logCallback("Process saved successfully", "INFO", traceId))
						.doOnError(e -> logCallback("Unable to save process caused by " + e.getMessage(), "ERROR", traceId))
						.subscribe();
				break;
			case PROCESS_INSTANCE:
				processInstanceService.save(json)
						.doOnSuccess(p -> logCallback("Process instance saved successfully", "INFO", traceId))
						.doOnError(e -> logCallback("Unable to save process instance caused by " + e.getMessage(), "ERROR", traceId))
						.subscribe();
				break;
			case UNKNOWN:
			default:
				log.warn("Unrecognized message unable to process");
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
		return UNKNOWN;
	}

	private void logCallback(String message, String level, String traceId) {
		try (MDC.MDCCloseable mdc = MDC.putCloseable("traceId", traceId)){
			switch (level) {
				case "INFO":
					log.info(message);
					break;
				case "ERROR":
					log.error(message);
					break;
			}
		}
	}

}
