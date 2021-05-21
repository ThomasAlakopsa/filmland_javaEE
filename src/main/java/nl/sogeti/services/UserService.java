package nl.sogeti.services;

import nl.sogeti.model.User;
import nl.sogeti.repository.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import java.util.Optional;

public class UserService {

    @Inject
    private UserRepository userRepository;

    public User getUser(Long id){
        return userRepository.find(id).orElseThrow(() -> new NotFoundException("file not found"));
    }

    public void createUser(User user){
        if(userRepository.findUserWithEmail(user.getEmail()).isPresent()) {
            throw new InternalServerErrorException();
        }
        userRepository.create(user);
    }

    public void deleteUser(Long id){
        userRepository.delete(id);
    }

}
