package com.example.restapiwithschemadriven.controller.task;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoapi.controller.TasksApi;

@RestController
public class TaskController implements TasksApi {

  @Override
  public ResponseEntity<Void> showTask() {
    return ResponseEntity.ok().build();
  }

}
