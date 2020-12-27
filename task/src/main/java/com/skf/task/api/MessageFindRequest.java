package com.skf.task.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created on 26.12.2020
 *
 * @author Pylyp Gorpenko
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Value
@AllArgsConstructor
@Builder
public class MessageFindRequest {

  @NotNull
  private LocalDateTime start;
  @NotNull
  private LocalDateTime end;

}
