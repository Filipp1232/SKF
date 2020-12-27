package com.skf.task.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created on 26.12.2020
 *
 * @author Pylyp Gorpenko
 */
@RedisHash("Message")
@AllArgsConstructor
@Builder
@Data
public class Message implements Serializable {

  @Id
  private String id;
  private String content;
  private LocalDateTime createdTime;
}