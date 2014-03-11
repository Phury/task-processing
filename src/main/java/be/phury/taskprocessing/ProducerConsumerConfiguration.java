package be.phury.taskprocessing;


public class ProducerConsumerConfiguration {
	
	private static final Integer DEFAULT_QUEUE_SIZE = 10;
	private static final Integer DEFAULT_BATCH_SIZE = 1;
	private static final Integer DEFAULT_CONSUMER_THREADS = 1;
	
	private Integer queueSize;
	private Integer batchSize;
	private Integer consumerThreads;
	private Class<? extends Consumer> consumerClass;
	private Class<? extends Producer> producerClass;
	private Consumer consumerInstance;
	private Producer producerInstance;
	
	public ProducerConsumerConfiguration() {
		setBatchSize(DEFAULT_BATCH_SIZE);
		setQueueSize(DEFAULT_QUEUE_SIZE);
		setConsumerThreads(DEFAULT_CONSUMER_THREADS);
	}
	
	public Integer getQueueSize() {
		return queueSize;
	}
	
	public ProducerConsumerConfiguration setQueueSize(Integer queueSize) {
		this.queueSize = queueSize;
		return this;
	}
	
	public Integer getBatchSize() {
		return batchSize;
	}
	
	public ProducerConsumerConfiguration setBatchSize(Integer batchSize) {
		this.batchSize = batchSize;
		return this;
	}
	
	public Integer getConsumerThreads() {
		return consumerThreads;
	}
	
	public ProducerConsumerConfiguration setConsumerThreads(Integer consumerThreads) {
		this.consumerThreads = consumerThreads;
		return this;
	}
	
	public Class<? extends Consumer> getConsumerClass() {
		return consumerClass;
	}
	
	public ProducerConsumerConfiguration setConsumerClass(Class<? extends Consumer> consumerClass) {
		this.consumerClass = consumerClass;
		return this;
	}
	
	public Class<? extends Producer> getProducerClass() {
		return producerClass;
	}
	
	public ProducerConsumerConfiguration setProducerClass(Class<? extends Producer> producerClass) {
		this.producerClass = producerClass;
		return this;
	}
	
	public Consumer getConsumerInstance() {
		if (consumerInstance == null) {
			try {
				setConsumerInstance(getConsumerClass().newInstance());
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
		return consumerInstance;
	}
	
	public ProducerConsumerConfiguration setConsumerInstance(Consumer consumerInstance) {
		this.consumerInstance = consumerInstance;
		return this;
	}
	
	public Producer getProducerInstance() {
		if (producerInstance == null) {
			try {
				setProducerInstance(getProducerClass().newInstance());
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
		return producerInstance;
	}
	
	public ProducerConsumerConfiguration setProducerInstance(Producer producerInstance) {
		this.producerInstance = producerInstance;
		return this;
	}
}
