package com.skf.task.service;

import com.skf.task.api.MessageFindRequest;
import com.skf.task.api.MessageService;
import com.skf.task.model.Message;
import com.skf.task.repository.MessageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 26.12.2020
 *
 * @author Pylyp Gorpenko
 */
@Slf4j
@Service
@AllArgsConstructor
public class RepositoryMessageService implements MessageService {
  private final MessageRepository messageRepository;

  @Override
  public void pushMessage(@Valid @NotNull String content) {
    log.trace("Creating message {content: {}}", content);
    Message message = Message.builder()
        .content(content)
        .createdTime(LocalDateTime.now())
        .build();
    Message saved = messageRepository.save(message);
    log.info("Created message {message: {}}", saved);
  }

  @Override
  public Message getLast() {
    log.info("Getting last message");
    List<Message> messages = (List<Message>) messageRepository.findAll();
    messages.sort(Comparator.comparing(Message::getCreatedTime));
    return messages.stream().reduce((first, second) -> second)
        .orElse(null);
  }

  @Override
  public List<Message> getByTime(MessageFindRequest messageFindRequest) {
    log.info("Getting messages between times {start:{}, end: {}}", messageFindRequest.getStart(), messageFindRequest.getEnd());
    List<Message> messages = (List<Message>) messageRepository.findAll();
    return messages.stream()
        .filter(message ->compareDates(messageFindRequest, message.getCreatedTime()))
        .collect(Collectors.toList());
  }

  private Boolean compareDates(MessageFindRequest messageFindRequest, LocalDateTime createdDate) {
    return createdDate.isEqual(messageFindRequest.getStart()) || createdDate.isEqual(messageFindRequest.getEnd()) ||
        (createdDate.isAfter(messageFindRequest.getStart()) && createdDate.isBefore(messageFindRequest.getEnd()));
  }

}
