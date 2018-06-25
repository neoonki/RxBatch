package neoonki.task;

import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class SampleTask {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Async("threadPoolTaskExecutor")
    public Future<String> run(String message) throws Exception {
		log.info("run - start");
		Thread.sleep(1000 * 10);
		log.info("run - end");
        return new AsyncResult<String>("Success");
    }
}
