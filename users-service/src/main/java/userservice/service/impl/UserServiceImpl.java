package userservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import userservice.entity.Roles;
import userservice.entity.User;
import userservice.repository.UserRepository;
import userservice.service.UserService;
import userservice.service.dto.UserDTO;
import userservice.service.vm.UserVM;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDTO update(UserVM data) throws Exception {
        if (data.getId() != null){
            UserDTO currentUser = getCurrentUser();
            User user = userRepository.findByLogin(currentUser.getLogin()).get();
            if(passwordEncoder.matches(data.getOldPassword(), user.getPassword())){
                user.setName(data.getName());
                user.setFirstName(data.getFirstName());
                user.setPassword(passwordEncoder.encode(data.getPassword()));
                return new UserDTO(userRepository.save(user));
            }
            else{
                return null;
            }
        }else{
            throw new Exception("id not found");
        }
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO getCurrentUser(){
        String username = getPrincipal();
        if (username != null)
            return userRepository.findByLogin(username).map(UserDTO::new).orElse(null);
        return null;
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
    @Override
    public UserDTO register(UserVM data) throws Exception{
        User user = new User();
        user.setName(data.getName());
        user.setFirstName(data.getFirstName());
        user.setRoles(data.getRoles());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setLogin(data.getLogin());
        user.setStatus(false);
        return new UserDTO(userRepository.save(user));
    }

    @Override
    public List<UserDTO> getAllByResponsibleUser(UserDTO userDTO) throws Exception {
        return userRepository.findAllByResponsibleUserId(userDTO.getId()).stream().map(UserDTO::new).collect(Collectors.toList());
    }
}












