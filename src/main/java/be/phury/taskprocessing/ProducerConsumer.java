package be.phury.taskprocessing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {
	
	private final List<Thread> threads = new ArrayList<Thread>();
	
	public ProducerConsumer(ProducerConsumerConfiguration configuration) {
        final BlockingQueue<Task> queue = new ArrayBlockingQueue<Task>(configuration.getQueueSize());
        final ProducerRunnable producer = new ProducerRunnable(
        		queue, 
        		configuration.getProducerInstance(), 
        		configuration.getBatchSize(), 
        		configuration.getConsumerThreads());
        final ConsumerRunnable consumer = new ConsumerRunnable(queue, configuration.getConsumerInstance());
       
        threads.add(createThread("producer thread", producer));
        for (int i = 1; i <= configuration.getConsumerThreads(); i++) {
        	threads.add(createThread("consumer thread " + i, consumer));
		}
	}
	
	private Thread createThread(String name, Runnable runnable) {
		final Thread t = new Thread(runnable);
		t.setName(name);
		return t;
	}
	
	public void start() {
        System.out.println("Starting threads");
        for (Thread t : threads) {
        	t.start();
        }
        
        for (Thread t : threads) {
        	try {
				t.join();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
        }
        System.out.println("Job finished");
    }

}
