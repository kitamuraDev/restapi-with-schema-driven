package com.example.restapiwithschemadriven.service.task;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.restapiwithschemadriven.repository.task.TaskRecord;
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

  public List<TaskEntity> find(Integer limit, Long offset) {
    return taskRepository.selectList(limit, offset).stream()
      .map(record -> new TaskEntity(record.getId(), record.getTitle()))
      .collect(Collectors.toList());
  }

  public TaskEntity create(String title) {
    var record = new TaskRecord(null, title);
    taskRepository.insert(record);

    return new TaskEntity(record.getId(), record.getTitle());
  }

}
