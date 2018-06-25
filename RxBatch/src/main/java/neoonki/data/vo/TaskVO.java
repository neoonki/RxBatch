package neoonki.data.vo;

import javax.management.RuntimeErrorException;

import neoonki.util.DateUtil;

public class TaskVO {
	
	private String taskName;
	private boolean isRun;
	private String lastRuntimeEnd;
	private String lastRuntimeStart;
	private int lastRunErrorCount;
	private int lastRunSuccessCount;
	
	public TaskVO(String taskName) {
		this.taskName = taskName;
		isRun = false;
		lastRuntimeEnd = null;
		lastRuntimeStart = null;
		lastRunErrorCount = 0;
		lastRunSuccessCount = 0;
		
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
			throw new RuntimeErrorException(new Error(), "[" + taskName + "] is already running!");
		} else {
			isRun = true;
			lastRuntimeStart = DateUtil.getCurrentStringDate();
		}
	}
	
	public void end() {
		if(!this.isRun) {
			throw new RuntimeErrorException(new Error(), "[" + taskName + "] is not running!");
		} else {
			isRun = false;
			lastRuntimeEnd = DateUtil.getCurrentStringDate();
		}
	}

	public String getTaskName() {
		return taskName;
	}

	public String getLastRuntimeEnd() {
		return lastRuntimeEnd;
	}

	public String getLastRuntimeStart() {
		return lastRuntimeStart;
	}

	public int getLastRunErrorCount() {
		return lastRunErrorCount;
	}

	public int getLastRunSuccessCount() {
		return lastRunSuccessCount;
	}

}
