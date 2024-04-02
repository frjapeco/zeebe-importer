package fjpc.zeebe.zeebeimporter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fjpc.zeebe.zeebeimporter.amqp.dto.ProcessInstanceMessage;
import fjpc.zeebe.zeebeimporter.domain.ProcessInstance;
import fjpc.zeebe.zeebeimporter.respository.ProcessInstanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class ProcessInstanceService {

    private final ObjectMapper mapper;
    private final ProcessInstanceRepository repository;
    private final Logger log = LoggerFactory.getLogger(ProcessInstanceService.class);


    public ProcessInstanceService(ObjectMapper mapper, ProcessInstanceRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public Mono<ProcessInstance> save(String json) throws JsonProcessingException {
        final var ctx = MDC.getCopyOfContextMap();
        final var message = mapper.readValue(json, ProcessInstanceMessage.class);
        if (message == null || message.getProcessInstanceKey() <= 0L || message.getProcessDefinitionKey() <= 0L) {
            return Mono.empty();
        }
        var instance = new ProcessInstance();
        instance.setId(message.getProcessInstanceKey());
        instance.setProcessId(message.getProcessDefinitionKey());
        assert instance.getId() != null;
        return repository.findById(instance.getId())
                .flatMap(i -> update(instance))
                .switchIfEmpty(repository.save(instance))
                .doOnSuccess(pi -> handleSuccessStorage(pi, ctx))
                .doOnError(e -> handleError(e, ctx));
    }

    private Mono<ProcessInstance> update(ProcessInstance instance) {
        instance.setIsNew(false);
        return repository.save(instance);
    }

    private void handleSuccessStorage(ProcessInstance pi, Map<String, String> ctx) {
        try (var mdc = MDC.putCloseable("trace-id", ctx.get("trace-id"))) {
            if (pi == null) {
                return;
            }
            log.info("Process instance with id {} stored successfully", pi.getProcessId());
        }
    }

    private void handleError(Throwable e, Map<String, String> ctx) {
        try (var mdc = MDC.putCloseable("trace-id", ctx.get("trace-id"))) {
            log.error("Unable to store process in database, caused by: {}", e.getMessage());
        }
    }

}
