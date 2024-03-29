package fjpc.zeebe.zeebeimporter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fjpc.zeebe.zeebeimporter.domain.Process;
import fjpc.zeebe.zeebeimporter.mq.dto.ProcessMessage;
import fjpc.zeebe.zeebeimporter.respository.ProcessRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProcessService {

    private final ObjectMapper mapper;
    private final ProcessRepository repository;

    public ProcessService(ProcessRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Process save(String json) throws IOException {
        final ProcessMessage message = mapper.readValue(json, ProcessMessage.class);
        if (message == null || message.getProcessesMetadata() == null || message.getProcessesMetadata().length == 0) {
            return null;
        }
        Process process = new Process();
        process.setId(message.getProcessesMetadata()[0].getProcessDefinitionKey());
        process.setTag(message.getProcessesMetadata()[0].getBpmnProcessId());
        process.setVersionNumber(message.getProcessesMetadata()[0].getVersion());
        return repository.save(process);
    }

}
