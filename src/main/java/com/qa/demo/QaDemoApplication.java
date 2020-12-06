package com.qa.demo;

import org.springframework.boot.SpringApplication;
import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import com.qa.demo.entity.AnswerEntity;
import com.qa.demo.entity.QuestionEntity;
import com.qa.demo.service.OptionValidatorService;
import com.qa.demo.service.QAValidatorService;
import com.qa.demo.service.QuestionService;
import com.qa.demo.utils.InputOptionsEnum;
import com.qa.demo.utils.StringConstants;

@SpringBootApplication
@ComponentScan(basePackages= {"com.qa.demo", "com.qa.demo.service"})
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class QaDemoApplication implements CommandLineRunner{

	
	@Autowired
	private Environment environment;
	
	@Autowired
	@Qualifier("optionValidatorService")
	private OptionValidatorService optionValidatorService;
	
	@Autowired
	@Qualifier("qaValidatorService")
	private QAValidatorService qaValidatorService;
	
	@Autowired
	@Qualifier("questionService")
	private QuestionService questionService;
	
	public static void main(String[] args) {
		SpringApplication.run(QaDemoApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println(environment.getProperty(StringConstants.WELCOME_TEXT_KEY));
		System.out.println(environment.getProperty(StringConstants.OPTION_TEXT_KEY));
		Scanner reader = new Scanner(System.in);
		while(reader.hasNext()) {
			String optionInput = reader.nextLine();
			if(optionValidatorService.isNotEmpty(optionInput) && optionValidatorService.isValidInputOption(optionInput)) {
				InputOptionsEnum option = InputOptionsEnum.valueOf(optionInput);
				switch(option) {
					case A:displayOnConsole(processQuestion(reader));
							break;
					case B:displayOnConsole(processQuestionAndAnswer(reader));
							break;
					case C:System.out.println(environment.getProperty(StringConstants.GOODBYE_KEY));
							System.exit(1);
							break;
				}
			}else {
				System.out.println(environment.getProperty(StringConstants.INVALID_OPTION_ERR_KEY));
			}
		}
	}
	
	
	public String processQuestion(Scanner reader) {
		displayOnConsole(environment.getProperty(StringConstants.ADD_QUESTION_KEY));
		String question = reader.nextLine();
		String displayStr = environment.getProperty(StringConstants.DEFAULT_ANSWER_KEY);
		try {
			if(qaValidatorService.isNotEmpty(question) && qaValidatorService.isValidMaxCharsSize(question)) {
				QuestionEntity questionEntity = questionService.getQuestionWithAnswers(question);
				if(null != questionEntity && null != questionEntity.getAnswers() && !questionEntity.getAnswers().isEmpty()) {
					Set<AnswerEntity> answers = questionEntity.getAnswers();
					StringBuilder sb = new StringBuilder();
					for(AnswerEntity answer : answers) {
						sb.append((answer.getAnswer())+"\n");
					}
					displayStr = sb.toString();
				}
			}
		}catch(Exception e) {
			e.getMessage();
		}
		return displayStr;
	}
	
	public String processQuestionAndAnswer(Scanner reader) {
		displayOnConsole(environment.getProperty(StringConstants.ADD_ANSWER_KEY));
		String questionAndAnswer = reader.nextLine();
		String displayStr = environment.getProperty(StringConstants.QUESTION_SAVED_KEY);
		try {
			if(qaValidatorService.isNotEmpty(questionAndAnswer) && qaValidatorService.isValidMaxCharsSize(questionAndAnswer)) {
				if(questionService.addNewQuestionWithAnswer(questionAndAnswer)) {
					displayStr = environment.getProperty(StringConstants.QUESTION_SAVED_KEY);
				}
			}else {
				displayStr = environment.getProperty(StringConstants.INVALID_OPTION_ERR_KEY);
			}
		}catch(Exception e) {
			displayStr = environment.getProperty(StringConstants.ERR_SAVING_QUESTION_KEY);
		}
		return displayStr;
	}
	
	public void displayOnConsole(String displayStr) {
		System.out.println(displayStr);
	}

}
