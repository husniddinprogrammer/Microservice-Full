package tasksservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tasksservice.entity.Tasks;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Tasks,Long> {
    public List<Tasks> findAllByUserId(Long userId);
}
