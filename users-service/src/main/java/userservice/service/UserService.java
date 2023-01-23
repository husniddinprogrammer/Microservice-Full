package userservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import userservice.service.dto.UserDTO;
import userservice.service.vm.UserVM;

import java.util.List;

public interface UserService {
    public UserDTO update(UserVM data) throws Exception;
    public void deleteById(Long id);
    UserDTO getCurrentUser();
    public UserDTO register(UserVM data) throws Exception;
    public List<UserDTO> getAllByResponsibleUser(UserDTO userDTO) throws Exception;
}
















