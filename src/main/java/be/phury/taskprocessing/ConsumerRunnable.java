package be.phury.taskprocessing;

import java.util.concurrent.BlockingQueue;

public class ConsumerRunnable implements Runnable {

	private final BlockingQueue<Task> queue;
	private final Consumer consumerService;
	
	public ConsumerRunnable(BlockingQueue<Task> queue, Consumer consumerService) {
		this.queue = queue;
		this.consumerService = consumerService;
	}
	
	@Override
	public void run() {
		while(!(Thread.currentThread().isInterrupted())) {
			try {
				//Thread.sleep(1000);
				Task task = getQueue().take();
				if (task.isShutdown()) {
					Thread.currentThread().interrupt();
				} else {
					getConsumerService().process(task);
				}
				
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		System.out.println("consumer: finished");
	}
	
	private BlockingQueue<Task> getQueue() {
		return queue;
	}
	
	private Consumer getConsumerService() {
		return consumerService;
	}
}
