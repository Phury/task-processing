package be.phury.taskprocessing;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ProducerRunnable implements Runnable {

	private final BlockingQueue<Task> queue;
	private final Producer producerService;
	private final Integer batchSize;
	private final Integer nbConsumers;
	
	public ProducerRunnable(BlockingQueue<Task> queue, Producer producerService, Integer batchSize, Integer nbConsumers) {
		this.queue = queue;
		this.producerService = producerService;
		this.batchSize = batchSize;
		this.nbConsumers = nbConsumers;
	}
	
	@Override
	public void run() {
		List<Task> tasks;
		while ((tasks = getProducerService().generateTasks(batchSize)).size() > 0) {
			try {	
				for (Task task : tasks) {
					getQueue().put(task);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("producer: sending shutdown");
		try {
			for (int i = 0; i < nbConsumers; i++) {
				getQueue().put(Task.SHUTDOWN_TASK);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("producer: finished");
	}

	private BlockingQueue<Task> getQueue() {
		return queue;
	}
	
	private Producer getProducerService() {
		return producerService;
	}
	
}
