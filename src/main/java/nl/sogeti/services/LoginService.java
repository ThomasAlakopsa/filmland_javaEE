package nl.sogeti.services;

import nl.sogeti.model.User;
import nl.sogeti.repository.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

public class LoginService {

    @Inject
    private UserRepository userRepository;

    public Boolean checkCredentials(User user){

        User possibleUser = userRepository.findUserWithEmail(user.getEmail())
                .orElseThrow(() -> new NotFoundException("user not found"));

        return user.getPassword().equals(possibleUser.getPassword());
    }

}
