package The.Geeks.ResmProject.service;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import The.Geeks.ResmProject.domain.User;
import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor @Component
public class UserServiceImpl implements UserService {
    @Override
    public void singUp(User user) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean checkIfUserExist(String email) {
        // TODO Auto-generated method stub
        return false;
    }
  
    
}
