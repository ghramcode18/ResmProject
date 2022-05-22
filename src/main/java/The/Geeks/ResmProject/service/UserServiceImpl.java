package collage.project1.resm.service;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import collage.project1.resm.domain.User;
import collage.project1.resm.repo.UserRepo;
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
