package com.example.restapiwithschemadriven.service;

import org.springframework.stereotype.Service;

@Service
public class SampleService {

  public SampleEntity find() {
    return new SampleEntity("hello world");
  }

}
