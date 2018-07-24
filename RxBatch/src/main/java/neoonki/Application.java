package neoonki;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import neoonki.data.rx.TaskDataHouse;
import neoonki.data.rx.TaskManager;
import neoonki.task.SampleTask;
import neoonki.task.btc.GetBTCInfoTask;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
@EnableAsync
public class Application {
	Logger log = Logger.getLogger(this.getClass());
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
    private TaskManager taskManager;
	
	@Autowired
    private TaskDataHouse taskDataHouse;

	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            log.info("Let's inspect the beans provided by Spring Boot:");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
            	log.info(beanName);
            }
            taskDataHouse.add(GetBTCInfoTask.class, GetBTCInfoTask.class.getName());
//            taskDataHouse.add(SampleTask.class);
            
            taskManager.start(ctx);
        };
    }
}
