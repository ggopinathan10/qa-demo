package com.qa.demo.dao;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import com.qa.demo.entity.QuestionEntity;

public interface QuestionDao extends CrudRepository<QuestionEntity, Integer>{
	
	Optional<QuestionEntity> findByQuestion(String question);
	
}
