package The.Geeks.ResmProject.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import The.Geeks.ResmProject.Dto.ProprtyDto;
import The.Geeks.ResmProject.Entities.Proprty;
import The.Geeks.ResmProject.Repo.ProprtyRepo;

@Service
public class ProprtyServiecImp implements ProprtyService{

    @Autowired
    ProprtyRepo proprtyRepo;
    
    @Override
    public List<Proprty> fetch_proprty() {

        List <Proprty> result = proprtyRepo.findAll();
        return  result;
    }
    
}
