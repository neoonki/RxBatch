package neoonki.task;

import java.io.Serializable;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
@Service
public class SampleTask extends Task implements Serializable {
	private Logger log = Logger.getLogger(this.getClass());
	
	public SampleTask() {
		log.info("init thread");
		setInterval(1000L);
	}
	
	@Override
	@Async("threadPoolTaskExecutor")
    public Future<String> run(String message) throws Exception {
		log.info("run - start");
		Thread.sleep(10000 * 1);
		log.info("run - end");
        return new AsyncResult<String>("Success");
    }
}
