package com.qa.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.qa.demo.entity.AnswerEntity;

public interface AnswerDao extends CrudRepository<AnswerEntity, Integer>{

}
