package tasksservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import tasksservice.entity.Tasks;
import tasksservice.service.TasksService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/tasks")
public class TasksController {
    @Autowired
    TasksService tasksService;

    @GetMapping
    public List<Tasks> getAllTasks(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken) throws Exception{
        String token = bearerToken.substring(7);
        return tasksService.getAllTasks(token);
    }
}

