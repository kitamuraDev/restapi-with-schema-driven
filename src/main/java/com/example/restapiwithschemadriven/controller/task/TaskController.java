package com.example.restapiwithschemadriven.controller.task;

import java.net.URI;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapiwithschemadriven.service.task.TaskEntity;
import com.example.restapiwithschemadriven.service.task.TaskService;
import com.example.todoapi.controller.TasksApi;
import com.example.todoapi.model.PageDTO;
import com.example.todoapi.model.TaskDTO;
import com.example.todoapi.model.TaskForm;
import com.example.todoapi.model.TaskListDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TaskController implements TasksApi {

  private final TaskService taskService;

  @Override
  public ResponseEntity<TaskDTO> showTask(Long taskId) {
    var entity = taskService.find(taskId);
    var dto = toTaskDTO(entity);

    return ResponseEntity.ok(dto);
  }

  @Override
  public ResponseEntity<TaskDTO> createTask(TaskForm form) {
    var entity = taskService.create(form.getTitle());
    var dto = toTaskDTO(entity);

    return ResponseEntity
      .created(URI.create("/tasks/" + dto.getId()))
      .body(dto);
  }

  @Override
  public ResponseEntity<TaskListDTO> listTasks(Integer limit, Long offset) {
    var entityList = taskService.find(limit, offset);

    var dtoList = entityList.stream()
      .map(this::toTaskDTO)
      .collect(Collectors.toList());

    var pageDTO = new PageDTO(limit, offset, dtoList.size());

    var dto = new TaskListDTO();
    dto.setPage(pageDTO);
    dto.setResults(dtoList);

    return ResponseEntity.ok(dto);
  }

  // List<TaskEntity> -> List<TaskDTO> に変換
  private TaskDTO toTaskDTO(TaskEntity taskEntity) {
    return new TaskDTO(taskEntity.getId(), taskEntity.getTitle());
  }

}
