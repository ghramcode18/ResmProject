package The.Geeks.ResmProject.service;

import org.apache.catalina.mbeans.UserMBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
import The.Geeks.ResmProject.repo.RoleRepo;
import The.Geeks.ResmProject.repo.UserRepo;
import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor @Component
public class UserServiceImpl implements UserService 
{
    private final UserRepo userRepo;
    private final AddressRepo addressRepo;
    private final RegionRepo regionRepo;
    private final CityRepo cityRepo;
    private final CountryRepo countryRepo;
    private final RoleRepo roleRepo;


    @Override
    public void singUp(UserModel userModel) throws Exception {
        User user = new User();
        Address address = new Address();
        Region region = new Region();
        City city = new City();
        Country country = new Country();
        Role role = roleRepo.findByroleId(Long.valueOf(1));

        user.setUsername(userModel.getUsername());
        user.setHashedPassword(userModel.getHashedPassword());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setRole(role);
        address.setLattitude(userModel.getLattitude());
        address.setLongitutde(userModel.getLongitutde());
        user.setAddress(address);
        region.setName(userModel.getRegionname());
        user.getAddress().setRegion(region);
        city.setName(userModel.getCityname());
        user.getAddress().getRegion().setCity(city);
        country.setName(userModel.getCountryname());
        user.getAddress().getRegion().getCity().setCountry(country);

        countryRepo.save(country);
        cityRepo.save(city);
        regionRepo.save(region);
        addressRepo.save(address);
        userRepo.save(user);
    }
    
}