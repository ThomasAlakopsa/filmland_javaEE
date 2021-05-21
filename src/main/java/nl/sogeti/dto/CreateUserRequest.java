package nl.sogeti.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class CreateUserRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
