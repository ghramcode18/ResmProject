package The.Geeks.ResmProject.service;
<<<<<<< Updated upstream
=======

import java.util.regex.Pattern;
>>>>>>> Stashed changes

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

<<<<<<< Updated upstream
import The.Geeks.ResmProject.domain.Address;
import The.Geeks.ResmProject.domain.City;
import The.Geeks.ResmProject.domain.Country;
import The.Geeks.ResmProject.domain.Region;
import The.Geeks.ResmProject.domain.Role;
import The.Geeks.ResmProject.domain.User;
import The.Geeks.ResmProject.model.UserModel;
import The.Geeks.ResmProject.repo.AddressRepo;
import The.Geeks.ResmProject.repo.CityRepo;
import The.Geeks.ResmProject.repo.CountryRepo;
import The.Geeks.ResmProject.repo.RegionRepo;
=======
import The.Geeks.ResmProject.domain.User;
>>>>>>> Stashed changes
import The.Geeks.ResmProject.repo.UserRepo;
import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor @Component
public class UserServiceImpl implements UserService {
<<<<<<< Updated upstream

    private final UserRepo userRepo;
    private final AddressRepo addressRepo;
    private final RegionRepo regionRepo;
    private final CityRepo cityRepo;
    private final CountryRepo countryRepo;

    @Override
    public void singUp(UserModel user) throws Exception
    {
=======
    private final UserRepo userRepo;
    @Override
    public void singUp(User user) throws Exception {
        if(checkIfUserExist(user.getUsername()))
        {
            throw new Exception("User already exists for this email ");
        }
        else if(! isValid(user.getUsername()))
        {
            throw new Exception("Sorry, this email is not valid");
        }
        else
        {
            User Euser = new User();
            Euser.setUsername(user.getUsername());
            Euser.setHashedPassword(user.getHashedPassword());
            Euser.setFirstName(user.getFirstName());
            Euser.setLastName(user.getLastName());
            Euser.getAddress().setLattitude(user.getAddress().getLattitude());
            Euser.getAddress().setLongitutde(user.getAddress().getLongitutde());
            Euser.getAddress().getRegion().setName(user.getAddress().getRegion().getName());
            Euser.getAddress().getRegion().getCity().setName(user.getAddress().getRegion().getCity().getName());
            Euser.getAddress().getRegion().getCity().getCountry().setName(user.getAddress().getRegion().getCity().getCountry().getName());
            
        }

    }
>>>>>>> Stashed changes

        if(checkIfUserExist(user.getUsername()))
        {
            throw new Exception("the username already exist..");
        }
        else
        {
            User Euser = new User();
            Address address = new Address();
            Region region = new Region();
            City city = new City();
            Country country = new Country();
            Role role = new Role();
            Euser.setUsername(user.getUsername());
            Euser.setHashedPassword(user.getHashedPassword());
            Euser.setFirstName(user.getFirstName());
            Euser.setLastName(user.getLastName());
            address.setLattitude(user.getLattitude());
            address.setLongitutde(user.getLongitutde());
            region = regionRepo.findByname(user.getRegionname());
            city = cityRepo.findByname(user.getCityname());
            country = countryRepo.findByname(user.getCountryname());
            role.setRoleId(1);
            Euser.setAddress(address);
            Euser.getAddress().setRegion(region);
            Euser.getAddress().getRegion().setCity(city);
            Euser.getAddress().getRegion().getCity().setCountry(country);
            Euser.setRole(role);
            userRepo.save(Euser);
            addressRepo.save(address);
        }
    }
    @Override
    public boolean checkIfUserExist(String username) {
        return userRepo.findByUsername(username) !=null ? true : false;
<<<<<<< Updated upstream
    }
}
=======
    }
    public static boolean isValid(String username)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (username == null)
            return false;
        return pat.matcher(username).matches();
    }

    
}
>>>>>>> Stashed changes
