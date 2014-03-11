package be.phury.taskprocessing;

public abstract class Task {
	
	public static final Task SHUTDOWN_TASK = new Task() {
		protected boolean isShutdown() { 
			return true;
		}
	};
	
	protected boolean isShutdown() {
		return false;
	}

}
