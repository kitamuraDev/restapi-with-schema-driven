package com.example.restapiwithschemadriven.repository.task;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TaskRepository {

  @Select("SELECT id, title FROM TASKS WHERE id = #{taskId}")
  Optional<TaskRecord> select(Long taskId);

}
