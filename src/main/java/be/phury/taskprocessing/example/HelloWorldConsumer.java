package be.phury.taskprocessing.example;

import be.phury.taskprocessing.Consumer;
import be.phury.taskprocessing.Task;

public class HelloWorldConsumer implements Consumer {

	@Override
	public void process(Task task) {
		System.out.println(Thread.currentThread().getName() + " > processing task: " + task);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
