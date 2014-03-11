package be.phury.taskprocessing.example;

import java.util.ArrayList;
import java.util.List;

import be.phury.taskprocessing.Producer;
import be.phury.taskprocessing.Task;

public class HelloWorldProducer implements Producer {

	private transient Integer toProduce = 20;
	private transient Integer produced = 0;
	
	@Override
	public List<Task> generateTasks(Integer nbTasks) {
		final List<Task> tasks = new ArrayList<Task>(nbTasks);
		if (produced < toProduce) {
			for (int i = 0; i < nbTasks; i++) {
				tasks.add(createTask(produced+i));
			}
			produced+=nbTasks;
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return tasks;
	}
	
	private Task createTask(final Integer taskNumber) {
		System.out.println(Thread.currentThread().getName() + " > creating task :" + taskNumber);
		return new Task() {
			@Override
			public String toString() {
				return "Hello world " + taskNumber;
			}
		};
	}

}
