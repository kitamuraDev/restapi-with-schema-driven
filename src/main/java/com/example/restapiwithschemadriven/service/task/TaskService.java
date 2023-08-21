package com.example.restapiwithschemadriven.service.task;

import org.springframework.stereotype.Service;

import com.example.restapiwithschemadriven.repository.task.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;

  public TaskEntity find() {
    return taskRepository.select()
      .map(record -> new TaskEntity(record.getId(), record.getTitle()))
      .orElseThrow(() -> new IllegalStateException("someting throw exception")); // TODO
  }

}
