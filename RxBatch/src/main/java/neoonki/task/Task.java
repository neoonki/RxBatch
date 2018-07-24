package neoonki.task;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public abstract class Task {
	private Long interval;

	public Long getInterval() {
		if(interval == null) {
			interval = 1000L;
		}
		return interval;
	}

	public void setInterval(Long interval) {
		this.interval = interval;
	}
	
	@Async("threadPoolTaskExecutor")
	public abstract Future<String> run(String message) throws Exception;
}
