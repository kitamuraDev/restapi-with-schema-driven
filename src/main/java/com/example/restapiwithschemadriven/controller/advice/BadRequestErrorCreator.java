package com.example.restapiwithschemadriven.controller.advice;

import java.util.stream.Collectors;

import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.todoapi.model.BadRequestError;
import com.example.todoapi.model.InvalidParam;

public class BadRequestErrorCreator {

  public static BadRequestError from(MethodArgumentNotValidException ex) {

    // ex.getFieldErrors からエラー情報を取り出して、List<InvalidParam>を組み立てる
    var invalidParamList = ex.getFieldErrors()
      .stream()
      .map(fieldError -> {
        var invalidParam = new InvalidParam(fieldError.getField(), fieldError.getDefaultMessage());
        return invalidParam;
      })
    .collect(Collectors.toList());

    var error = new BadRequestError();
    error.setInvalidParams(invalidParamList);

    return error;

  }

}
