package be.phury.taskprocessing.example;

import be.phury.taskprocessing.ProducerConsumer;
import be.phury.taskprocessing.ProducerConsumerConfiguration;

public class HelloApp {
	public static void main(String[] args) {
		final ProducerConsumer pc = new ProducerConsumer(new ProducerConsumerConfiguration()
				.setBatchSize(5)
				.setQueueSize(10)
				.setConsumerThreads(3)
				.setConsumerClass(HelloWorldConsumer.class)
				.setProducerClass(HelloWorldProducer.class));
		pc.start();
	}
}
