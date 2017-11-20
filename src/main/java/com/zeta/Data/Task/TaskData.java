package com.zeta.Data.Task;

import com.zeta.Models.Campus;
import com.zeta.Models.Task;

import java.util.List;

public interface TaskData {
    List<Task> getTasks();
    List<Task> getTasks(Campus campus);
}
