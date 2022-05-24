package The.Geeks.ResmProject.service;

<<<<<<< Updated upstream
import The.Geeks.ResmProject.model.UserModel;
=======
import The.Geeks.ResmProject.domain.User;
>>>>>>> Stashed changes

public interface UserService {
    void singUp(UserModel user) throws Exception;
    boolean checkIfUserExist(String email);
    
}
