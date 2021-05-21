package nl.sogeti.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginRequestDTO {

    @NotNull @Size(min=5,max=20)
    private String email;

    @NotNull(message = "password is needed")
    @Size(min=5,max=20)
    private String password;

}
