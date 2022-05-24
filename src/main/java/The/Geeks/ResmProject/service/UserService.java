package The.Geeks.ResmProject.service;
import The.Geeks.ResmProject.model.UserModel;

public interface UserService {
    void singUp(UserModel user) throws Exception;
    boolean checkIfUserExist(String email);
    
}
