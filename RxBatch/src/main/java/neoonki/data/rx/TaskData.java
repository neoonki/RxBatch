package neoonki.data.rx;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import neoonki.data.vo.TaskVO;

@Repository
public class TaskData {
	private Logger log = Logger.getLogger(this.getClass());
	private List<TaskVO> taskList = new ArrayList();
	
	public boolean add(String taskName) {
		log.info("request of add " + taskName);
		TaskVO taskVO = new TaskVO(taskName);
		return add(taskVO);
	}
	
	private boolean add(TaskVO taskVO) {
		boolean is = true;
		for(TaskVO t : this.taskList) {
			if(t.getTaskName().equals(taskVO.getTaskName())) {
				is = false;
			}
		}
		if(is) {
			log.info("add...");
			taskList.add(taskVO);
		}
		return is;
	}
	
	public int getTaskCount() {
		return taskList.size();
	}
}
