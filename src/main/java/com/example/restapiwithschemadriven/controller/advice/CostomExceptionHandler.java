package com.example.restapiwithschemadriven.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.restapiwithschemadriven.service.task.TaskEntityNotFoundException;
import com.example.todoapi.model.ResourceNotFoundError;

@RestControllerAdvice
public class CostomExceptionHandler {

  /**
   * TaskEntityNotFoundException が発生したときに処理をフックするハンドラーメソッド
   *
   * @param e TaskEntityNotFoundException
   * @return ResponseEntity<Void>
   */
  @ExceptionHandler(TaskEntityNotFoundException.class)
  public ResponseEntity<ResourceNotFoundError> handleTaskEntityNotFoundException(TaskEntityNotFoundException e) {
    var error = new ResourceNotFoundError();
    error.setDetail(e.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

}
