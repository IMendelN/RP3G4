package store.DTO;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import groovy.transform.ToString;
import lombok.Data;
import store.models.Genre;
import store.models.Platform;
import store.models.User;
import store.models.enums.UserRole;

@Data
@ToString
public class UserDTO {
    @NotBlank @NotNull private String name;
    @NotBlank @NotNull private String password;
    @NotBlank @NotNull private String email; 
    @NotBlank @NotNull private String date;
    @NotBlank @NotNull private String confirmPassword;
    @NotNull private UserRole role;

    private LocalDate birthDate;
    private String address;
    private Set<Platform> platforms;
    private Set<Genre> genres;

    public User toUser() {
        return new User(name, password, address, email, role, birthDate, platforms, genres);
    }
}
