package paul.leavitt.todo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
@EnableJpaAuditing
public class TodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @Bean
    ApplicationRunner init(TodoRepository repository) {
        return args -> {
            Stream.of("Feed cat", "Wash dishes", "Take out bins", "Get a job")
                    .forEach(job -> {
                        Todo todo = new Todo(job);
                        todo.setIsCompleted(false);
                        todo.setCreatedAt(new Date());
                        repository.save(todo);

                    });
            repository.findAll().forEach(System.out::println);
        };
    }
}
