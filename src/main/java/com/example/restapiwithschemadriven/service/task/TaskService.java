package com.example.restapiwithschemadriven.service.task;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

  public TaskEntity find() {
    return new TaskEntity(2, "from service");
  }

}
