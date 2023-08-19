package com.example.restapiwithschemadriven.repository.sample;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SampleRepository {

  @Select("SELECT content FROM SAMPLES")
  public SampleRecord select();

}
