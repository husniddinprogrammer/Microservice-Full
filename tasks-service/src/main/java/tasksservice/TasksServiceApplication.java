package tasksservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tasksservice.entity.Tasks;
import tasksservice.repository.TasksRepository;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableEurekaClient
public class TasksServiceApplication implements ApplicationRunner {
    @Autowired
    TasksRepository tasksRepository;

    public static void main(String[] args) {
        SpringApplication.run(TasksServiceApplication.class,args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        tasksRepository.save(new Tasks(1l,"Task1","Task 1 more",true,1l, LocalDateTime.now()));
        tasksRepository.save(new Tasks(2l,"Task2","Task 2 more",true,2l, LocalDateTime.now()));
        tasksRepository.save(new Tasks(3l,"Task3","Task 3 more",true,3l, LocalDateTime.now()));
        tasksRepository.save(new Tasks(4l,"Task4","Task 4 more",true,2l, LocalDateTime.now()));
        tasksRepository.save(new Tasks(5l,"Task5","Task 5 more",true,3l, LocalDateTime.now()));
        tasksRepository.save(new Tasks(6l,"Task6","Task 6 more",true,1l, LocalDateTime.now()));
        tasksRepository.save(new Tasks(7l,"Task7","Task 7 more",true,2l, LocalDateTime.now()));
        tasksRepository.save(new Tasks(8l,"Task8","Task 8 more",true,1l, LocalDateTime.now()));
        tasksRepository.save(new Tasks(9l,"Task9","Task 9 more",true,2l, LocalDateTime.now()));
    }
}
