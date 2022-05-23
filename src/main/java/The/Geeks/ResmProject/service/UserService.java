package The.Geeks.ResmProject.service;

import org.springframework.stereotype.Service;

import The.Geeks.ResmProject.domain.User;

@Service
public interface UserService {
    void singUp(User user) throws Exception;
    boolean checkIfUserExist(String email);
    
}
