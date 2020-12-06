package com.qa.demo.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.qa.demo.utils.InputOptionsEnum;

@Service
@Qualifier("optionValidatorService")
public class OptionValidatorServiceImpl extends OptionValidatorService{

	@Override
	public boolean isValidInputOption(String option) {
		return option.equals(InputOptionsEnum.A.getOption()) ||
				option.equals(InputOptionsEnum.B.getOption()) ||
				option.equals(InputOptionsEnum.C.getOption());
	}

}
