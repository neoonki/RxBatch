package neoonki.data.vo;

public class TaskVO {
	private String taskName;
	private boolean isRun;
	private String lastRuntimeEnd;
	private String lastRuntimeStart;
	private int lastRunErrorCount;
	private int lastRunSuccess;
	
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public boolean isRun() {
		return isRun;
	}
	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}
	public String getLastRuntimeEnd() {
		return lastRuntimeEnd;
	}
	public void setLastRuntimeEnd(String lastRuntimeEnd) {
		this.lastRuntimeEnd = lastRuntimeEnd;
	}
	public String getLastRuntimeStart() {
		return lastRuntimeStart;
	}
	public void setLastRuntimeStart(String lastRuntimeStart) {
		this.lastRuntimeStart = lastRuntimeStart;
	}
	public int getLastRunErrorCount() {
		return lastRunErrorCount;
	}
	public void setLastRunErrorCount(int lastRunErrorCount) {
		this.lastRunErrorCount = lastRunErrorCount;
	}
	public int getLastRunSuccess() {
		return lastRunSuccess;
	}
	public void setLastRunSuccess(int lastRunSuccess) {
		this.lastRunSuccess = lastRunSuccess;
	}
}
