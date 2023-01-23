package tasksservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import tasksservice.dto.UserDTO;
import tasksservice.entity.Tasks;
import tasksservice.repository.TasksRepository;
import tasksservice.service.TasksService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TasksServiceImpl implements TasksService {
    @Autowired
    TasksRepository tasksRepository;
    @Autowired
    WebClient.Builder webClient;

    @Override
    public Page<Tasks> getAll(Pageable pageable) {
        return tasksRepository.findAll(pageable);
    }

    @Override
    public Tasks getById(Long id) throws Exception {
        return tasksRepository.getById(id);
    }

    @Override
    public Tasks create(Tasks data) throws Exception {
        return tasksRepository.save(data);
    }

    @Override
    public Tasks update(Tasks data) throws Exception {
        return tasksRepository.save(data);
    }

    @Override
    public void delete(Tasks data) {
        tasksRepository.delete(data);
    }

    @Override
    public List<Tasks> getAllTasks(String token) {
        UserDTO[] userDTOList = webClient.build().get()
                .uri("http://tasks-service/api/users/list")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(UserDTO[].class)
                .block();

        List<Tasks> tasksList = new ArrayList<>();
        for (int i = 0; i < userDTOList.length; i++) {
            tasksList.addAll(tasksRepository.findAllByUserId(userDTOList[i].getId()));
        }
        return tasksList;
    }
}
