package com.skf.task.service;

import com.skf.task.api.MessageFindRequest;
import com.skf.task.model.Message;
import com.skf.task.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created on 26.12.2020
 *
 * @author Pylyp Gorpenko
 */
@ExtendWith(MockitoExtension.class)
public class RepositoryMessageServiceTest {

  @Autowired
  private RepositoryMessageService repositoryMessageService;

  @Mock
  private MessageRepository messageRepository;

  @BeforeEach
  public void setUp() {

    Message message = Message.builder()
        .content("test1")
        .createdTime(LocalDateTime.now().minusHours(1))
        .id("test")
        .build();

    Message message2 = Message.builder()
        .content("test2")
        .createdTime(LocalDateTime.now())
        .id("test")
        .build();

    repositoryMessageService = new RepositoryMessageService(messageRepository);

    Mockito.when(messageRepository.findAll()).thenReturn(Arrays.asList(message, message2));
  }


  @Test
  public void testPushMessage() {
    repositoryMessageService.pushMessage("test2");

    Message last = repositoryMessageService.getLast();

    assertThat(last.getContent()).isEqualTo("test2");
  }

  @Test
  public void testGetByTime() {

    MessageFindRequest messageFindRequest = MessageFindRequest.builder()
        .start(LocalDateTime.now().minusDays(1))
        .end(LocalDateTime.now())
        .build();

    List<Message> messages = repositoryMessageService.getByTime(messageFindRequest);

    assertThat(messages.size()).isEqualTo(2);
  }




}
