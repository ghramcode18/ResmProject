package The.Geeks.ResmProject.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import The.Geeks.ResmProject.Dto.ProprtyDto;
import The.Geeks.ResmProject.Entities.Proprty;

@Service
public interface ProprtyService {
   
    public List<ProprtyDto> fetch_proprty ();



}
