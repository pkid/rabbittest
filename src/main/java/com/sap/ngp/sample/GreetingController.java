package com.sap.ngp.sample;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.ngp.sample.model.TestScope;
import com.sap.ngp.sample.model.TestScopeRepository;


import io.prometheus.client.Counter;

@RestController
public class GreetingController {
    @Autowired 
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TestScopeRepository testScopeRepository;


    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    static final Counter requests = Counter.build()
            .name("greeting_requests_total").help("Total requests.").register();

    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);


    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        requests.inc();
        logger.info("Greetings will be sent out! Hi!");

        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }


    @RequestMapping("/writedb")
    public void writedb() {
        testScopeRepository.save(new TestScope(UUID.randomUUID(), true));
    }

    @RequestMapping("/getdb")
    public String getdb() {
        Iterable<TestScope> listdata = testScopeRepository.findAll();
        String result = new String();

        for(TestScope item: listdata){
            result += " | " + item.getuUID().toString();
        }

        return result;
    }

    @RequestMapping("/sendrabbit")
    public void sendrabbit() {
        String message = (new Date()) + ":Test Message:" + UUID.randomUUID();
        rabbitTemplate.convertAndSend("testqueue", message);
    }
}
