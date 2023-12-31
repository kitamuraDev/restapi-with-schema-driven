package com.example.restapiwithschemadriven.controller.advice;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.todoapi.model.BadRequestError;
import com.example.todoapi.model.InvalidParam;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ElementKind;

public class BadRequestErrorCreator {

  /**
   * エラー情報を取り出して、BadRequestError#setInvalidParams でセットして BadRequestErrorオブジェクトを返す
   *
   * @param ex バリデーションエラーで投げられる例外
   * @return BadRequestError
   */
  public static BadRequestError from(MethodArgumentNotValidException ex) {

    var invalidParamList = createInvalidParamList(ex);

    var error = new BadRequestError();
    error.setInvalidParams(invalidParamList);

    return error;

  }

  public static BadRequestError form(ConstraintViolationException ex) {
    var invalidParamList = createInvalidParamList(ex);

    var error = new BadRequestError();
    error.setInvalidParams(invalidParamList);

    return error;
  }

  private static List<InvalidParam> createInvalidParamList(ConstraintViolationException ex) {
    return ex.getConstraintViolations()
      .stream()
      .map(BadRequestErrorCreator::createInvalidParam)
      .collect(Collectors.toList());
  }

  private static InvalidParam createInvalidParam(ConstraintViolation<?> violation) {
    var parameterOpt = StreamSupport.stream(violation.getPropertyPath().spliterator(), false)
      .filter(node -> node.getKind().equals(ElementKind.PARAMETER))
      .findFirst();
    var invalidParam = new InvalidParam();
    parameterOpt.ifPresent(p -> invalidParam.setName(p.getName()));
    invalidParam.setReason(violation.getMessage());
    return invalidParam;
  }

  /**
   * ex.getFieldErrors からエラー情報を取り出して、List<InvalidParam>を組み立てる
   *
   * @param ex バリデーションエラーで投げられる例外
   * @return List<InvalidParam>
   */
  private static List<InvalidParam> createInvalidParamList(MethodArgumentNotValidException ex) {
    return ex.getFieldErrors()
      .stream()
      .map(BadRequestErrorCreator::createInvalidParam) // Xxx::xxX は method reference と言う書き方
      .collect(Collectors.toList());
  }

  private static InvalidParam createInvalidParam(FieldError fieldError) {
    var invalidParam = new InvalidParam(fieldError.getField(), fieldError.getDefaultMessage());
    return invalidParam;
  }

}
