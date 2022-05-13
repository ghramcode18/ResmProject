package The.Geeks.ResmProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import The.Geeks.ResmProject.Dto.Msg_json;

@Controller
public class GreetingController {
   
    @MessageMapping("/hello/{username}")
    @SendTo("/topic/greetings/{username}")
    public HttpEntity greeting( @RequestBody  Msg_json msg_json,@DestinationVariable String username) throws Exception {
        System.out.println(username);
        Thread.sleep(1000); // simulated delay
        return ResponseEntity.ok("valid");
    }
}
