package userservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import userservice.entity.Roles;
import userservice.entity.User;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String firstName;
    private Boolean status;
    private String login;
    protected Set<Roles> roles;
    private Long responsibleUserId;
    private String responsibleUserLogin;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.firstName = user.getFirstName();
        this.status = user.getStatus();
        this.login = user.getLogin();
        this.roles = user.getRoles();
        if (user.getResponsibleUser() != null) {
            this.responsibleUserId = user.getResponsibleUser().getId();
            this.responsibleUserLogin = user.getResponsibleUser().getLogin();
        }
    }

}

