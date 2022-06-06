package The.Geeks.ResmProject.service;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import The.Geeks.ResmProject.domain.Address;
import The.Geeks.ResmProject.domain.City;
import The.Geeks.ResmProject.domain.Country;
import The.Geeks.ResmProject.domain.Property;
import The.Geeks.ResmProject.domain.Region;
import The.Geeks.ResmProject.domain.Role;
import The.Geeks.ResmProject.domain.User;
import The.Geeks.ResmProject.message.ResponseMessage;
import The.Geeks.ResmProject.model.UserModel;
import The.Geeks.ResmProject.payload.request.AddPropertyToFavoriteListRequest;
import The.Geeks.ResmProject.payload.request.PropertyRequest;
import The.Geeks.ResmProject.repo.AddressRepo;
import The.Geeks.ResmProject.repo.CityRepo;
import The.Geeks.ResmProject.repo.CountryRepo;
import The.Geeks.ResmProject.repo.PropertyRepo;
import The.Geeks.ResmProject.repo.RegionRepo;
import The.Geeks.ResmProject.repo.UserRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Component
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final AddressRepo addressRepo;
    private final RegionRepo regionRepo;
    private final CityRepo cityRepo;
    private final CountryRepo countryRepo;
    @Autowired
    UserRepo userRepository;

    @Autowired
    PropertyRepo propertyRepo;

    @Override
    public void singUp(User user) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean checkIfUserExist(String email) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ResponseEntity<ResponseMessage> addPropertyToFavoriteList(
            AddPropertyToFavoriteListRequest addPropertyToFavoriteListRequest, Integer integer) {

        Message message = new Message();
        try {

            // here decode token and checkIfUserExist in db
            DecodeToken dtoken = decodeToken(addPropertyToFavoriteListRequest.getToken());

            User user = userRepository.findByUsername(
                    dtoken.getSub())
                    .orElseThrow(() -> new RuntimeException("Error: user is not found."));

            // here check if property exist in db        
            String PropertyId = addPropertyToFavoriteListRequest.getPropertyId();
            Integer PropertyIdInt = Integer.parseInt(PropertyId);

            Optional<Property> newProperty = propertyRepo.findById((long)PropertyIdInt);
            // if (newProperty.isPresent()) {
            //     Property property = newProperty.get();
            //     user.getFavoriteProperties().add(property);
            //     userRepository.save(user);
            //     message.setMessage("Property added to favorite list successfully");
            //     message.setStatus(200);
            //     return ResponseEntity.status(HttpStatus.OK).body(message);
            // } else {
            //     message.setMessage("Property not found");
            //     message.setStatus(404);
            //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            // }

            ResponseMessage responseMessage = new ResponseMessage("successful");

            return ResponseEntity.ok(responseMessage);

        } catch (Exception e) {
            ResponseMessage responseMessage = new ResponseMessage("error");

            return ResponseEntity.ok(responseMessage);

        }
    }

    // method to decodeToken
    private DecodeToken decodeToken(String token) throws UnsupportedEncodingException {

        DecodeToken dtoken = DecodeToken.getDecoded(token);

        System.out.println(dtoken);

        return dtoken;
    }

}