package fjpc.zeebe.zeebeimporter.amqp;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
public class MessageInterceptor {

    @Before("execution(* fjpc.zeebe.zeebeimporter.amqp.MessageListener.receive(..))")
    public void beforeReceiveMessage() {
        MDC.put("traceId", UUID.randomUUID().toString());
    }

    @After("execution(* fjpc.zeebe.zeebeimporter.amqp.MessageListener.receive(..))")
    public void afterReceiveMessage() {
        MDC.clear();
    }

}
