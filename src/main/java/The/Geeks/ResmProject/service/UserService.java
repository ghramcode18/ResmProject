package collage.project1.resm.service;

import collage.project1.resm.domain.User;

public interface UserService {
    void singUp(User user) throws Exception;
    boolean checkIfUserExist(String email);
    
}
