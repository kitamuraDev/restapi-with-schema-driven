package com.example.restapiwithschemadriven.controller.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.restapiwithschemadriven.service.task.TaskEntityNotFoundException;
import com.example.todoapi.model.BadRequestError;
import com.example.todoapi.model.ResourceNotFoundError;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

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

  /**
   * MethodArgumentNotValidException が発生したときに処理をフックするハンドラーメソッド
   * Spring側で ResponseEntityExceptionHandler#handleMethodArgumentNotValid という専用のメソッドが用意されているので、オーバーライドする
   */
  @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request
  ){
    var error = BadRequestErrorCreator.from(ex);
		return ResponseEntity.badRequest().body(error);
	}

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<BadRequestError> handleConstraintViolationException(ConstraintViolationException ex) {
    var error = BadRequestErrorCreator.form(ex);
    return ResponseEntity.badRequest().body(error);
  }

}
