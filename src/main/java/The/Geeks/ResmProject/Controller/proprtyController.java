package The.Geeks.ResmProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import The.Geeks.ResmProject.Dto.ProprtyDto;
import The.Geeks.ResmProject.Entities.Proprty;
import The.Geeks.ResmProject.Services.ProprtyServiecImp;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api")

public class proprtyController {
 @Autowired
 ProprtyServiecImp service;


   @RequestMapping(value = "/fetch_proprty", method = RequestMethod.GET)
    public List<ProprtyDto> fetch() {
        return service.fetch_proprty();
    }
   
}