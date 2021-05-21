package nl.sogeti.services;

import nl.sogeti.dto.CreateUserRequest;
import nl.sogeti.dto.LoginRequestDTO;
import nl.sogeti.dto.UserDTO;
import nl.sogeti.model.User;
import nl.sogeti.repository.UserRepository;
import nl.sogeti.util.CustomMapper;
import nl.sogeti.util.Hasher;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    @Inject
    private UserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper();
    private final CustomMapper customMapper = new CustomMapper();

    public UserDTO getUser(Long id){

        User user = userRepository.find(id).orElseThrow(() -> new NotFoundException("file not found"));
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> getAllUsers(){
        List<User> userList = userRepository.getAllUsers();
        return customMapper.mapUserListToDTO(userList);
    }

    public void createUser(CreateUserRequest user){
        if(userRepository.findUserWithEmail(user.getEmail()).isPresent()) {
            throw new InternalServerErrorException();
        }
        User mappedUser = modelMapper.map(user, User.class);
        //mappedUser.setPassword(Hasher.hashPassword(user.getPassword()));
        userRepository.create(mappedUser);
    }

    public void deleteUser(Long id){
        userRepository.delete(id);
    }
}
