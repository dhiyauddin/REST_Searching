package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.CardioChallenge;

public interface CardioChallengeService {
	
	CardioChallenge findById(long id);

	CardioChallenge findByName(String name);

	void saveCardio(CardioChallenge cardio);

	void updateCardio(CardioChallenge  cardio);

	void deleteCardioById(long id);

	List<CardioChallenge> findAllCardios();
	
	List<CardioChallenge> findAllCardiosByName(String level);

	void deleteAllCardios();

	public boolean isCardioExist(CardioChallenge cardio);
}
