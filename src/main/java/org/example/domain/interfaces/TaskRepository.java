package org.example.domain.interfaces;

import org.example.domain.Task;
import org.example.domain.Tasks;

import java.util.List;

public interface TaskRepository {

    Tasks getTasks();
    Tasks saveTask(Tasks tasks);

}
