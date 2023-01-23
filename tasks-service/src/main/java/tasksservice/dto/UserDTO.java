package tasksservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}

