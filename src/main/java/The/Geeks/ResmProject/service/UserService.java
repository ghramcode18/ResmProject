package The.Geeks.ResmProject.service;
import The.Geeks.ResmProject.domain.User;
import The.Geeks.ResmProject.model.UserModel;

public interface UserService {
    void singUp(User user) throws Exception;
    boolean checkIfUserExist(String email);
    
}
