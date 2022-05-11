package The.Geeks.ResmProject.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import The.Geeks.ResmProject.Dto.ProprtyDto;
import The.Geeks.ResmProject.Dto.Proprty_typeDto;
import The.Geeks.ResmProject.Entities.Proprty;
import The.Geeks.ResmProject.Repo.ProprtyRepo;
// import The.Geeks.ResmProject.mapstruct.MapStructMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class ProprtyServiecImp implements ProprtyService {

    @Autowired
    ProprtyRepo proprtyRepo;

    // @Autowired
    // MapStructMapper mapstruct;

    @Override
    public List<ProprtyDto> fetch_proprty() {

        List<Proprty> result = proprtyRepo.findAll();

        return ListProprtyToListProprtyDto(result);
    }

    private List<ProprtyDto> ListProprtyToListProprtyDto(List<Proprty> result) {

        List<ProprtyDto> list_proprties = new ArrayList<>();
        if (result.size() > 0) {
            for (Proprty proprty : result) {

                ProprtyDto proprtyDto =new ProprtyDto().id(proprty.getId())
                .proprty_name(proprty.getProprty_name())
                .price(proprty.getPrice())
                .space(proprty.getSpace())
                .available(proprty.isAvailable())
                .num_rooms(proprty.getNum_rooms())
                .num_bathrooms(proprty.getNum_bathrooms())
                .description(proprty.getDescription())
                .available(proprty.isAvailable())
                .cladding_type(proprty.getCladding_type())
                .date_in(proprty.getDate_in());
               //.proprty_type(Proprty_typeDto.valueOf(proprty.getProprty_type() != null ));
               //.proprty_type(Proprty_typeDto.valueOf(proprty.getProprty_type() != null ? proprty.getProprty_type().get(0) : "sell"));
               //.withProprty_type(proprty.getProprty_type());
               //.withImage_url(proprty.getImages())
              //.withCategory(proprty.getCategories());
              //.withUser(proprty.getUser());
                list_proprties.add(proprtyDto);

            }
          return list_proprties;
        } 
        else
            return new ArrayList<ProprtyDto>();

        
    }
    
    public List<ProprtyDto> fetchAll_proprty(int pageNo, int pageSize) {

        return proprtyRepo.findAll(
                PageRequest.of(pageNo, pageSize, Sort.by("proprty_name"))).toList().stream().map(
                        (e) -> {
                            return ProprtyToProprtyDto(e);
                        })
                .toList();

    }

    private ProprtyDto ProprtyToProprtyDto(Proprty proprty) {


        ProprtyDto proprtyDto = new ProprtyDto().id(proprty.getId())
                .proprty_name(proprty.getProprty_name())
                .price(proprty.getPrice())
                .space(proprty.getSpace())
                .available(proprty.isAvailable())
                .num_rooms(proprty.getNum_rooms())
                .num_bathrooms(proprty.getNum_bathrooms())
                .description(proprty.getDescription())
                .available(proprty.isAvailable())
                .cladding_type(proprty.getCladding_type())
                .date_in(proprty.getDate_in());
        return proprtyDto;
    }

}
