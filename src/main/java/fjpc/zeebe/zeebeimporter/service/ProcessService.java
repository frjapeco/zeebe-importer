package fjpc.zeebe.zeebeimporter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fjpc.zeebe.zeebeimporter.amqp.dto.ProcessMessage;
import fjpc.zeebe.zeebeimporter.domain.Process;
import fjpc.zeebe.zeebeimporter.respository.ProcessRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Map;

@Service
public class ProcessService {

    private final ObjectMapper mapper;
    private final ProcessRepository repository;
    private final Logger log = LoggerFactory.getLogger(ProcessService.class);

    public ProcessService(ProcessRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<Process> save(String json) throws IOException {
        final var ctx = MDC.getCopyOfContextMap();
        final var message = mapper.readValue(json, ProcessMessage.class);
        if (message == null || message.getProcessesMetadata() == null || message.getProcessesMetadata().length == 0) {
            return Mono.empty();
        }
        var process = new Process();
        process.setId(message.getProcessesMetadata()[0].getProcessDefinitionKey());
        process.setTag(message.getProcessesMetadata()[0].getBpmnProcessId());
        process.setVersionNumber(message.getProcessesMetadata()[0].getVersion());
        assert process.getId() != null;
        return repository.findById(process.getId())
                .flatMap(p -> update(process))
                .switchIfEmpty(repository.save(process))
                .doOnSuccess(p -> handleSuccessStorage(p, ctx))
                .doOnError(e -> handleError(e, ctx));
    }

    private Mono<Process> update(Process process) {
        process.setIsNew(false);
        return repository.save(process);
    }

    private void handleSuccessStorage(Process p, Map<String, String> ctx) {
        try (var mdc = MDC.putCloseable("trace-id", ctx.get("trace-id"))) {
            if (p == null) {
                return;
            }
            log.info("Process with id {} stored successfully", p.getId());
        }
    }

    private void handleError(Throwable e, Map<String, String> ctx) {
        try (var mdc = MDC.putCloseable("trace-id", ctx.get("trace-id"))) {
            log.error("Unable to store process in database, caused by: {}", e.getMessage());
        }
    }

}
