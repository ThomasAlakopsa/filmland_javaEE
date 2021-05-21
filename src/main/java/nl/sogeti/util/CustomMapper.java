package nl.sogeti.util;

import nl.sogeti.dto.UserDTO;
import nl.sogeti.model.User;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CustomMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public List<UserDTO> mapUserListToDTO(List<User> userList){
        return userList.stream().map((user) -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }
}