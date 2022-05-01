package The.Geeks.ResmProject.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import The.Geeks.ResmProject.Dto.ProprtyDto;
import The.Geeks.ResmProject.Entities.Proprty;
import The.Geeks.ResmProject.Repo.ProprtyRepo;
import The.Geeks.ResmProject.mapstruct.MapStructMapper;

@Service
public class ProprtyServiecImp implements ProprtyService {

    @Autowired
    ProprtyRepo proprtyRepo;

    @Autowired
    MapStructMapper mapstruct;

    @Override
    public List<ProprtyDto> fetch_proprty() {

        List<Proprty> result = proprtyRepo.findAll();

        return ListProprtyToListProprtyDto(result);
    }

    private List<ProprtyDto> ListProprtyToListProprtyDto(List<Proprty> result) {

        List<ProprtyDto> list_proprties = new ArrayList<>();
        if (result.size() > 0) {
            for (Proprty proprty : result) {

                ProprtyDto proprtyDto =new ProprtyDto().withId(proprty.getId())
                .withProprty_name(proprty.getProprty_name())
                .withPrice(proprty.getPrice())
                .withSpace(proprty.getSpace())
                .withAvailable(proprty.isAvailable())
                .withNum_rooms(proprty.getNum_rooms())
                .withNum_bathrooms(proprty.getNum_bathrooms())
                .withDescription(proprty.getDescription())
                .withAvailable(proprty.isAvailable())
                .withCladding_type(proprty.getCladding_type())
                .withDate_in(proprty.getDate_in());

               // .withProprty_type(proprty.getProprty_type());
               // .withImage_url(proprty.getImages())
                //.withCategory(proprty.getCategories());
                //.withUser(proprty.getUser());

                list_proprties.add(proprtyDto);

            }
          return list_proprties;
        } 
        else
            return new ArrayList<ProprtyDto>();

    }

}
