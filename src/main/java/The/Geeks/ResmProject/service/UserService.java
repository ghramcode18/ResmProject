package The.Geeks.ResmProject.service;

import org.springframework.stereotype.Service;

import The.Geeks.ResmProject.model.UserModel;

@Service
public interface UserService {
    void singUp(UserModel userModel) throws Exception;
    
}
