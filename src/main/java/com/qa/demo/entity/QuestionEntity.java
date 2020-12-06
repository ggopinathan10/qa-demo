package com.qa.demo.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="QUESTIONS")
public class QuestionEntity extends AbstractEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "QUESTION_ID")
	private int id;
	
	@Column(name = "QUESTION")
	private String question;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "QUESTION_ID")
	private Set<AnswerEntity> answers;
	
	public QuestionEntity() {}
	
	
	
	public QuestionEntity(int id, String question, Set<AnswerEntity> answers) {
		super();
		this.id = id;
		this.question = question;
		this.answers = answers;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public Set<AnswerEntity> getAnswers() {
		return answers;
	}
	public void setAnswers(Set<AnswerEntity> answers) {
		this.answers = answers;
	}
	
}
