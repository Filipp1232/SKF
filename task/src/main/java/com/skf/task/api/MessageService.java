package com.skf.task.api;

import com.skf.task.model.Message;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created on 26.12.2020
 *
 * @author Pylyp Gorpenko
 */
public interface MessageService {

  void pushMessage(@Valid @NotNull String content);

  Message getLast();

  List<Message> getByTime(MessageFindRequest messageFindRequest);
}
