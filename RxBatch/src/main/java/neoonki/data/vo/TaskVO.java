package neoonki.data.vo;

import javax.management.RuntimeErrorException;

import neoonki.task.Task;
import neoonki.util.DateUtil;

public class TaskVO {
	
	private boolean isRun;
	private Long lastRuntimeEnd;
	private Long lastRuntimeStart;
	private int lastRunErrorCount;
	private int lastRunSuccessCount;
	private Long interval;
	private Object taskClass;
	private String className;
	
	public TaskVO(Object obj, String className) {
		this.taskClass = obj;
		isRun = false;
		lastRuntimeEnd = null;
		lastRuntimeStart = null;
		lastRunErrorCount = 0;
		lastRunSuccessCount = 0;
		interval = 5000L;//((Task) obj).getInterval();
		this.className = className;
	}
	
	public Object getTask() {
		return taskClass;
	}
	
	public boolean isRuntime() {
		boolean isRuntime = false;
		if(!isRun) {
			if(lastRuntimeStart == null) {
				System.out.println(taskClass + " is naver run.");				
				isRuntime = true;
			}else if(lastRuntimeEnd != null) {
				System.out.println(taskClass + " was done on " + lastRuntimeEnd);
				Long now = DateUtil.getCurrentLongDate();
				if(now >= lastRuntimeEnd + interval) {
					isRuntime = true;
				}
			}
		}
		return isRuntime;
	}
	
	public boolean isRun() {
		return this.isRun;
	}
	
	public void update(int successCount, int errorCount) {
		this.lastRunSuccessCount = successCount;
		this.lastRunErrorCount = errorCount;
	}
	
	public void start() {
		if(this.isRun) {
			throw new RuntimeErrorException(new Error(), "[" + taskClass + "] is already running!");
		} else {
			isRun = true;
			lastRuntimeStart = DateUtil.getCurrentLongDate();
		}
	}
	
	public void end() {
		if(!this.isRun) {
			throw new RuntimeErrorException(new Error(), "[" + taskClass + "] is not running!");
		} else {
			isRun = false;
			lastRuntimeEnd = DateUtil.getCurrentLongDate();
		}
	}

	public String getTaskName() {
		return className;
	}

	public Long getLastRuntimeEnd() {
		return lastRuntimeEnd;
	}

	public Long getLastRuntimeStart() {
		return lastRuntimeStart;
	}

	public int getLastRunErrorCount() {
		return lastRunErrorCount;
	}

	public int getLastRunSuccessCount() {
		return lastRunSuccessCount;
	}

}
