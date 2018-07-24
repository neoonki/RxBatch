package neoonki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import neoonki.data.rx.TaskDataHouse;
import neoonki.task.SampleTask;

@RestController
public class HomeController {
	
	@Autowired
    private TaskDataHouse taskDataHouse;
	
	@Autowired
	private SampleTask sampleTask;
	
	@RequestMapping("/")
	public String index() {
		return "index : " + taskDataHouse.getTaskTotalCount();
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
