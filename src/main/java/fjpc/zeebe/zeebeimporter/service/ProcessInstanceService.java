package fjpc.zeebe.zeebeimporter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fjpc.zeebe.zeebeimporter.domain.ProcessInstance;
import fjpc.zeebe.zeebeimporter.mq.dto.ProcessInstanceMessage;
import fjpc.zeebe.zeebeimporter.respository.ProcessInstanceRepository;
import org.springframework.stereotype.Service;

@Service
public class ProcessInstanceService {

    private final ObjectMapper mapper;
    private final ProcessInstanceRepository repository;

    public ProcessInstanceService(ObjectMapper mapper, ProcessInstanceRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public ProcessInstance save(String json) throws JsonProcessingException {
        final ProcessInstanceMessage message = mapper.readValue(json, ProcessInstanceMessage.class);
        if (message == null || message.getProcessInstanceKey() <= 0L || message.getProcessDefinitionKey() <= 0L) {
            return null;
        }
        ProcessInstance instance = new ProcessInstance();
        instance.setId(message.getProcessInstanceKey());
        instance.setProcessId(message.getProcessDefinitionKey());
        return repository.save(instance);
    }

}
