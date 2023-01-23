package userservice.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import userservice.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByLogin(String username);
  public Page<User> findAllByIdGreaterThanOrderByIdDesc(Long id, Pageable pageable);
  public List<User> findAllByResponsibleUserId(Long responsibleUserId);
}