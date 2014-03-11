package be.phury.taskprocessing;

import java.util.List;

public interface Producer {

	List<Task> generateTasks(Integer nbTasks);

}
