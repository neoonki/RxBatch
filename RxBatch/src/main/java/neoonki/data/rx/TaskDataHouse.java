package neoonki.data.rx;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import neoonki.data.vo.TaskVO;

@Repository
public class TaskDataHouse {
	private Logger log = Logger.getLogger(this.getClass());
	private List<TaskVO> taskList = new ArrayList<TaskVO>();
	private List<String> taskNameList = new ArrayList<String>();
	
	public boolean add(Object obj, String className) {
		TaskVO taskVO = new TaskVO(obj, className);
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
			log.info("request of add task : " + taskVO.getTaskName());
			taskList.add(taskVO);
			taskNameList.add(taskVO.getTaskName());
		}
		return is;
	}
	
	public boolean isRunable(String taskName) {
		TaskVO task = (TaskVO) getTaskVO(taskName);
		if(task == null) {
			return false;
		}else {
			return task.isRuntime();
		}
	}
	
	public List<Object> getRunableTaskList() {
		List<Object> taskList = new ArrayList<Object> ();
		
		for(TaskVO task : this.taskList) {
			if(task.isRuntime()) {
				taskList.add(task.getTask());
			}
		}
		
		return taskList;
	}
	
	public int getTaskTotalCount() {
		return taskNameList.size();
	}
	
	public TaskVO getTaskVO(String taskClassName) {
		log.info("taskName = " + taskClassName);
		TaskVO obj = null;
		for(TaskVO task : taskList) {
			log.info(task.getTaskName());
			log.info(taskClassName);
			if(task.getTaskName().equals(taskClassName)) {
				obj = task;
			}
		}
		return obj;
	}
}
