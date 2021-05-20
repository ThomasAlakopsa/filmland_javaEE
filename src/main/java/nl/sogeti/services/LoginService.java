package nl.sogeti.services;

import nl.sogeti.model.User;
import nl.sogeti.repository.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

public class LoginService {

    @Inject
    private UserRepository userRepository;

    public Boolean checkCredentials(User user){

        String email = user.getEmail();
        String password = user.getPassword();

        return password.equals(userRepository.findUserWithEmail(email)
                .orElseThrow(() -> new NotFoundException("user not found"))
                .getPassword());
    }

}
