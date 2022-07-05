package store.DTO;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import store.models.User;
import store.models.enums.UserRole;

@Data
public class UserDTO {
    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
	private String password;

    @NotBlank
    @NotNull
	private String email; 
    private LocalDate birthDate;

    private String address;

    public User toUser() {
        return new User(name, password, address, email, UserRole.USER, birthDate);
    }
}
