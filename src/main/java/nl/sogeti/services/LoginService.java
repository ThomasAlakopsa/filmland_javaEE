package nl.sogeti.services;

import nl.sogeti.model.User;
import nl.sogeti.repository.UserRepository;

import javax.inject.Inject;

public class LoginService {

    @Inject
    private UserRepository userRepository;

    public Boolean checkCredentials(User user){

        String email = user.getEmail();
        String password = user.getPassword();

        return password.equals(userRepository.findByEmail(email).getPassword());
    }

}
