package com.qa.demo.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.qa.demo.dao.QuestionDao;
import com.qa.demo.entity.AnswerEntity;
import com.qa.demo.entity.QuestionEntity;

@Service
@Qualifier("questionService")
@Transactional
public class QuestionEntityServiceImpl implements QuestionService{
	
	@Autowired
	private QuestionDao questionDao;
	
	@Autowired
	@Qualifier("qaValidatorService")
	private QAValidatorService qaValidatorService;
	

	@Override
	public QuestionEntity getQuestionWithAnswers(String question) {
		QuestionEntity questionEntity = null;
		Optional<QuestionEntity> optional = questionDao.findByQuestion(question);
		if(optional.isPresent()) {
			questionEntity = optional.get();
		}
		return questionEntity;
	}
	
	@Override
	public boolean addNewQuestionWithAnswer(String questionAndAnswer) {
		if(qaValidatorService.isValidQuestion(questionAndAnswer) && qaValidatorService.isValidAnswer(questionAndAnswer)) {
			QuestionEntity questionEntity = null;
			Set<AnswerEntity> answersList = null;
			String[] inputArr = questionAndAnswer.split("\\?");
			if(inputArr.length > 1) {
				String question = inputArr[0];
				questionEntity = getQuestionWithAnswers(question+"?");
				Pattern p = Pattern.compile("\\<(.*?)\\>");
				Matcher m = p.matcher(inputArr[1]);
				if(null != questionEntity) {
					answersList = questionEntity.getAnswers();
				}else {
					questionEntity = new QuestionEntity();
					answersList = new HashSet<>();
				}
				while(m.find()) {
					answersList.add(new AnswerEntity(m.group(1)));
				}
				questionEntity.setQuestion(question+"?");
				questionEntity.setAnswers(answersList);
			}
			QuestionEntity qe = questionDao.save(questionEntity);
			if(null != qe) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	

}
