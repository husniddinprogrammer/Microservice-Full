package userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import userservice.entity.Roles;
import userservice.entity.User;
import userservice.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableEurekaClient
public class UsersServiceApplication implements ApplicationRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(UsersServiceApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userRepository.findAll(PageRequest.of(0,10)).getTotalElements() == 0) {
            Set<Roles> rolesSet = new HashSet<>();
            rolesSet.add(Roles.ADMIN);
            User user1 = new User();
            user1.setName("Abbos");
            user1.setLogin("hello1234");
            user1.setPassword(passwordEncoder.encode("hello1234"));
            user1.setStatus(true);
            user1.setRoles(rolesSet);
            user1 = userRepository.save(user1);

            User user2 = new User();
            user2.setName("Jasur");
            user2.setLogin("jasur1234");
            user2.setPassword(passwordEncoder.encode("jasur1234"));
            user2.setStatus(true);
            user2.setRoles(rolesSet);
            user2.setResponsibleUser(user1);
            userRepository.save(user2);

            User user3 = new User();
            user3.setName("Husniddin");
            user3.setLogin("husniddin");
            user3.setPassword(passwordEncoder.encode("husniddin"));
            user3.setStatus(true);
            user3.setRoles(rolesSet);
            user3.setResponsibleUser(user1);
            userRepository.save(user3);
        }
    }
}
