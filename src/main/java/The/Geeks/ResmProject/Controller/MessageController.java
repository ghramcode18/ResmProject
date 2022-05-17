package The.Geeks.ResmProject.Controller;

import org.springframework.stereotype.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import The.Geeks.ResmProject.kafka.KafkaProducer;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {
    
    private KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
    //http:localhost:8080/api/v1/kafka/publish?message=hello word
 //  @PostMapping("/publish")
   // public ResponseEntity<String> publish(@RequestBody User user) {
       // kafkaProducer.sendMessage(user);


       @GetMapping("/publish")
       public ResponseEntity<String> publish(@RequestParam String message) {
         kafkaProducer.sendMessage(message);

        return ResponseEntity.ok("Message sent to kafka topic");
    }
}
