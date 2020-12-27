package com.skf.task.repository;

import com.skf.task.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on 26.12.2020
 *
 * @author Pylyp Gorpenko
 */
@Repository
public interface MessageRepository extends CrudRepository<Message, String> {

}