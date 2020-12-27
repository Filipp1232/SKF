package com.skf.task.web.boundary;

import com.skf.task.api.MessageFindRequest;
import com.skf.task.api.MessageService;
import com.skf.task.model.Message;
import com.skf.task.repository.MessageRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


/**
 * Created on 26.12.2020
 *
 * @author Pylyp Gorpenko
 */
@Api(value = MessageResource.API_URL)
@SwaggerDefinition(
    info = @Info(
        title = "Message management API",
        version = "v1"
    )
)
@RestController
@RequestMapping(value = MessageResource.API_URL)
@AllArgsConstructor
@Slf4j
public class MessageResource {

  static final String API_URL = "/api/message";

  private final MessageService messageService;

  @ApiOperation(value = "Create message")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "content", value = "content", dataType = "string", paramType = "query")
  })
  @GetMapping(value = "/publish")
  @ResponseStatus(HttpStatus.OK)
  public void publish(@RequestParam String content) {
    messageService.pushMessage(content);
  }

  @ApiOperation(value = "Get last message")
  @GetMapping(value = "/getLast")
  @ResponseStatus(HttpStatus.OK)
  public Message getLast() {
    return messageService.getLast();
  }

  @ApiOperation(value = "Get messages between times")
  @PostMapping(value = "/getByTime")
  @ResponseStatus(HttpStatus.OK)
  public List<Message> getByTime(@RequestBody @Valid  MessageFindRequest messageFindRequest) {
    return messageService.getByTime(messageFindRequest);
  }

}
