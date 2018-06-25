package neoonki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import neoonki.data.rx.TaskData;
import neoonki.task.SampleTask;

@RestController
public class HomeController {
	
	@Autowired
    private TaskData taskData;
	
	@Autowired
	private SampleTask sampleTask;
	
	@RequestMapping("/")
	public String index() {
		return "index : " + taskData.getTaskCount();
	}
	
	@RequestMapping("/run")
	public void run() {
		try {
			sampleTask.run("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
