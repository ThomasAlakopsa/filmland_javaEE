package nl.sogeti.services;

import nl.sogeti.model.User;
import nl.sogeti.repository.UserRepository;

import javax.inject.Inject;

public class UserService {

    @Inject
    private UserRepository userRepository;

    public User getUser(Long id){
        return userRepository.find(id);
    }

    public User createUser(User user){
        return userRepository.create(user);
    }

    public void deleteUser(Long id){
        userRepository.delete(id);
    }

}
