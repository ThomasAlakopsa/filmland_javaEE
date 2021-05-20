package nl.sogeti.services;

import nl.sogeti.model.User;
import nl.sogeti.repository.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

public class UserService {

    @Inject
    private UserRepository userRepository;

    public User getUser(Long id){
        return userRepository.find(id).orElseThrow(() -> new NotFoundException("file not found"));
    }

    public User createUser(User user){
        return userRepository.create(user);
    }

    public void deleteUser(Long id){
        userRepository.delete(id);
    }

    public boolean checkForDuplicateEmail(User user){
        return userRepository.findUserWithEmail(user.getEmail()).isPresent();
    }

}
