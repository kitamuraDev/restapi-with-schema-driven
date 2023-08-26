package com.example.restapiwithschemadriven.service.task;

import org.springframework.stereotype.Service;

import com.example.restapiwithschemadriven.repository.task.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;

  public TaskEntity find(Long taskId) {
    return taskRepository.select(taskId)
      .map(record -> new TaskEntity(record.getId(), record.getTitle()))
      .orElseThrow(() -> new TaskEntityNotFoundException(taskId));
  }

}
