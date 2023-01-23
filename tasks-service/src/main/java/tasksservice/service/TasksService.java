package tasksservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tasksservice.entity.Tasks;

import java.util.List;

public interface TasksService {
    public Page<Tasks> getAll(Pageable pageable);
    public Tasks getById(Long id) throws Exception;
    public Tasks create(Tasks data) throws Exception;
    public Tasks update(Tasks data) throws Exception;
    public void delete(Tasks data);
    public List<Tasks> getAllTasks(String token);
}
