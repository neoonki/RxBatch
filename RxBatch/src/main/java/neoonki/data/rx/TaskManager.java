package neoonki.data.rx;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import neoonki.task.Task;

@Component
public class TaskManager {
	private Logger log = Logger.getLogger(this.getClass());
	private final static int INTERVAL = 1000 * 1;
	private boolean isRunable = false;
	private ApplicationContext ctx = null;
	
	@Autowired
    private TaskDataHouse taskDataHouse;
	
	public void start(ApplicationContext ctx) {
		if(isRunable == false) {
			isRunable = true;
			this.ctx = ctx;
			starter();
		}
	}
	
	@Async("threadPoolTaskExecutor")
	private void starter() {
		log.info("start");
		try {
			while(isRunable) {
				List<Object> taskList = taskDataHouse.getRunableTaskList();
				if(taskList.size() > 0) {
					for(Object obj : taskList) {
						try {
							Task task = (Task) this.ctx.getBean((Class) obj);
							task.run("");
						} catch(Exception e) {
							e.printStackTrace();
						}
					}
				}else {
					//stop();
				}
				
				Thread.sleep(INTERVAL);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("end");
	}
	
	public void stop () {
		this.isRunable = false;
	}
}
