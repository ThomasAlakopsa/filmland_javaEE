package nl.sogeti.services;

import nl.sogeti.dto.UserDTO;
import nl.sogeti.model.User;
import nl.sogeti.repository.UserRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    @Inject
    private UserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public UserDTO getUser(Long id){

        User user = userRepository.find(id).orElseThrow(() -> new NotFoundException("file not found"));
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> getAllUsers(){
        List<User> userList = userRepository.getAllUsers();
        return userList.stream().map((user) -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
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
