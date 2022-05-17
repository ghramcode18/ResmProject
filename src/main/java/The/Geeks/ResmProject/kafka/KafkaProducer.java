package The.Geeks.ResmProject.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;

@Service

public class KafkaProducer {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    // @Autowired
    // private KafkaTemplate<String, User> kafkaTemplate;

    // public void sendMessage(User data) {
    //     LOGGER.info(String.format("Message sent -> %s", data.toString()));

    //     Message<User> message = MessageBuilder
    //             .withPayload(data)
    //             .setHeader(KafkaHeaders.TOPIC, AppConstants.TOPIC_NAME)
    //             .build();

    //     kafkaTemplate.send(message);
    // }
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
    LOGGER.info(String.format("Message sent -> %s", message.toString()));
    kafkaTemplate.send("javaguides",message);

}}
