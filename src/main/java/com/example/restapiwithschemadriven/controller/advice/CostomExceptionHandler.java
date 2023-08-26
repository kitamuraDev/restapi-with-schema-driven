package com.example.restapiwithschemadriven.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.restapiwithschemadriven.service.task.TaskEntityNotFoundException;

@RestControllerAdvice
public class CostomExceptionHandler {

  /**
   * TaskEntityNotFoundException が発生したときに処理をフックするハンドラーメソッド
   *
   * @param e TaskEntityNotFoundException
   * @return ResponseEntity<Void>
   */
  @ExceptionHandler(TaskEntityNotFoundException.class)
  public ResponseEntity<Void> handleTaskEntityNotFoundException(TaskEntityNotFoundException e) {
    return ResponseEntity.notFound().build();
  }

}
